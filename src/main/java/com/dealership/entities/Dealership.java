package com.dealership.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "tb_dealership")
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome da concessionária não pode ser nulo.")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "O endereço da concessionária não pode ser nulo.")
    @Column(nullable = false)
    private String address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    @OneToMany(mappedBy = "dealership", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sale> sales;
}
