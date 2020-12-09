package com.miage.alom.shop_api.bo.item.trainer;

import com.miage.alom.shop_api.bo.item.trainer.BallTrainerItem;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokeBall extends BallTrainerItem {

    List<Pokemon> pokeballs;

    @Override
    public int price() {
        return 10_000;
    }

    @Autowired
    void setPokeBall(List<Pokemon> pokeballs) {
        this.pokeballs = pokeballs;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return this.pokeballs;
    }
}
