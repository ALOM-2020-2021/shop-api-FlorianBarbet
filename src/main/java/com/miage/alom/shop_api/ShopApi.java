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
            var ash = repository.findById("Ash").orElse(new Trainer("Ash"));
            var ash_portefeuille = new Portefeuille();
            ash.setPortefeuille(ash_portefeuille);
            ash_portefeuille.approvisionnement(20_000);

            var misty = repository.findById("Misty").orElse(new Trainer("Misty"));
            var misty_portefeuille = new Portefeuille();
            misty.setPortefeuille(misty_portefeuille);
            misty_portefeuille.approvisionnement(20_000);

            // save a couple of trainers
            repository.save(ash);
            repository.save(misty);
        };
    }
}
