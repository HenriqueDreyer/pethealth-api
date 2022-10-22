package com.dreyer.pethealth.api.core.domain.entity;

import lombok.*;

@Value
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@With
public class Owner {
    Long id;
    String name;
    String phoneNumber;
}
