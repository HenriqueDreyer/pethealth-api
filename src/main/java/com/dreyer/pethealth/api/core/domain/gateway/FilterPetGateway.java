package com.dreyer.pethealth.api.core.domain.gateway;

import com.dreyer.pethealth.api.core.domain.entity.Pet;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilterPetGateway {
    List<Pet> findByName(Long userId, String petName);
    List<Pet> findByUser(Long userId);

    List<Pet> findAll(Pageable pageable, Long userId);
}
