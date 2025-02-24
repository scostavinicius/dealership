package com.dealership.services;

import com.dealership.dto.DealershipDTO;
import com.dealership.dto.UserDTO;
import com.dealership.entities.Dealership;
import com.dealership.entities.User;
import com.dealership.repositories.DealershipRepository;
import com.dealership.utils.FindEntitiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DealershipService {

    private final DealershipRepository dealershipRepository;
    private final UserService userService;
    private final FindEntitiesUtil findEntitiesUtil;

    public DealershipService(DealershipRepository dealershipRepository, UserService userService,
                             FindEntitiesUtil findEntitiesUtil) {
        this.dealershipRepository = dealershipRepository;
        this.userService = userService;
        this.findEntitiesUtil = findEntitiesUtil;
    }

    @Transactional(readOnly = true)
    public List<DealershipDTO> findAllDealerships() {
        List<Dealership> dealerships = dealershipRepository.findAll();
        return dealerships.stream().map(dealership -> new DealershipDTO(dealership)).toList();
    }

    @Transactional(readOnly = true)
    public DealershipDTO findDealershipById(Long id) {
        Dealership dealership = findEntitiesUtil.findDealershipById(id);
        return new DealershipDTO(dealership);
    }

    @Transactional
    public DealershipDTO createDealership(DealershipDTO dealershipDTO) {
        // Procura o user manager no banco de dados e caso encontre atualiza a role
        User manager = findEntitiesUtil.findUserById(dealershipDTO.getManagerId());
        if (manager.isManager()) {
            throw new RuntimeException(
                    // Um usuário não pode ser gerente de duas concessionárias
                    "O usuário " + manager.getId() + " já é gerente de uma concessionária.");
        } else {
            manager.setRole(User.Role.MANAGER);
            userService.updateUser(manager.getId(), new UserDTO(manager));
        }

        Dealership dealership = new Dealership();

        dealership.setName(dealershipDTO.getName());
        dealership.setAddress(dealershipDTO.getAddress());
        dealership.setManager(manager);

        dealership = dealershipRepository.save(dealership);

        return new DealershipDTO(dealership);
    }

    public void deleteDealership(Long id) {
        Dealership dealership = findEntitiesUtil.findDealershipById(id);
        dealershipRepository.delete(dealership);
    }
}
