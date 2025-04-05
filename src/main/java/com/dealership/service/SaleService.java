package com.dealership.service;

import com.dealership.dto.SaleDTO;
import com.dealership.entity.Dealership;
import com.dealership.entity.Sale;
import com.dealership.entity.User;
import com.dealership.entity.Vehicle;
import com.dealership.repository.SaleRepository;
import com.dealership.util.FindEntitiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final InventoryService inventoryService;
    private final FindEntitiesUtil findEntitiesUtil;

    public SaleService(SaleRepository saleRepository,
                       InventoryService inventoryService,
                       FindEntitiesUtil findEntitiesUtil) {
        this.saleRepository = saleRepository;
        this.inventoryService = inventoryService;
        this.findEntitiesUtil = findEntitiesUtil;
    }

    @Transactional(readOnly = true)
    public List<SaleDTO> findAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream().map(sale -> new SaleDTO(sale)).toList();
    }

    @Transactional(readOnly = true)
    public SaleDTO findSaleById(Long id) {
        Sale sale = findEntitiesUtil.findSaleById(id);
        return new SaleDTO(sale);
    }

    @Transactional
    public SaleDTO createSale(SaleDTO saleDTO) {
        User customer = findEntitiesUtil.findUserById(saleDTO.getCustomerId());
        Dealership dealership = findEntitiesUtil.findDealershipById(saleDTO.getDealershipId());
        Vehicle vehicle = findEntitiesUtil.findVehicleById(saleDTO.getVehicleId());

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setDealership(dealership);
        sale.setVehicle(vehicle);
        sale.setSaleDate(saleDTO.getSaleDate());

        sale = saleRepository.save(sale);

        inventoryService.removeVehicleFromInventory(dealership.getId(), vehicle.getId(), 1);

        return new SaleDTO(sale);
    }

    @Transactional
    public void deleteSale(Long id) {
        Sale sale = findEntitiesUtil.findSaleById(id);
        saleRepository.delete(sale);
    }
}
