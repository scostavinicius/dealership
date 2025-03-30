package com.dealership.controllers;

import com.dealership.dto.SaleDTO;
import com.dealership.services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDTO>> findAllSales() {
        return ResponseEntity.ok(saleService.findAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.findSaleById(id));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO newSale = saleService.createSale(saleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
