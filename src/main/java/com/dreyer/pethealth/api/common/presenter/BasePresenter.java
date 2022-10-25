package com.dreyer.pethealth.api.common.presenter;

import com.dreyer.pethealth.api.common.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.pethealth.api.common.errorresponsemodel.ErrorResponseBody;
import com.dreyer.pethealth.api.common.util.UserRequestContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BasePresenter<S, T> {
    private final ThreadLocal<T> successResponseBodyThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<List<ErrorResponseModel>> errorsResponseModelThreadLocal = new ThreadLocal<>();

    private final MessageSource messageSource;
    private final UserRequestContext userRequestContext;

    protected BasePresenter(final MessageSource messageSource,
                            final UserRequestContext userRequestContext) {
        this.messageSource = messageSource;
        this.userRequestContext = userRequestContext;
    }

    public ResponseEntity<Object> present() {
        try {
            var errors = this.getErrors();
            if (errors != null && !errors.isEmpty()) {
                return this.presentOnError(errors);
            } else {
                return ResponseEntity.status(this.getSuccessHttpStatus()).body(this.getSuccessResponseBody());
            }
        } finally {
            this.clear();
        }
    }

    public void error(final List<ErrorResponseModel> errors) {
        errorsResponseModelThreadLocal.set(errors);
    }

    public void success(final S responseModel) {
        this.successResponseBodyThreadLocal.set(this.convert(responseModel));
    }

    protected abstract ResponseEntity<Object> presentOnError(final List<ErrorResponseModel> errors);

    protected abstract T convert(final S responseModel);

    protected HttpStatus getSuccessHttpStatus() {
        return HttpStatus.OK;
    }

    protected ErrorResponseBody buildErrorResponseBody(final List<ErrorResponseModel> errors) {
        return ErrorResponseBody.builder()
                .requestId(userRequestContext.getRequestId())
                .userId(userRequestContext.getUserId())
                .locale(userRequestContext.getLocale())
                .errors(this.getLocalizedErrorMessages(errors)).build();
    }

    protected List<String> getLocalizedErrorMessages(final List<ErrorResponseModel> errors) {
        return errors.stream().map(this::getLocalizedErrorMessage).collect(Collectors.toList());
    }

    protected String getLocalizedErrorMessage(final ErrorResponseModel errorResponseModel) {
        final var errorCode = errorResponseModel.getCode();
        final var params = this.getParams(errorResponseModel);
        final var errorMessage = getLocalizedErrorMessage(errorCode, params.toArray(String[]::new));

        return String.format("%s - %s", errorCode, errorMessage);
    }

    private String getLocalizedErrorMessage(final String key, final String[] params) {
        return this.messageSource.getMessage(key, params, userRequestContext.getLocale());
    }

    private T getSuccessResponseBody() {
        return successResponseBodyThreadLocal.get();
    }

    private void clear() {
        successResponseBodyThreadLocal.remove();
        errorsResponseModelThreadLocal.remove();
    }

    private List<ErrorResponseModel> getErrors() {
        return errorsResponseModelThreadLocal.get();
    }

    private List<String> getParams(final ErrorResponseModel errorResponseModel) {
        return errorResponseModel.getParams() != null ? errorResponseModel.getParams() : Collections.emptyList();
    }
}
