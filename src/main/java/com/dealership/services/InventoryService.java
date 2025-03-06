package com.dealership.services;

import com.dealership.dto.InventoryDTO;
import com.dealership.entities.Inventory;
import com.dealership.entities.InventoryPK;
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
}
