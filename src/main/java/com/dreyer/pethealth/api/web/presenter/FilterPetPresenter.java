package com.dreyer.pethealth.api.web.presenter;

import com.dreyer.pethealth.api.common.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.pethealth.api.common.presenter.BasePresenter;
import com.dreyer.pethealth.api.common.util.UserRequestContext;
import com.dreyer.pethealth.api.core.boundary.output.FilterPetOutput;
import com.dreyer.pethealth.api.core.boundary.responsemodel.FilterPetResponseModel;
import com.dreyer.pethealth.api.web.dto.FilterPetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterPetPresenter extends BasePresenter<List<FilterPetResponseModel>, List<FilterPetDTO>>
        implements FilterPetOutput {

    @Autowired
    public FilterPetPresenter(final MessageSource messageSource,
                              final UserRequestContext userRequestContext) {
        super(messageSource, userRequestContext);
    }

    @Override
    protected ResponseEntity<Object> presentOnError(List<ErrorResponseModel> errors) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.buildErrorResponseBody(errors));
    }

    @Override
    protected List<FilterPetDTO> convert(List<FilterPetResponseModel> responseModel) {
        return responseModel.stream().map(FilterPetDTO::buildFrom).collect(Collectors.toList());
    }
}
