package com.dreyer.pethealth.api.common.validator;

import com.dreyer.pethealth.api.common.boundary.requestmodel.BaseRequestModel;
import com.dreyer.pethealth.api.common.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.pethealth.api.common.errorcode.CommonErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseRequestValidator {
    private BaseRequestValidator(){}

    public static List<ErrorResponseModel> validate(BaseRequestModel requestModel) {
        final var baseRequestModelValidations = new ArrayList<ErrorResponseModel>();

        if(Objects.isNull(requestModel)) {
            baseRequestModelValidations.add(
                    ErrorResponseModel.builder()
                            .code(CommonErrorCode.E0001.name())
                            .message(CommonErrorCode.E0001.getValue())
                            .build()
            );
        }

//        if(Objects.isNull(requestModel.getRequestId())) {
//            baseRequestModelValidations.add(
//                    ErrorResponseModel.builder()
//                            .code(CommonErrorCode.E0005.name())
//                            .message(CommonErrorCode.E0005.getValue())
//                            .build()
//            );
//        }

        if(Objects.isNull(requestModel.getUserId())) {
            baseRequestModelValidations.add(
                    ErrorResponseModel.builder()
                            .code(CommonErrorCode.E0004.name())
                            .message(CommonErrorCode.E0004.getValue())
                            .build()
            );
        }

//        if(Objects.isNull(requestModel.getLocale())) {
//            baseRequestModelValidations.add(
//                    ErrorResponseModel.builder()
//                            .code(CommonErrorCode.E0003.name())
//                            .message(CommonErrorCode.E0003.getValue())
//                            .build()
//            );
//        }

        return baseRequestModelValidations;
    }
}
