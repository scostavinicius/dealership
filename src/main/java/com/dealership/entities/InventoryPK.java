package com.dealership.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InventoryPK implements Serializable {

    @Column(name = "dealership_id")
    private Long dealershipId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        InventoryPK that = (InventoryPK) obj;
        return Objects.equals(dealershipId, that.dealershipId) &&
               Objects.equals(vehicleId, that.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealershipId, vehicleId);
    }
}

