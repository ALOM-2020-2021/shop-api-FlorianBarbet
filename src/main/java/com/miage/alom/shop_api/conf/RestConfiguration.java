package com.miage.alom.shop_api.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Value("${trainer.service.username}")
    private String username;

    @Value("${trainer.service.password}")
    private String password;


    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    RestTemplate trainerApiRestTemplate(){
        var restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username,password));
        return restTemplate;
    }
}