package com.dreyer.pethealth.api.common.interceptors;

import com.dreyer.pethealth.api.common.util.UserRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    private final UserRequestContext userRequestContext;

    @Autowired
    public RequestInterceptor(UserRequestContext userRequestContext) {
        this.userRequestContext = userRequestContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var requestId = UUID.randomUUID().toString();
        userRequestContext.setRequestId(requestId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userRequestContext.removeRequestId();
    }
}
