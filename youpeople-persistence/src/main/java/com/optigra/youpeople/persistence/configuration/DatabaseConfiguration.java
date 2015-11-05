package com.optigra.youpeople.persistence.configuration;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by romanmudryi on 05.08.15.
 */
@Configuration
@EntityScan({"com.optigra.youpeople.domain.*", "com.optigra.youpeople.view.*"})
@EnableJpaRepositories("com.optigra.youpeople.persistence.repository.*")
@EnableTransactionManagement
@EnableJpaAuditing
public class DatabaseConfiguration{

    @Bean
    public AuditorAware<String> createAuditorProvider() {
        return new SecurityAuditor();
    }

    @Bean
    public AuditingEntityListener createAuditingListener() {
        return new AuditingEntityListener();
    }


}
