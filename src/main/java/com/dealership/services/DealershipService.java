package com.dealership.services;

import com.dealership.dto.DealershipDTO;
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
    private final FindEntitiesUtil findEntitiesUtil;

    public DealershipService(DealershipRepository dealershipRepository,
                             FindEntitiesUtil findEntitiesUtil) {
        this.dealershipRepository = dealershipRepository;
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
        User manager = findEntitiesUtil.findUserById(dealershipDTO.getManagerId());

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
