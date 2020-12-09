package com.miage.alom.shop_api.bo.item.trainer;


import com.miage.alom.shop_api.trainer.bo.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuperBall extends BallTrainerItem {

    List<Pokemon> superballs;

    @Autowired
    void setSuperBall(List<Pokemon> superballs){
      this.superballs = superballs;
    }

    @Override
    public int price() {
        return 25_000;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return this.superballs;
    }
}
