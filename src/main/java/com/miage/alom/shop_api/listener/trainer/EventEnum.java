package com.miage.alom.shop_api.listener.trainer;

import java.util.Arrays;

public enum EventEnum {
    CREATED(TrainerCreatedEvent.class,"trainer.event.created"),
    ;

    Class<?> eventClass;
    String topic;

    EventEnum(
            Class<?> eventClass,
            String topic
    ){
        this.eventClass = eventClass;
        this.topic = topic;
    }

    public String topic(){
        return this.topic;
    }
}
