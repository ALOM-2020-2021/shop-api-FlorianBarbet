package com.miage.alom.shop_api.bo.item.trainer;

import org.springframework.beans.factory.annotation.Autowired;

class SuperBallTest extends BallTrainerItemTest {

    SuperBall superBall;

    @Override
    public BallTrainerItem getInstance() {
        return this.superBall;
    }

    @Autowired
    public void setSuperBall(SuperBall superBall) {
        this.superBall = superBall;
    }
}