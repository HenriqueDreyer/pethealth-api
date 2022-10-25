package com.dreyer.pethealth.api.jpa.gatewayimp;

import com.dreyer.pethealth.api.core.domain.entity.Pet;
import com.dreyer.pethealth.api.core.domain.gateway.FilterPetGateway;
import com.dreyer.pethealth.api.jpa.entity.PetEntity;
import com.dreyer.pethealth.api.jpa.repository.FilterPetEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FilterPetGatewayImp implements FilterPetGateway {

    private final FilterPetEntityRepository filterPetEntityRepository;

    @Autowired
    public FilterPetGatewayImp(final FilterPetEntityRepository filterPetEntityRepository) {
        this.filterPetEntityRepository = filterPetEntityRepository;
    }

    @Override
    public List<Pet> findByName(String userId, String petName) {
        return Collections.emptyList();
    }

    @Override
    public List<Pet> findByUser(String userId) {
        return Collections.emptyList();
    }

    @Override
    public List<Pet> findAll(Pageable pageable, String userId) {
        Page<PetEntity> allPetsPage = filterPetEntityRepository.findAll(pageable);

        return  allPetsPage.stream()
                .map(this::buildPetFromEntity)
                .collect(Collectors.toList());
    }

    private Pet buildPetFromEntity(PetEntity petEntity) {
        return Pet.builder()
                .id(petEntity.getId())
                .name(petEntity.getName())
                .race(petEntity.getRace())
                .species(petEntity.getSpecies())
                .gender(petEntity.getGender())
                .color(petEntity.getColor())
                .weight(petEntity.getWeight())
                .birthday(petEntity.getBirthday())
                .build();
    }
}
