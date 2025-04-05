package com.dealership.controller;

import com.dealership.dto.VehicleDTO;
import com.dealership.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO newVehicle = vehicleService.createVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id,
                                                    @RequestBody VehicleDTO vehicleUpdate) {
        VehicleDTO updatedVehicle = vehicleService.updateVehicle(id, vehicleUpdate);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
