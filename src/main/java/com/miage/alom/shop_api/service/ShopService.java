package com.miage.alom.shop_api.service;

import com.miage.alom.shop_api.bo.item.Item;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.stereotype.Service;


public interface ShopService {
    Trainer getTrainerShop(String trainerName);
    Boolean approvisionner(Trainer trainer,Integer addingAmount);
    Trainer saveTrainer(Trainer trainer);
    <E> void buyItem(Item<E> item,E target);
}
