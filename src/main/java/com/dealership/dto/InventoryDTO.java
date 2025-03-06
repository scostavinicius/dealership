package com.dealership.dto;

import com.dealership.entities.Inventory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryDTO {

    private Integer quantity;
    private Long dealershipId;
    private Long vehicleId;

    public InventoryDTO(Inventory inventory) {
        this.dealershipId = inventory.getId().getDealershipId();
        this.vehicleId = inventory.getId().getVehicleId();
        this.quantity = inventory.getQuantity();
    }
}
