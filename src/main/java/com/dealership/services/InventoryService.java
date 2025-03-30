package com.dealership.services;

import com.dealership.dto.InventoryDTO;
import com.dealership.entities.Dealership;
import com.dealership.entities.Inventory;
import com.dealership.entities.InventoryPK;
import com.dealership.entities.Vehicle;
import com.dealership.repositories.InventoryRepository;
import com.dealership.utils.FindEntitiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final FindEntitiesUtil findEntitiesUtil;

    public InventoryService(InventoryRepository inventoryRepository,
                            FindEntitiesUtil findEntitiesUtil) {
        this.inventoryRepository = inventoryRepository;
        this.findEntitiesUtil = findEntitiesUtil;
    }

    @Transactional(readOnly = true)
    public List<InventoryDTO> findAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(inventory -> new InventoryDTO(inventory)).toList();
    }

    @Transactional(readOnly = true)
    public InventoryDTO findInventoryById(InventoryPK inventoryPK) {
        Inventory inventory = findEntitiesUtil.findInventoryById(inventoryPK);
        return new InventoryDTO(inventory);
    }

    public InventoryDTO addVehicleToInventory(Long dealershipId, Long vehicleId, Integer quantity) {
        Dealership dealership = findEntitiesUtil.findDealershipById(dealershipId);
        Vehicle vehicle = findEntitiesUtil.findVehicleById(vehicleId);

        InventoryPK inventoryPK = new InventoryPK(dealershipId, vehicleId);
        Inventory inventory = inventoryRepository.findById(inventoryPK).orElse(null);

        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity() + quantity);
        } else {
            inventory = new Inventory();
            inventory.setId(inventoryPK);
            inventory.setDealership(dealership);
            inventory.setVehicle(vehicle);
            inventory.setQuantity(quantity);
        }

        inventory = inventoryRepository.save(inventory);
        return new InventoryDTO(inventory);
    }

    public InventoryDTO removeVehicleFromInventory(Long dealershipId,
                                                   Long vehicleId,
                                                   Integer quantity) {
        InventoryPK inventoryPK = new InventoryPK(dealershipId, vehicleId);
        Inventory inventory = findEntitiesUtil.findInventoryById(inventoryPK);

        inventory.setQuantity(inventory.getQuantity() - quantity);

        if (inventory.getQuantity() == 0) {
            inventoryRepository.delete(inventory);
            return null;
        }

        inventory = inventoryRepository.save(inventory);
        return new InventoryDTO(inventory);
    }
}
