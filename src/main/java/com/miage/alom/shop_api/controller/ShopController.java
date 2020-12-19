package com.miage.alom.shop_api.controller;

import com.miage.alom.shop_api.bo.ItemUI;
import com.miage.alom.shop_api.bo.item.Item;
import com.miage.alom.shop_api.bo.item.ReferentielEnum;
import com.miage.alom.shop_api.bo.item.pokemon.ConsommablePokemonItem;
import com.miage.alom.shop_api.bo.item.trainer.BallTrainerItem;
import com.miage.alom.shop_api.service.ShopService;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import com.miage.alom.shop_api.trainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    ApplicationContext applicationContext;

    ShopService shopService;
    TrainerService trainerService;
    List<Pokemon> starter;

    @PutMapping({"/{name}/","/{name}"})
    public Boolean approvisionner(
            @PathVariable("name") String name,
            @RequestBody Integer addingAmount
    ){
        var trainer = trainerService.getTrainer(name);

        return shopService.approvisionner(trainer,addingAmount);
    }


    @GetMapping({"","/"})
    public List<ItemUI> listProducts(){
        return ReferentielEnum.getReferentiel();
    }

    @GetMapping("/starter")
    public List<Pokemon> getStarters(){
        return starter;
    }

    @PutMapping({"/{item}","/{item}/"})
    public Boolean buyItem(
            @PathVariable("item") String item,
            @RequestBody Trainer trainer,
            @RequestParam(value = "pokemon_id",required = false)
                String pokemonId
    ){
        if(trainer != null && trainer.getTeam() != null ) {
            var itemRef = ReferentielEnum.fromName(item);
            var itemBean = applicationContext.getBean(item,itemRef.getType());

            if (pokemonId != null) {
                var parsedPokemonId = Integer.parseInt(pokemonId);
                var team = trainer.getTeam();
                var pokemon = team.stream().filter(pk -> parsedPokemonId == pk.getPokemonTypeId()).findFirst();
                pokemon.ifPresent(value -> shopService.buyItem((ConsommablePokemonItem)itemBean, Pair.of(trainer, value)));
                return true;
            }
            shopService.buyItem((BallTrainerItem) itemBean,trainer);
            return true;
        }
        return false;
    }

    @Autowired
    public void setStarter(List<Pokemon> starter) {
        this.starter = starter;
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
