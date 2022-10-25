package com.dreyer.pethealth.api.common.interceptors;

import com.dreyer.pethealth.api.common.util.UserRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class UserIdInterceptor implements HandlerInterceptor {

    public static final String HEADER_X_USER_ID = "X-User-ID";

    private final UserRequestContext userRequestContext;

    @Autowired
    public UserIdInterceptor(UserRequestContext userRequestContext) {
        this.userRequestContext = userRequestContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var headerUserId = request.getHeader(HEADER_X_USER_ID);
        userRequestContext.setUserId(headerUserId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userRequestContext.removeUserId();
    }
}
