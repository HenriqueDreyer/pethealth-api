package com.dreyer.pethealth.api.core.usecase;

import com.dreyer.pethealth.api.common.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.pethealth.api.common.errorcode.CommonErrorCode;
import com.dreyer.pethealth.api.common.validator.BaseRequestValidator;
import com.dreyer.pethealth.api.core.boundary.input.FilterPetInput;
import com.dreyer.pethealth.api.core.boundary.output.FilterPetOutput;
import com.dreyer.pethealth.api.core.boundary.requestmodel.FilterPetRequestModel;
import com.dreyer.pethealth.api.core.boundary.responsemodel.FilterPetResponseModel;
import com.dreyer.pethealth.api.core.domain.entity.Pet;
import com.dreyer.pethealth.api.core.domain.gateway.FilterPetGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FilterPetUseCase implements FilterPetInput {

    private final FilterPetOutput filterPetOutput;
    private final FilterPetGateway filterPetGateway;

    @Autowired
    public FilterPetUseCase(final FilterPetOutput filterPetOutput,
                            final FilterPetGateway filterPetGateway) {
        this.filterPetOutput = filterPetOutput;
        this.filterPetGateway = filterPetGateway;
    }

    @Override
    public void execute(FilterPetRequestModel requestModel) {
        var errorsResponseModel = BaseRequestValidator.validate(requestModel);

        if (Objects.isNull(requestModel.getPageIndex()) || requestModel.getPageIndex() < 0) {
            errorsResponseModel.add(ErrorResponseModel.builder()
                    .code(CommonErrorCode.E0006.name())
                    .message(CommonErrorCode.E0006.getValue())
                    .build());
        }

        if(Objects.isNull(requestModel.getPageSize()) || requestModel.getPageSize() < 1) {
            errorsResponseModel.add(ErrorResponseModel.builder()
                    .code(CommonErrorCode.E0007.name())
                    .message(CommonErrorCode.E0007.getValue())
                    .build());
        }

        if(!errorsResponseModel.isEmpty()) {
            filterPetOutput.error(errorsResponseModel);
            return;
        }

        Pageable pageable = PageRequest.of(
                requestModel.getPageIndex(),
                requestModel.getPageSize()
        );

        var petList = filterPetGateway.findAll(
                pageable,
                requestModel.getUserId()
        );

        var response = petList.stream()
                .map(this::buildResponseModel)
                .collect(Collectors.toList());

        filterPetOutput.success(response);
    }

    private FilterPetResponseModel buildResponseModel(Pet pet) {
        return FilterPetResponseModel.builder()
                .id(pet.getId())
                .name(pet.getName())
                .race(pet.getRace())
                .species(pet.getSpecies())
                .gender(pet.getGender())
                .color(pet.getColor())
                .weight(pet.getWeight())
                .birthday(pet.getBirthday())
                .build();
    }

}
