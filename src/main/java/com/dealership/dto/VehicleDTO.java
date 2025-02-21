package com.dealership.dto;

import com.dealership.entities.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class VehicleDTO {

    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private Double price;
    private Vehicle.Type type;

    public VehicleDTO(Vehicle vehicle) {
        BeanUtils.copyProperties(vehicle, this);
    }
}
