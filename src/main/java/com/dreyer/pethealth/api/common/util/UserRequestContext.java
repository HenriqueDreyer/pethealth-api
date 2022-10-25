package com.dreyer.pethealth.api.common.util;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserRequestContext {

    private final ThreadLocal<String> requestIdThreadLocal = new ThreadLocal<>();

    private final ThreadLocal<String> userIdThreadLocal = new ThreadLocal<>();

    private final ThreadLocal<String> acceptLanguageThreadLocal = new ThreadLocal<>();

    private final ThreadLocal<Locale> localeThreadLocal = new ThreadLocal<>();

    public String getRequestId() {
        return requestIdThreadLocal.get();
    }

    public void setRequestId(String requestId) {
        requestIdThreadLocal.set(requestId);
    }

    public void removeRequestId() {
        requestIdThreadLocal.remove();
    }

    public String getUserId() {
        return userIdThreadLocal.get();
    }

    public void setUserId(String userId) {
        userIdThreadLocal.set(userId);
    }

    public void removeUserId() {
        userIdThreadLocal.remove();
    }

    public String getAcceptLanguage() {
        return acceptLanguageThreadLocal.get();
    }

    public void setAcceptLanguage(String acceptLanguage) {
        acceptLanguageThreadLocal.set(acceptLanguage);
    }

    public void removeAcceptLanguage() {
        acceptLanguageThreadLocal.remove();
    }

    public Locale getLocale() {
        return localeThreadLocal.get();
    }

    public void setLocale(Locale locale) {
        localeThreadLocal.set(locale);
    }

    public void removeLocale() {
        localeThreadLocal.remove();
    }
}
