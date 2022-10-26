package com.dreyer.pethealth.api.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CreatePetRequestBodyDTO {
    String name;
    String race;
    String species;
    String gender;
    String color;
    Double weight;
    LocalDateTime birthday;
}
