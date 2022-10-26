package com.dreyer.pethealth.api.core.boundary.requestmodel;

import com.dreyer.pethealth.api.common.boundary.requestmodel.BaseRequestModel;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Value
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreatePetRequestModel extends BaseRequestModel {
    String name;
    String race;
    String species;
    String gender;
    String color;
    Double weight;
    LocalDateTime birthday;
}
