package com.miage.alom.shop_api;

import com.miage.alom.shop_api.bo.Portefeuille;
import com.miage.alom.shop_api.repository.TrainerShopRepository;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ShopApi {
    public static void main(String... args){
        SpringApplication.run(ShopApi.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner demo(TrainerShopRepository repository) {
        return (args) -> {
            /*on met à jour le portefeuille de tous nos trainer deja presents*/
            var trainers = repository.findAll();
            trainers.forEach(trainer -> {
                if(trainer.getPortefeuille() == null) {
                    var portefeuille = new Portefeuille();
                    trainer.setPortefeuille(portefeuille);
                    portefeuille.approvisionnement(20_000);
                }
            });
            repository.saveAll(trainers);
        };
    }
}
