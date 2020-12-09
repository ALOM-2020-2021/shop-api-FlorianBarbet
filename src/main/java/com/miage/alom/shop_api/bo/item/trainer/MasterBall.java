package com.miage.alom.shop_api.bo.item.trainer;

import com.miage.alom.shop_api.bo.item.trainer.BallTrainerItem;
import com.miage.alom.shop_api.exception.MasterBallAlreadyBuyedException;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MasterBall extends BallTrainerItem {
    List<Pokemon> masterballs;

    @Override
    public int price() {
        return 100_000;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return masterballs;
    }

    @Override
    public Trainer applyEffect() {
        if(trainer.getBuyedMasterBall())
            throw new MasterBallAlreadyBuyedException("Had already buy a masterball !");
        return super.applyEffect();
    }

    @Autowired
    void setMasterBall(List<Pokemon> masterballs){
        this.masterballs = masterballs;
    }
}
