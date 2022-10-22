package com.dreyer.pethealth.api.web.dto;

import com.dreyer.pethealth.api.core.boundary.responsemodel.FilterPetResponseModel;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class FilterPetDTO {
    Long id;
    String name;
    String race;
    String species;
    String gender;
    String color;
    Double weight;
    LocalDateTime birthday;

    public static FilterPetDTO buildFrom(FilterPetResponseModel responseModel) {
        return FilterPetDTO.builder()
                .id(responseModel.getId())
                .name(responseModel.getName())
                .race(responseModel.getRace())
                .species(responseModel.getSpecies())
                .gender(responseModel.getGender())
                .color(responseModel.getColor())
                .weight(responseModel.getWeight())
                .birthday(responseModel.getBirthday())
                .build();
    }
}
