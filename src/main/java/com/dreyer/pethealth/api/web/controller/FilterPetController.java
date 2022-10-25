package com.dreyer.pethealth.api.web.controller;

import com.dreyer.pethealth.api.common.controller.BaseController;
import com.dreyer.pethealth.api.common.interceptors.AcceptLanguageInterceptor;
import com.dreyer.pethealth.api.common.util.UserRequestContext;
import com.dreyer.pethealth.api.core.boundary.requestmodel.FilterPetRequestModel;
import com.dreyer.pethealth.api.web.presenter.FilterPetPresenter;
import com.dreyer.pethealth.api.web.service.FilterPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FilterPetController extends BaseController {

    private final FilterPetService filterPetService;
    private final FilterPetPresenter filterPetPresenter;

    @Autowired
    public FilterPetController(final UserRequestContext userRequestContext,
                               final FilterPetService filterPetService,
                               final FilterPetPresenter filterPetPresenter) {
        super(userRequestContext);
        this.filterPetService = filterPetService;
        this.filterPetPresenter = filterPetPresenter;
    }

    @GetMapping(value = "/filterPet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> filterPet(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE,
            defaultValue = AcceptLanguageInterceptor.DEFAULT_ACCEPT_LANGUAGE) String acceptLanguage,
                                            @RequestParam(value = "petName", required = false) String petName,
                                            @RequestParam("page") Integer page,
                                            @RequestParam("itemPerPage") Integer itemPerPage) {

        var request = FilterPetRequestModel.builder()
                .requestId(this.getUserRequestContext().getRequestId())
                .userId(this.getUserRequestContext().getUserId())
                .locale(this.getUserRequestContext().getLocale())
                .petName(petName)
                .pageIndex(page)
                .pageSize(itemPerPage)
                .build();

        filterPetService.execute(request);

        return filterPetPresenter.present();
    }
}
