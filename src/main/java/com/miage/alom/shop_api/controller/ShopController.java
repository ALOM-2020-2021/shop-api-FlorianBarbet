package com.miage.alom.shop_api.controller;

import com.miage.alom.shop_api.bo.ItemUI;
import com.miage.alom.shop_api.bo.Portefeuille;
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
    /*Je vais me servir de cette methode pour recuperer le portefeuille de mes utilisateurs*/
    @PutMapping({"/solde","/solde/"})
    public Integer approvisionner(
            @RequestParam("name") String name,
            @RequestParam(value = "amount",required = false) Integer addingAmount
    ){

        var trainer = shopService.getTrainerShop(name);
        if(trainer == null) return 0;

        var portefeuille = trainer.getPortefeuille();
        if(addingAmount != null && addingAmount > 0) /*si on traite ici ça evite quelques traitements*/
            shopService.approvisionner(trainer,addingAmount);

        return portefeuille.getSolde();
    }

    @GetMapping({"","/"})
    public List<ItemUI> listProducts(
            @RequestParam("name") String name
    ){
        var trainer = shopService.getTrainerShop(name);
        return ReferentielEnum.getReferentiel(trainer);
    }

    @GetMapping("/starter")
    public List<Pokemon> getStarters(){
        return starter;
    }

    @PutMapping({"/buy/{item}","/buy/{item}/"})
    public Boolean buyItem(
            @PathVariable("item") String item,
            @RequestParam(value = "trainer") String trainerBase,
            /*
            Je suis pas sur niveau secu si c'est ok comme ca..
            Je sais que c'est pas un cours de secu mais je me dis que peut etre un token à la place du nom ça aiderai,
            Si jamais j'aimerai bien un feedback ça m'interesse :)
            */
            @RequestParam(value = "pokemon_id",required = false)
                String pokemonId
    ){
        var trainer = shopService.getTrainerShop(trainerBase);/*On veut recuperer nos donnees de trainer*/
        if(trainer != null && trainer.getTeam() != null ) {
            var itemRef = ReferentielEnum.fromName(item);
            var itemBean = applicationContext.getBean(itemRef.getType());

            if (pokemonId != null) {
                var parsedPokemonId = Integer.parseInt(pokemonId);
                var team = trainer.getTeam();
                var pokemon = team.stream().filter(pk -> parsedPokemonId == pk.getPokemonTypeId()).findFirst();
                pokemon.ifPresent(value -> {
                    shopService.buyItem((ConsommablePokemonItem)itemBean, Pair.of(trainer, value));
                    shopService.saveTrainer(trainer);
                });
                return pokemon.isPresent();
            }
            shopService.buyItem((BallTrainerItem) itemBean,trainer);
            shopService.saveTrainer(trainer);
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
