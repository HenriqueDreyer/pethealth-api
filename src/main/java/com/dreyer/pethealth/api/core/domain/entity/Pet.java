package com.dreyer.pethealth.api.core.domain.entity;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Value
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@With
public class Pet {
    Long id;
    String name;
    String race;
    String species;
    String gender;
    String color;
    Double weight;
    LocalDateTime birthday;
}
