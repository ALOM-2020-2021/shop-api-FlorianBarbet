package com.miage.alom.shop_api.bo.item.trainer;

import org.springframework.beans.factory.annotation.Autowired;

class PokeBallTest extends BallTrainerItemTest {

    PokeBall pokeBall;

    @Override
    public BallTrainerItem getInstance() {
        return this.pokeBall;
    }

    @Autowired
    public void setPokeBall(PokeBall pokeBall) {
        this.pokeBall = pokeBall;
    }
}