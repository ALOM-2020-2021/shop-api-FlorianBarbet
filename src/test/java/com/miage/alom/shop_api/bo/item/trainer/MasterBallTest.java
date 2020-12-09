package com.miage.alom.shop_api.bo.item.trainer;

import com.miage.alom.shop_api.exception.MasterBallAlreadyBuyedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MasterBallTest extends BallTrainerItemTest {

    MasterBall masterBall;

    @Override
    public BallTrainerItem getInstance() {
        return this.masterBall;
    }

    @Test
    public void should_throw_exception_if_trainer_already_buyed_master_ball(){
        var ball = getInstance();
        ball.load(ash);

        ash.setBuyedMasterBall(true);

        assertThrows(MasterBallAlreadyBuyedException.class, ball::applyEffect);
    }

    @Autowired
    public void setMasterBall(MasterBall masterBall) {
        this.masterBall = masterBall;
    }
}