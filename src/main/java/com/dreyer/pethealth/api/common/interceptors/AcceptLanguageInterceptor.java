package com.dreyer.pethealth.api.common.interceptors;

import com.dreyer.pethealth.api.common.util.UserRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class AcceptLanguageInterceptor implements HandlerInterceptor {

    public static final String DEFAULT_ACCEPT_LANGUAGE = "en-US";
    private static final Locale DEFAULT_LOCALE = Locale.US;
    private static final String LOCALE_PORTUGUESE = "pt";
    private static final String LOCALE_SPAINISH = "es";

    private final List<Locale> supportedLocales = Arrays.asList(
            Locale.US,
            Locale.ENGLISH,
            new Locale(LOCALE_PORTUGUESE, "BR"),
            new Locale(LOCALE_SPAINISH, "ES")
    );

    private final UserRequestContext userRequestContext;

    @Autowired
    public AcceptLanguageInterceptor(UserRequestContext userRequestContext) {
        this.userRequestContext = userRequestContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String headerAcceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        String acceptLanguage = DEFAULT_ACCEPT_LANGUAGE;
        Locale locale = DEFAULT_LOCALE;

        try{
            if(headerAcceptLanguage != null && !headerAcceptLanguage.isBlank()) {
                final var foundLocale = Locale.lookup(Locale.LanguageRange.parse(headerAcceptLanguage),
                        supportedLocales);
                if(foundLocale != null) {
                    acceptLanguage = headerAcceptLanguage;
                    locale = foundLocale;
                }
            }
        }catch (IllegalArgumentException err) {
            log.error(err.getMessage(), err);
        }

        userRequestContext.setAcceptLanguage(acceptLanguage);
        userRequestContext.setLocale(locale);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userRequestContext.removeAcceptLanguage();
        userRequestContext.removeLocale();
    }
}
