package com.miage.alom.shop_api.repository;

import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainerShopRepository extends CrudRepository<Trainer, String> {
    
}
