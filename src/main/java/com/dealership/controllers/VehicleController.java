package com.dealership.controllers;

import com.dealership.dto.VehicleDTO;
import com.dealership.services.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAllVehicles() {
        return ResponseEntity.ok(vehicleService.findAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.findVehicleById(id));
    }
}
