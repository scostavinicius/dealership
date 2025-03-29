package com.dealership.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_inventory")
public class Inventory {

    @EmbeddedId
    private InventoryPK id;

    @ManyToOne
    @JoinColumn(name = "dealership_id", nullable = false, insertable = false, updatable = false)
    private Dealership dealership;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false, insertable = false, updatable = false)
    private Vehicle vehicle;

    @NotNull(message = "A quantidade de veículos não pode ser nula.")
    @Column(nullable = false)
    @Positive(message = "A quantidade deve ser um valor positivo.")
    private Integer quantity;

    public Inventory(Dealership dealership, Vehicle vehicle, Integer quantity) {
        this.id = new InventoryPK(dealership.getId(), vehicle.getId());
        this.dealership = dealership;
        this.vehicle = vehicle;
        this.quantity = quantity;
    }
}

