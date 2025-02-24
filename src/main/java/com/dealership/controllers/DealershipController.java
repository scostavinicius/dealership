package com.dealership.controllers;

import com.dealership.dto.DealershipDTO;
import com.dealership.entities.Dealership;
import com.dealership.repositories.DealershipRepository;
import com.dealership.services.DealershipService;
import com.dealership.utils.FindEntitiesUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dealerships")
public class DealershipController {

    private final DealershipService dealershipService;
    private final FindEntitiesUtil findEntitiesUtil;

    public DealershipController(DealershipService dealershipService,
                                FindEntitiesUtil findEntitiesUtil) {
        this.dealershipService = dealershipService;
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
}
