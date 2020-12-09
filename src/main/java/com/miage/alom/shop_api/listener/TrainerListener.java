package com.miage.alom.shop_api.listener;

import com.miage.alom.shop_api.bo.Portefeuille;
import com.miage.alom.shop_api.listener.trainer.TrainerCreatedEvent;
import com.miage.alom.shop_api.repository.TrainerShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainerListener {

    private static final Logger log = LoggerFactory.getLogger(TrainerListener.class);

    TrainerShopRepository trainerShopRepository;

    @RabbitListener(queues = "trainer-created")
    void welcomeNewTrainer(TrainerCreatedEvent trainerCreatedEvent){

        var trainer = trainerCreatedEvent.getTrainer();

        log.info("Welcome to {} your sold up to 10K !",trainer);

        trainerShopRepository.findById(trainer.getName()).ifPresent(tr ->{
            var portefeuille = new Portefeuille();
            portefeuille.approvisionnement(10_000);
            tr.setPortefeuille(portefeuille);
            trainerShopRepository.save(tr);
        });
        /*TODO websocket to offer one of theses 3 pokemons : 1/4/7 */
    }

    @Autowired
    public void setTrainerShopRepository(TrainerShopRepository trainerShopRepository) {
        this.trainerShopRepository = trainerShopRepository;
    }
}
