package com.dealership.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_inventory")
public class Inventory {

    @EmbeddedId
    private InventoryPK id = new InventoryPK();

    @NotNull(message = "A quantidade de veículos não pode ser nulo.")
    @Column(nullable = false)
    @Positive(message = "A quantidade deve ser um valor positivo.")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "dealership_id", insertable = false, updatable = false)
    private Dealership dealership;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private Vehicle vehicle;
}
