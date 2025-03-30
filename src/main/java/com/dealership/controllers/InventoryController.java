package com.dealership.controllers;

import com.dealership.dto.InventoryDTO;
import com.dealership.entities.Inventory;
import com.dealership.entities.InventoryPK;
import com.dealership.services.InventoryService;
import com.dealership.utils.FindEntitiesUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;
    private final FindEntitiesUtil findEntitiesUtil;

    public InventoryController(InventoryService inventoryService,
                               FindEntitiesUtil findEntitiesUtil) {
        this.inventoryService = inventoryService;
        this.findEntitiesUtil = findEntitiesUtil;
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> findAllInventories() {
        return ResponseEntity.ok(inventoryService.findAllInventories());
    }

    @GetMapping("/{dealershipId}/{vehicleId}")
    public ResponseEntity<InventoryDTO> findInventoryById(@PathVariable Long dealershipId,
                                                          @PathVariable Long vehicleId) {
        InventoryPK inventoryPK = new InventoryPK(dealershipId, vehicleId);

        return ResponseEntity.ok(inventoryService.findInventoryById(inventoryPK));
    }

    @PatchMapping("/{dealershipId}/{vehicleId}/add")
    public ResponseEntity<InventoryDTO> addVehicleToInventory(@PathVariable Long dealershipId,
                                                              @PathVariable Long vehicleId,
                                                              @RequestBody Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade de veículos adicionados deve ser positiva.");
        }

        InventoryDTO updatedInventory =
                inventoryService.addVehicleToInventory(dealershipId, vehicleId, quantity);
        return ResponseEntity.ok(updatedInventory);
    }

    @PatchMapping("/{dealershipId}/{vehicleId}/remove")
    public ResponseEntity<Object> removeVehicleFromInventory(@PathVariable Long dealershipId,
                                                             @PathVariable Long vehicleId,
                                                             @RequestBody Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade de veículos removidos deve ser positiva.");
        }

        InventoryPK inventoryPK = new InventoryPK(dealershipId, vehicleId);
        Inventory inventory = findEntitiesUtil.findInventoryById(inventoryPK);

        if (inventory == null) {
            throw new IllegalArgumentException("O veículo não existe no estoque da concessionária.");
        }

        if (inventory.getQuantity() < quantity) {
            throw new IllegalArgumentException(
                    "A quantidade de veículos a serem removidos excede a quatidade de veículos no estoque (inventory)");
        }

        InventoryDTO updatedInventory =
                inventoryService.removeVehicleFromInventory(dealershipId, vehicleId, quantity);

        if (updatedInventory == null) {
            return ResponseEntity.ok(Map.of("message", "Estoque zerado e removido."));
        }

        return ResponseEntity.ok(updatedInventory);
    }
}
