package com.dreyer.pethealth.api.common.errorresponsemodel;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Locale;

@Value
@Builder
public class ErrorResponseBody {
    String requestId;
    String userId;
    Locale locale;
    List<String> errors;
}
