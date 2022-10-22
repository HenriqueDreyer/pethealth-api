package com.dreyer.pethealth.api.web.service;

import com.dreyer.pethealth.api.core.boundary.input.FilterPetInput;
import com.dreyer.pethealth.api.core.boundary.requestmodel.FilterPetRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FilterPetService {

    private final FilterPetInput filterPetInput;

    @Autowired
    public FilterPetService(final FilterPetInput filterPetInput) {
        this.filterPetInput = filterPetInput;
    }

    public void execute(FilterPetRequestModel requestModel) {
        filterPetInput.execute(requestModel);
    }
}
