package com.dealership.controllers;

import com.dealership.dto.DealershipDTO;
import com.dealership.dto.InventoryDTO;
import com.dealership.entities.Dealership;
import com.dealership.entities.Inventory;
import com.dealership.entities.InventoryPK;
import com.dealership.entities.Vehicle;
import com.dealership.services.DealershipService;
import com.dealership.services.InventoryService;
import com.dealership.utils.FindEntitiesUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealerships")
public class DealershipController {

    private final DealershipService dealershipService;
    private final InventoryService inventoryService;
    private final FindEntitiesUtil findEntitiesUtil;

    public DealershipController(DealershipService dealershipService,
                                InventoryService inventoryService,
                                FindEntitiesUtil findEntitiesUtil) {
        this.dealershipService = dealershipService;
        this.inventoryService = inventoryService;
        this.findEntitiesUtil = findEntitiesUtil;
    }

    @GetMapping
    public ResponseEntity<List<DealershipDTO>> findAllDealerships() {
        return ResponseEntity.ok(dealershipService.findAllDealerships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealershipDTO> findDealershipById(@PathVariable Long id) {
        return ResponseEntity.ok(dealershipService.findDealershipById(id));
    }

    @PostMapping
    public ResponseEntity<DealershipDTO> createDealership(@RequestBody DealershipDTO dealershipDTO) {
        DealershipDTO newDealership = dealershipService.createDealership(dealershipDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDealership);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealershipDTO> updateDealership(@PathVariable Long id,
                                                          @RequestBody DealershipDTO dealershipUpdate) {
        DealershipDTO updatedDealership = dealershipService.updateDealership(id, dealershipUpdate);
        return ResponseEntity.ok(updatedDealership);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealership(@PathVariable Long id) {
        dealershipService.deleteDealership(id);
        return ResponseEntity.noContent().build();
    }

    // -----------------------------------------------------------
    // MÈTODOS PARA INVENTORIES PERTENCENTES A UMA DEALERSHIP
    // -----------------------------------------------------------

    // Função para adicionar veículos a uma dealership
    @PatchMapping("/{dealershipId}/{vehicleId}/add")
    public ResponseEntity<InventoryDTO> addVehicle(@PathVariable Long dealershipId,
                                                   @PathVariable Long vehicleId,
                                                   @RequestBody Integer quantity) {
        InventoryPK inventoryPK = new InventoryPK(dealershipId, vehicleId);
        Inventory inventory = null;

        // Validar: se existir esse inventory, adicionar a quantidade de veículos
        //          se não, criar inventory com a quantidade informada
        try {
            inventory = findEntitiesUtil.findInventoryById(inventoryPK);
        } catch (RuntimeException e) {
            // Se não encontrar, seguimos para a criação do novo Inventory
        }

        if (inventory != null) {
            if (quantity <= 0) {
                throw new RuntimeException("A quantidade de veículos adicionados deve ser positiva.");
            }

            inventory.setQuantity(inventory.getQuantity() + quantity);
            InventoryDTO validateInventoryDTO = new InventoryDTO(inventory);
            validateInventoryDTO =
                    inventoryService.updateInventory(inventoryPK, validateInventoryDTO);

            return ResponseEntity.ok(validateInventoryDTO);
        } else {
            Dealership dealership = findEntitiesUtil.findDealershipById(dealershipId);
            Vehicle vehicle = findEntitiesUtil.findVehicleById(vehicleId);

            Inventory newInventory = new Inventory();

            InventoryPK newInventoryPK = new InventoryPK(dealershipId, vehicleId);
            newInventory.setId(newInventoryPK);
            newInventory.setDealership(dealership);
            newInventory.setVehicle(vehicle);
            newInventory.setQuantity(quantity);

            // Verificando a chave composta antes de salvar
            if (newInventory.getId() == null || newInventory.getId().getDealershipId() == null ||
                newInventory.getId().getVehicleId() == null) {
                throw new RuntimeException(
                        "A chave composta do inventory não foi gerada corretamente.");
            }

            InventoryDTO newInventoryDTO = new InventoryDTO(newInventory);

            newInventoryDTO = inventoryService.createInventory(newInventoryDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(newInventoryDTO);
        }
    }

    // Função para remover veículos de uma dealership
    @PatchMapping("/{dealershipId}/{vehicleId}/remove")
    public ResponseEntity<InventoryDTO> removeVehicle(@PathVariable Long dealershipId,
                                                      @PathVariable Long vehicleId,
                                                      @RequestBody Integer quantity) {
        InventoryPK inventoryPK = new InventoryPK(dealershipId, vehicleId);

        // Validar: se existir esse inventory, remover a quantidade de veículos
        //          se não, retornar um erro
        Inventory inventory = findEntitiesUtil.findInventoryById(inventoryPK);

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("A quantidade de veículos removidos deve ser positiva.");
        }

        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException(
                    "A quantidade de veículos a serem removidos excede a quatidade de veículos no estoque (inventory)");
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        InventoryDTO validateInventoryDTO = new InventoryDTO(inventory);
        validateInventoryDTO =
                inventoryService.updateInventory(inventoryPK, validateInventoryDTO);

        if (validateInventoryDTO == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(validateInventoryDTO);
    }

    // Função para remover o estoque (inventory) de um certo veículo de uma dealership
    @DeleteMapping("/{dealershipId}/{vehicleId}")
    public ResponseEntity<Void> deleteInventoryFromDealership(@PathVariable Long dealershipId,
                                                              @PathVariable Long vehicleId) {
        InventoryPK inventoryPK = new InventoryPK(dealershipId, vehicleId);
        inventoryService.deleteInventory(inventoryPK);

        return ResponseEntity.noContent().build();
    }
}
