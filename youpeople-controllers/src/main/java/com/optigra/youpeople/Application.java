package com.optigra.youpeople;

import com.optigra.youpeople.configuration.*;
import com.optigra.youpeople.filters.CORSFilter;
import com.optigra.youpeople.persistence.configuration.DatabaseConfiguration;
import com.optigra.youpeople.web.pagination.configuration.PaginationUtilConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * Created by romanmudryi on 04.08.15.
 */
@SpringBootApplication
@Import({
        DatabaseConfiguration.class,
        ServiceConfig.class,
        FacadeConfig.class,
        ControllerConfig.class,
        SecurityConfig.class,
        PaginationUtilConfiguration.class,
        CORSFilter.class,
        SwaggerConfig.class
})
public class Application extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
