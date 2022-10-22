package com.dreyer.pethealth.api.web.controller;

import com.dreyer.pethealth.api.core.boundary.output.FilterPetOutput;
import com.dreyer.pethealth.api.core.boundary.requestmodel.FilterPetRequestModel;
import com.dreyer.pethealth.api.web.presenter.FilterPetPresenter;
import com.dreyer.pethealth.api.web.service.FilterPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FilterPetController {

    private final FilterPetService filterPetService;
    private final FilterPetPresenter filterPetPresenter;

    @Autowired
    public FilterPetController(final FilterPetService filterPetService,
                               final FilterPetPresenter filterPetPresenter) {
        this.filterPetService = filterPetService;
        this.filterPetPresenter = filterPetPresenter;
    }

    @GetMapping(value = "/filterPet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> filterPet(@RequestParam("userId") Long userId,
                                            @RequestParam(value = "petName", required = false) String petName,
                                            @RequestParam("page") Integer page,
                                            @RequestParam("itemPerPage") Integer itemPerPage) {

        var request = FilterPetRequestModel.builder()
                .userId(userId)
                .petName(petName)
                .pageIndex(page)
                .pageSize(itemPerPage)
                .build();

        filterPetService.execute(request);

        return filterPetPresenter.present();
    }
}
