package com.dealership.service;

import com.dealership.dto.VehicleDTO;
import com.dealership.entity.Vehicle;
import com.dealership.repository.VehicleRepository;
import com.dealership.util.FindEntitiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final FindEntitiesUtil findEntitiesUtil;

    public VehicleService(VehicleRepository vehicleRepository, FindEntitiesUtil findEntitiesUtil) {
        this.vehicleRepository = vehicleRepository;
        this.findEntitiesUtil = findEntitiesUtil;
    }

    @Transactional(readOnly = true)
    public List<VehicleDTO> findAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream().map(vehicle -> new VehicleDTO(vehicle)).toList();
    }

    @Transactional(readOnly = true)
    public VehicleDTO findVehicleById(Long id) {
        Vehicle vehicle = findEntitiesUtil.findVehicleById(id);
        return new VehicleDTO(vehicle);
    }

    @Transactional
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setVehicleYear(vehicleDTO.getVehicleYear());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setType(vehicleDTO.getType());

        vehicle = vehicleRepository.save(vehicle);

        return new VehicleDTO(vehicle);
    }

    @Transactional
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleUpdate) {
        Vehicle vehicle = findEntitiesUtil.findVehicleById(id);

        vehicle.setBrand(vehicleUpdate.getBrand());
        vehicle.setModel(vehicleUpdate.getModel());
        vehicle.setVehicleYear(vehicleUpdate.getVehicleYear());
        vehicle.setPrice(vehicleUpdate.getPrice());
        vehicle.setType(vehicleUpdate.getType());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return new VehicleDTO(updatedVehicle);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = findEntitiesUtil.findVehicleById(id);
        vehicleRepository.delete(vehicle);
    }
}
