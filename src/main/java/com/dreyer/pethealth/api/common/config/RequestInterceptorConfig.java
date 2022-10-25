package com.dreyer.pethealth.api.common.config;

import com.dreyer.pethealth.api.common.interceptors.AcceptLanguageInterceptor;
import com.dreyer.pethealth.api.common.interceptors.RequestInterceptor;
import com.dreyer.pethealth.api.common.interceptors.UserIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestInterceptorConfig implements WebMvcConfigurer {

    private UserIdInterceptor userIdInterceptor;
    private RequestInterceptor requestInterceptor;
    private AcceptLanguageInterceptor acceptLanguageInterceptor;

    @Autowired
    public RequestInterceptorConfig(UserIdInterceptor userIdInterceptor,
                                    RequestInterceptor requestInterceptor,
                                    AcceptLanguageInterceptor acceptLanguageInterceptor) {
        this.userIdInterceptor = userIdInterceptor;
        this.requestInterceptor = requestInterceptor;
        this.acceptLanguageInterceptor = acceptLanguageInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIdInterceptor);
        registry.addInterceptor(requestInterceptor);
        registry.addInterceptor(acceptLanguageInterceptor);
    }
}
