package com.dealership.controllers;

import com.dealership.dto.InventoryDTO;
import com.dealership.entities.InventoryPK;
import com.dealership.services.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
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
}
