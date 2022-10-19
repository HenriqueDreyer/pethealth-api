package com.dreyer.pethealth.api.common.boundary.responsemodel;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ErrorResponseModel {
    String code;
    List<String> params;
    String message;
}
