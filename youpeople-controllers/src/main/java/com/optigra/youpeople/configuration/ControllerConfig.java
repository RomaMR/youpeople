package com.optigra.youpeople.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * Created by romanmudryi on 04.08.15.
 */
@Configuration
public class ControllerConfig {

    @Bean
    protected HttpMessageConverter<?> jacksonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

}
