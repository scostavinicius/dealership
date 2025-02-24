package com.dealership.services;

import com.dealership.dto.UserDTO;
import com.dealership.entities.User;
import com.dealership.repositories.UserRepository;
import com.dealership.utils.FindEntitiesUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FindEntitiesUtil findEntitiesUtil;

    public UserService(UserRepository userRepository, FindEntitiesUtil findEntitiesUtil) {
        this.userRepository = userRepository;
        this.findEntitiesUtil = findEntitiesUtil;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserDTO(user)).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO findUserById(Long id) {
        User user = findEntitiesUtil.findUserById(id);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        user = userRepository.save(user);

        return new UserDTO(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userUpdate) {
        User user = findEntitiesUtil.findUserById(id);

        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        user.setPassword(userUpdate.getPassword());
        user.setRole(userUpdate.getRole());

        User updatedUser = userRepository.save(user);

        return new UserDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findEntitiesUtil.findUserById(id);
        userRepository.delete(user);
    }
}
