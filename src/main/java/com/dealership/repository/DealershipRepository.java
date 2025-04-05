package com.dealership.repository;

import com.dealership.entity.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealershipRepository extends JpaRepository<Dealership, Long> {
}
