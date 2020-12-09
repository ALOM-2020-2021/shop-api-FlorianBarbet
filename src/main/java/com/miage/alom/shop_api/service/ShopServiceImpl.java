package com.miage.alom.shop_api.service;

import com.miage.alom.shop_api.bo.item.Item;
import com.miage.alom.shop_api.repository.TrainerShopRepository;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService{

    TrainerShopRepository trainerShopRepository;

    @Override
    public Boolean approvisionner(Trainer trainer,Integer addingAmount) {
        var portefeuille = trainer.getPortefeuille();
        portefeuille.approvisionnement(addingAmount);

        var trainerInDB = trainerShopRepository.save(trainer);
        var portefeuilleInDB = trainerInDB.getPortefeuille();

        return portefeuilleInDB.equals(portefeuille);
    }

    @Override
    public <E> void buyItem(Item<E> item,E target){
        item.load(target);
        item.applyEffect();
    }

    @Autowired
    public void setTrainerShopRepository(TrainerShopRepository trainerShopRepository) {
        this.trainerShopRepository = trainerShopRepository;
    }
}
