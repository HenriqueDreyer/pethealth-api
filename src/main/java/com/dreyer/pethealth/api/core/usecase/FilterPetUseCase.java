package com.dreyer.pethealth.api.core.usecase;

import com.dreyer.pethealth.api.common.validator.BaseRequestValidator;
import com.dreyer.pethealth.api.core.boundary.input.FilterPetInput;
import com.dreyer.pethealth.api.core.boundary.output.FilterPetOutput;
import com.dreyer.pethealth.api.core.boundary.requestmodel.FilterPetRequestModel;
import com.dreyer.pethealth.api.core.boundary.responsemodel.FilterPetResponseModel;
import com.dreyer.pethealth.api.core.domain.entity.Pet;
import com.dreyer.pethealth.api.core.domain.gateway.FilterPetGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
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

        if(!errorsResponseModel.isEmpty()) {
            filterPetOutput.error(errorsResponseModel);
            return;
        }

        Pageable pageable = PageRequest.of(
                requestModel.getPage(),
                requestModel.getItemPerPage()
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
