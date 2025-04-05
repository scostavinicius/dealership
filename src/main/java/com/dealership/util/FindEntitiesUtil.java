package com.dealership.util;

import com.dealership.entity.*;
import com.dealership.exception.custom.*;
import com.dealership.repository.*;
import org.springframework.stereotype.Component;

@Component
public class FindEntitiesUtil {
    private final UserRepository userRepository;
    private final DealershipRepository dealershipRepository;
    private final VehicleRepository vehicleRepository;
    private final InventoryRepository inventoryRepository;
    private final SaleRepository saleRepository;

    public FindEntitiesUtil(UserRepository userRepository,
                            DealershipRepository dealershipRepository,
                            VehicleRepository vehicleRepository,
                            InventoryRepository inventoryRepository,
                            SaleRepository saleRepository) {
        this.userRepository = userRepository;
        this.dealershipRepository = dealershipRepository;
        this.vehicleRepository = vehicleRepository;
        this.inventoryRepository = inventoryRepository;
        this.saleRepository = saleRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                "Usuário com ID " + id + " não foi encontrado."));
    }

    public Dealership findDealershipById(Long id) {
        return dealershipRepository.findById(id).orElseThrow(() -> new DealershipNotFoundException(
                "Concessionária com ID " + id + " não foi encontrada."));
    }

    public Vehicle findVehicleById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(
                "Veículo com ID " + id + " não foi encontrado."));
    }

    public Inventory findInventoryById(InventoryPK inventoryPK) {
        return inventoryRepository.findById(inventoryPK)
                                  .orElseThrow(() -> new InventoryNotFoundException(
                                          "Estoque (Inventory) com ID " + inventoryPK +
                                          " não foi encontrado."));
    }

    public Sale findSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new SaleNotFoundException(
                "Venda com ID " + id + " não foi encontrada."));
    }

}
