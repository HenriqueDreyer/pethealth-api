package com.dreyer.pethealth.api.common.boundary.requestmodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Locale;

@Getter
@SuperBuilder
@EqualsAndHashCode
@ToString
public class BaseRequestModel {
    private final String requestId;
    private final Long userId;
    private final Locale locale;
}
