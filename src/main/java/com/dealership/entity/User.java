package com.dealership.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do usuário não pode ser nulo.")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "O email do usuário não pode ser nulo.")
    @Column(nullable = false, unique = true)
    @Email(message = "E-mail inválido")
    private String email;

    @NotNull(message = "A senha do usuário não pode ser nulo.")
    @Column(nullable = false)
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;

    @NotNull(message = "O papel do usuário não pode ser nulo.")
    @Column(nullable = false, length = 20)
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sale> sales;

    public enum Role {
        MANAGER,
        USER;
    }

    public Role getRole() {
        return Role.valueOf(this.role);
    }

    public void setRole(Role role) {
        this.role = role.name();
    }


    public boolean isManager() {
        return getRole() == Role.MANAGER;
    }
}
