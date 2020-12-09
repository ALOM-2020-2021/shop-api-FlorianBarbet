package com.miage.alom.shop_api.bo.item.trainer;

import com.miage.alom.shop_api.bo.Portefeuille;
import com.miage.alom.shop_api.conf.BallConfiguration;
import com.miage.alom.shop_api.exception.NotEnoughtFundException;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {
        BallConfiguration.class,
        HyperBall.class,
        MasterBall.class,
        PokeBall.class,
        SuperBall.class
})
@SpringBootTest
abstract class BallTrainerItemTest {

    Trainer ash;

    @BeforeEach
    protected void setup(){
        ash = new Trainer();
        ash.setBuyedMasterBall(false);
        ash.setName("Ash");

        var portefeuille = new Portefeuille();
        portefeuille.setSolde(10_000);

        ash.setPortefeuille(portefeuille);

        var pikachu = new Pokemon();
        pikachu.setExperience(1);
        pikachu.setPokemonTypeId(25);
        var pokemons = new ArrayList<Pokemon>();
        pokemons.add(pikachu);
        ash.setTeam(pokemons);
    }

    @Test
    protected void should_get_a_random_pokemon_from_pokemons_referenced_in_beans(){
        //Given
        var ball =  getInstance();
        ball.load(ash);
        var loto = ball.getPokemons();
        var team = ash.getTeam();
        var expected = team.size()+1;
        var portefeuille = ash.getPortefeuille();
        portefeuille.setSolde(ball.price());
        //When
        ball.applyEffect();
        //Then
        assertEquals(expected,team.size());
        assertTrue(team.stream().anyMatch(loto::contains));
    }

    @Test
    protected void should_reduce_solde_when_buying_ball(){
        //Given
        var ball =  getInstance();
        ball.load(ash);
        var prix = ball.price();
        var team = ash.getTeam();
        var expectedSize = team.size()+1;
        var portefeuille = ash.getPortefeuille();
        portefeuille.setSolde(prix);
        //When
        ball.applyEffect();
        //Then
        assertEquals(expectedSize,team.size());
        assertEquals(0,portefeuille.getSolde());
    }

    @Test
    protected void shoud_throw_exception_when_trainer_is_unable_to_buy_article(){
        var ball = getInstance();
        ball.load(ash);

        var portefeuille = ash.getPortefeuille();
        portefeuille.setSolde(ball.price()-1);

        assertThrows(NotEnoughtFundException.class, ball::applyEffect);

        portefeuille.setSolde(0);
        assertThrows(NotEnoughtFundException.class, ball::applyEffect);
    }

    protected abstract BallTrainerItem getInstance();

}