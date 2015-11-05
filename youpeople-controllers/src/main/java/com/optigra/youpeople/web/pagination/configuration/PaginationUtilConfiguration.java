package com.optigra.youpeople.web.pagination.configuration;

import com.optigra.youpeople.web.pagination.PaginationUtil;
import com.optigra.youpeople.web.pagination.order.person.PersonOrderEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by romanmudryi on 21.08.15.
 */
@Configuration
public class PaginationUtilConfiguration {

    @Bean(name = "personPaginationUtil")
    public PaginationUtil personPaginationUtil() {
        PaginationUtil paginationUtil = new PaginationUtil(PersonOrderEnum.values());
        return paginationUtil;
    }
}
