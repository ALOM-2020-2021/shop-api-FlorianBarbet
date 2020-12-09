package com.miage.alom.shop_api.listener.trainer;


import com.miage.alom.shop_api.trainer.bo.Trainer;

import static com.miage.alom.shop_api.listener.trainer.EventEnum.CREATED;

public class TrainerCreatedEvent extends TrainerEvent {
    public TrainerCreatedEvent(Trainer trainer){
        super();
        this.setTrainer(trainer);
        super.setType(CREATED);
    }

    public TrainerCreatedEvent() {
    }
}
