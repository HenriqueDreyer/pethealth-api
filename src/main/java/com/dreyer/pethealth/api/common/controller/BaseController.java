package com.dreyer.pethealth.api.common.controller;

import com.dreyer.pethealth.api.common.util.UserRequestContext;
import lombok.Getter;

@Getter
public abstract class BaseController {

    private final UserRequestContext userRequestContext;

    public BaseController(UserRequestContext userRequestContext) {
        this.userRequestContext = userRequestContext;
    }
}
