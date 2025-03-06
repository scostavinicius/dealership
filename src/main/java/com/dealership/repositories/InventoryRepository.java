package com.dealership.repositories;

import com.dealership.entities.Inventory;
import com.dealership.entities.InventoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryPK> {
}
