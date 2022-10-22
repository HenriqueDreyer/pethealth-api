package com.dreyer.pethealth.api.jpa.repository;

import com.dreyer.pethealth.api.jpa.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterPetEntityRepository extends JpaRepository<PetEntity, Long> {
}
