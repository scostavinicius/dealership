package com.dealership.repositories;

import com.dealership.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Função para verificar se o email já existe no banco
    boolean existsByEmail(String email);
}
