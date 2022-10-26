package com.dreyer.pethealth.api.web.controller;

import com.dreyer.pethealth.api.common.controller.BaseController;
import com.dreyer.pethealth.api.common.interceptors.AcceptLanguageInterceptor;
import com.dreyer.pethealth.api.common.util.UserRequestContext;
import com.dreyer.pethealth.api.core.boundary.requestmodel.CreatePetRequestModel;
import com.dreyer.pethealth.api.web.dto.CreatePetRequestBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CreatePetController extends BaseController {

    @Autowired
    public CreatePetController(final UserRequestContext userRequestContext) {
        super(userRequestContext);
    }
    
    @PostMapping(value = "/createPet", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPet(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE,
            defaultValue = AcceptLanguageInterceptor.DEFAULT_ACCEPT_LANGUAGE) String acceptLanguage,
                                            @RequestBody(required = false) CreatePetRequestBodyDTO requestBody) {

        final var requestModel = CreatePetRequestModel.builder()
                .requestId(this.getUserRequestContext().getRequestId())
                .userId(this.getUserRequestContext().getUserId())
                .locale(this.getUserRequestContext().getLocale())
                .name(requestBody.getName())
                .race(requestBody.getRace())
                .species(requestBody.getSpecies())
                .gender(requestBody.getGender())
                .color(requestBody.getColor())
                .weight(requestBody.getWeight())
                .birthday(requestBody.getBirthday())
                .build();

        return ResponseEntity.ok().body(requestModel);
    }
}
