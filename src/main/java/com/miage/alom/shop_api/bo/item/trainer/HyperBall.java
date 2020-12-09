package com.miage.alom.shop_api.bo.item.trainer;

import com.miage.alom.shop_api.bo.item.trainer.BallTrainerItem;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HyperBall extends BallTrainerItem {

    List<Pokemon> hyperballs;

    @Override
    public int price() {
        return 50_000;
    }

    @Autowired
    void setHyperballs(List<Pokemon> hyperballs){
        this.hyperballs = hyperballs;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return this.hyperballs;
    }
}
