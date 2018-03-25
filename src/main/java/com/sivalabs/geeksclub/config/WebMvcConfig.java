package com.sivalabs.geeksclub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addRedirectViewController("/newlink", "/newlink");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration_status").setViewName("registration_status");
    }

    @Bean
    public SpringSecurityDialect securityDialect()  {
        return new SpringSecurityDialect();
    }
}
