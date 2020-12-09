package com.miage.alom.shop_api.listener.trainer;


import com.miage.alom.shop_api.trainer.bo.Trainer;

public class TrainerEvent {

    EventEnum type;
    Trainer trainer;

    public EventEnum getType() {
        return type;
    }

    public void setType(EventEnum type) {
        this.type = type;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String topic(){
        return type.topic();
    }

    public TrainerEvent() {
    }
}
