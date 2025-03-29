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
}
