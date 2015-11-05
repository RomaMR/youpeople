package com.optigra.youpeople.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Configuration
@ComponentScan({"com.optigra.youpeople.facades.*", "com.optigra.youpeople.converter.*"})
public class FacadeConfig {
}
