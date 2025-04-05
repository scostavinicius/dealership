package com.dealership.dto;

import com.dealership.entity.Sale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SaleDTO {

    private Long id;
    private Long customerId;
    private Long dealershipId;
    private Long vehicleId;
    private LocalDate saleDate;

    public SaleDTO(Sale sale) {
        this.id = sale.getId();
        this.customerId = sale.getCustomer().getId();  // Mapear o ID do customer
        this.dealershipId = sale.getDealership().getId();  // Mapear o ID do dealership
        this.vehicleId = sale.getVehicle().getId();  // Mapear o ID do vehicle
        this.saleDate = sale.getSaleDate();
    }
}
