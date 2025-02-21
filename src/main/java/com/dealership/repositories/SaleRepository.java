package com.dealership.repositories;

import com.dealership.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
