package com.dreyer.pethealth.api.core.boundary.requestmodel;

import com.dreyer.pethealth.api.common.boundary.requestmodel.BaseRequestModel;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class FilterPetRequestModel extends BaseRequestModel {
    String petName;
    Integer pageIndex;
    Integer pageSize;
}
