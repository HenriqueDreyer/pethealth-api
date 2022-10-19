package com.dreyer.pethealth.api.common.errorcode;

public enum CommonErrorCode {
    E0001("The request model cannot be null"),
    E0002("The parameter %s cannot be null"),
    E0003("Locale is empty"),
    E0004("User id is empty"),
    E0005("Request id is empty");

    private final String value;

    public String getValue() {
        return value;
    }

    CommonErrorCode(String value) {
        this.value = value;
    }
}
