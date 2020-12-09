package com.miage.alom.shop_api.exception;

public class MasterBallAlreadyBuyedException extends IllegalArgumentException{
    public MasterBallAlreadyBuyedException(String s) {
        super(s);
    }
}
