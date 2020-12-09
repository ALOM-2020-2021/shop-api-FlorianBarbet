package com.miage.alom.shop_api.service;

import com.miage.alom.shop_api.bo.Portefeuille;
import com.miage.alom.shop_api.repository.TrainerShopRepository;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {
        ShopServiceImpl.class
})
class ShopServiceTest {

    ShopServiceImpl shopService;
    TrainerShopRepository trainerShopRepository;

    @BeforeEach
    void setup(){
        shopService = new ShopServiceImpl();
        trainerShopRepository = mock(TrainerShopRepository.class);
        shopService.setTrainerShopRepository(trainerShopRepository);

    }

    @Test
    void should_approvisionne_trainer_is_valid(){
        //Given
        var trainer = new Trainer();
        var portefeuille = new Portefeuille();
        var expected = 10_000;
        trainer.setName("Polly");
        portefeuille.setSolde(expected);
        trainer.setPortefeuille(portefeuille);

        //When
        when(trainerShopRepository.save(trainer)).thenReturn(trainer);
        assertEquals(expected,trainer.getPortefeuille().getSolde());
        assertEquals(true,shopService.approvisionner(trainer,expected));

        //Then
        assertEquals(expected*2,trainer.getPortefeuille().getSolde());
    }


    @Autowired
    public void setShopService(ShopServiceImpl shopService) {
        this.shopService = shopService;
    }
}