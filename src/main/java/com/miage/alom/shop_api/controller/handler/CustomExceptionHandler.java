package com.miage.alom.shop_api.controller.handler;

import com.miage.alom.shop_api.exception.DontHaveEvoliInTeamException;
import com.miage.alom.shop_api.exception.MasterBallAlreadyBuyedException;
import com.miage.alom.shop_api.exception.NotEnoughtFundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            MasterBallAlreadyBuyedException.class,
            NotEnoughtFundException.class,
            DontHaveEvoliInTeamException.class
    })
    public ResponseEntity<String> handleMasterBallAndNotEnoghFund(
            Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}