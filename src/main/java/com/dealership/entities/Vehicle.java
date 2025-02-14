package com.dealership.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(value = 1886, message = "O ano deve ser maior ou igual a 1886.") // Primeiro carro criado em 1886
    private Integer year;

    @NotNull(message = "O preço do veículo não pode ser nulo.")
    @Column(nullable = false)
    @Positive(message = "O preço deve ser um valor positivo.")
    private Double price;

    @NotNull(message = "O tipo do veículo não pode ser nulo.")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sale> sales;

    public enum Type {
        CAR,
        TRUCK,
        MOTORCYCLE;
    }
}
