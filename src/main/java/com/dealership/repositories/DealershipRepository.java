package com.dealership.repositories;

import com.dealership.entities.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealershipRepository extends JpaRepository<Dealership, Long> {
}
