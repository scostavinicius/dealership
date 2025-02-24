package com.dealership.dto;

import com.dealership.entities.Inventory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class InventoryDTO {

    private Long id;
    private Integer quantity;
    private Long dealership;
    private Long vehicle;

    public InventoryDTO(Inventory inventory) {
        BeanUtils.copyProperties(inventory, this);
    }
}
