package com.dreyer.pethealth.api.core.boundary.responsemodel;

import com.dreyer.pethealth.api.core.domain.entity.Owner;
import lombok.*;

import java.time.LocalDateTime;

@Value
@ToString
@Builder
@EqualsAndHashCode
@With
public class FilterPetResponseModel {
    Long id;
    String name;
    String race;
    String species;
    String gender;
    String color;
    Double weight;
    LocalDateTime birthday;
}
