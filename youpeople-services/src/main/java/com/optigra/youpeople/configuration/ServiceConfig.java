package com.optigra.youpeople.configuration;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpHost;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by romanmudryi on 06.08.15.
 */
@Configuration
@ComponentScan("com.optigra.youpeople.services.*")
public class ServiceConfig {

    @Bean(name = "bCryptPasswordEncoder")
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean(name = "proxyList")
    public List<HttpHost> proxyList(){
    	// https://theproxisright.com/
    	return Arrays.asList(
    			new HttpHost("185.28.193.95", 8080, "http")
    			);
    }
    

}
