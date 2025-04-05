package com.dealership.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A marca do veículo não pode ser nula.")
    @Column(nullable = false)
    private String brand;

    @NotNull(message = "O modelo do veículo não pode ser nulo.")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "O ano do veículo não pode ser nulo.")
    @Column(nullable = false)
    private Integer vehicleYear;

    @NotNull(message = "O preço do veículo não pode ser nulo.")
    @Column(nullable = false)
    @Positive(message = "O preço deve ser um valor positivo.")
    private Double price;

    @NotNull(message = "O tipo do veículo não pode ser nulo.")
    @Column(nullable = false, length = 20)
    private String type;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<Sale> sales;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.REMOVE, CascadeType.MERGE,
            CascadeType.PERSIST}, orphanRemoval = true)
    private List<Inventory> inventory;

    public enum Type {
        CAR,
        TRUCK,
        MOTORCYCLE;
    }

    public Type getType() {
        return Type.valueOf(this.type);
    }

    public void setType(Type type) {
        this.type = type.name();
    }
}
