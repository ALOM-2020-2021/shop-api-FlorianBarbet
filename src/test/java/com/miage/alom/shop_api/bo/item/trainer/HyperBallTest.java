package com.miage.alom.shop_api.bo.item.trainer;

import org.springframework.beans.factory.annotation.Autowired;


class HyperBallTest extends BallTrainerItemTest {

    HyperBall hyperBall;

    @Override
    public BallTrainerItem getInstance() {
        return this.hyperBall;
    }

    @Autowired
    public void setHyperBall(HyperBall hyperBall) {
        this.hyperBall = hyperBall;
    }
}