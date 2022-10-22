package com.dreyer.pethealth.api.core.boundary.output;

import com.dreyer.pethealth.api.common.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.pethealth.api.core.boundary.responsemodel.FilterPetResponseModel;

import java.util.List;

public interface FilterPetOutput {
    void success(final List<FilterPetResponseModel> filterPetResponseModels);
    void error(final List<ErrorResponseModel> errors);
}
