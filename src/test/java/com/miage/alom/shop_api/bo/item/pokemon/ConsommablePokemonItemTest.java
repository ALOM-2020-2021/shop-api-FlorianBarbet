package com.miage.alom.shop_api.bo.item.pokemon;

import com.miage.alom.shop_api.bo.Portefeuille;
import com.miage.alom.shop_api.bo.item.trainer.HyperBall;
import com.miage.alom.shop_api.bo.item.trainer.MasterBall;
import com.miage.alom.shop_api.bo.item.trainer.PokeBall;
import com.miage.alom.shop_api.bo.item.trainer.SuperBall;
import com.miage.alom.shop_api.conf.BallConfiguration;
import com.miage.alom.shop_api.exception.NotEnoughtFundException;
import com.miage.alom.shop_api.exception.TypeNeverLoadException;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class ConsommablePokemonItemTest {

    Pokemon pikachu;
    Trainer ash;

    @BeforeEach
    void setup(){
        pikachu = new Pokemon();
        pikachu.setExperience((int)Math.pow(5,3));
        pikachu.setPokemonTypeId(25);

        ash = new Trainer();
        ash.setName("Ash");
        ash.setPortefeuille(new Portefeuille());

        ash.setTeam(new ArrayList<>());
        ash.getTeam().add(pikachu);


    }

    @Test
    void should_check_if_trainer_can_pay(){
        var consommable = getInstance();
        var portefeuille = ash.getPortefeuille();

        consommable.load(ash,pikachu);

        portefeuille.setSolde(0);
        assertThrows(NotEnoughtFundException.class, consommable::applyEffect);

        portefeuille.setSolde(consommable.price()-1);
        assertThrows(NotEnoughtFundException.class, consommable::applyEffect);
    }

    @Test
    void should_check_if_pokemon_and_trainer_is_loaded(){
        var consommable = getInstance();
        consommable.load(ash,null);
        assertThrows(TypeNeverLoadException.class, consommable::applyEffect);
        consommable.load(null,pikachu);
        assertThrows(TypeNeverLoadException.class, consommable::applyEffect);
    }

    @Test
    void should_decrease_trainer_s_fund_at_applying_effect(){
        //Given
        var consommable = getInstance();
        var portefeuille = ash.getPortefeuille();

        consommable.load(ash,pikachu);
        portefeuille.setSolde(consommable.price());
        //When
        consommable.applyEffect();
        //then
        assertEquals(0,portefeuille.getSolde());
    }

    abstract ConsommablePokemonItem getInstance();
}