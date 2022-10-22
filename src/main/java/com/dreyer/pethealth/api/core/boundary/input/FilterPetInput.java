package com.dreyer.pethealth.api.core.boundary.input;

import com.dreyer.pethealth.api.core.boundary.requestmodel.FilterPetRequestModel;

public interface FilterPetInput {
    void execute(FilterPetRequestModel requestModel);
}
