package com.dealership.repository;

import com.dealership.entity.Inventory;
import com.dealership.entity.InventoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryPK> {
}
