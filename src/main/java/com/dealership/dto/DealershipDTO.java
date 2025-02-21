package com.dealership.dto;


import com.dealership.entities.Dealership;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class DealershipDTO {

    private Long id;
    private String name;
    private String address;
    private Long managerId;

    public DealershipDTO(Dealership dealership) {
        BeanUtils.copyProperties(dealership, this);
    }
}
