package com.miage.alom.shop_api.controller;

import com.miage.alom.shop_api.bo.item.Item;
import com.miage.alom.shop_api.bo.item.ReferentielEnum;
import com.miage.alom.shop_api.service.ShopService;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import com.miage.alom.shop_api.trainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller("/shop")
public class ShopController {

    ShopService shopService;
    TrainerService trainerService;

    @PutMapping({"/{name}/","/{name}"})
    public Boolean approvisionner(
            @PathVariable("name") String name,
            @RequestBody Integer addingAmount
    ){
        var trainer = trainerService.getTrainer(name);

        return shopService.approvisionner(trainer,addingAmount);
    }

    @GetMapping({"","/"})
    public List<Pair<String, Pair<String,Integer>>> listProducts(){
        return ReferentielEnum.getReferentiel();
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}
