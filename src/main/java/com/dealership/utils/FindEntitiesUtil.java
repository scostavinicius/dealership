package com.dealership.utils;

import com.dealership.entities.Dealership;
import com.dealership.entities.Sale;
import com.dealership.entities.User;
import com.dealership.entities.Vehicle;
import com.dealership.repositories.DealershipRepository;
import com.dealership.repositories.SaleRepository;
import com.dealership.repositories.UserRepository;
import com.dealership.repositories.VehicleRepository;
import org.springframework.stereotype.Component;

@Component
public class FindEntitiesUtil {
    private UserRepository userRepository;
    private DealershipRepository dealershipRepository;
    private VehicleRepository vehicleRepository;
    private SaleRepository saleRepository;

    public FindEntitiesUtil(UserRepository userRepository,
                            DealershipRepository dealershipRepository,
                            VehicleRepository vehicleRepository,
                            SaleRepository saleRepository) {
        this.userRepository = userRepository;
        this.dealershipRepository = dealershipRepository;
        this.vehicleRepository = vehicleRepository;
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

    public Sale findSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Venda com ID " + id + " não foi encontrada."));
    }

}
