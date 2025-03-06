package com.dealership.utils;

import com.dealership.entities.*;
import com.dealership.repositories.*;
import org.springframework.stereotype.Component;

@Component
public class FindEntitiesUtil {
    private UserRepository userRepository;
    private DealershipRepository dealershipRepository;
    private VehicleRepository vehicleRepository;
    private InventoryRepository inventoryRepository;
    private SaleRepository saleRepository;

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
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Usuário com ID " + id + " não foi encontrado."));
    }

    public Dealership findDealershipById(Long id) {
        return dealershipRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Concessionária com ID " + id + " não foi encontrada."));
    }

    public Vehicle findVehicleById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Veículo com ID " + id + " não foi encontrado."));
    }

    public Inventory findInventoryById(InventoryPK inventoryPK) {
        return inventoryRepository.findById(inventoryPK).orElseThrow(() -> new RuntimeException(
                "Estoque (Inventory) com ID " + inventoryPK + " não foi encontrado."));
    }

    public Sale findSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Venda com ID " + id + " não foi encontrada."));
    }

}
