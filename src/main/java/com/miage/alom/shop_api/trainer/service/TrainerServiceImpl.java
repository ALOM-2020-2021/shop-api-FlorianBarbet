package com.miage.alom.shop_api.trainer.service;

import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Locale;

import static java.util.Objects.requireNonNull;

@Service
public class TrainerServiceImpl implements TrainerService {
    RestTemplate restTemplate;
    String trainerServiceUrl;
    static String TRAINER_ROOT = "/trainers/";


    @Override
    public Trainer getTrainer(String username) {
        var headers = new HttpHeaders();
        headers.setAcceptLanguageAsLocales(List.of(Locale.FRENCH));
        var url = UriComponentsBuilder.fromHttpUrl(trainerServiceUrl).path(TRAINER_ROOT).path(username);
        var httpRequest = new HttpEntity<>(headers);
        return requireNonNull(
                restTemplate.getForObject(
                        url.toUriString(),
                        Trainer.class,
                        httpRequest)
        );
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String trainerServiceUrl){
        this.trainerServiceUrl = trainerServiceUrl;
    }

}
