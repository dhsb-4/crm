package com.t248.crm.config;

import com.t248.crm.interceptor.Authorizationlnterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private String[] ex;

    public void addInterceptors(InterceptorRegistry registry) {
        String[] pat = {"/**"};

        String[] ex = {"/login", "/dologin"};
        registry.addInterceptor(new Authorizationlnterceptor()).addPathPatterns(pat).excludePathPatterns(ex);
    }
}
