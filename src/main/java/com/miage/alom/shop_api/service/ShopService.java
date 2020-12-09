package com.miage.alom.shop_api.service;

import com.miage.alom.shop_api.bo.item.Item;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.stereotype.Service;


public interface ShopService {

    Boolean approvisionner(Trainer trainer,Integer addingAmount);
    <E> void buyItem(Item<E> item,E target);
}
