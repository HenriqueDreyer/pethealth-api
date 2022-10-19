package com.dreyer.pethealth.api.common.errorresponsemodel;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ErrorResponseBody {
    List<String> errors;
}
