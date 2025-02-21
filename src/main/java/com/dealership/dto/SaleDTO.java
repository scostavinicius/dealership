package com.dealership.dto;

import com.dealership.entities.Sale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SaleDTO {

    private Long id;
    private Long customer_id;
    private Long dealership_id;
    private Long vehicle_id;
    private LocalDate saleDate;

    public SaleDTO(Sale sale) {
        BeanUtils.copyProperties(sale, this);
    }
}
