package com.miage.alom.shop_api.bo.item.pokemon;

import com.miage.alom.shop_api.exception.DontHaveEvoliInTeamException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ElementStoneTest extends ConsommablePokemonItemTest{

    @BeforeEach
    @Override
    void setup() {
        super.setup();
        pikachu.setPokemonTypeId(133); /*Pikachu se transforme en Evoli pour ces tests bien sur ;)*/
    }

    @Override
    ConsommablePokemonItem getInstance() {
        return getInstanceElementStone();
    }

    @Test
    void should_adding_evolution_of_evoli(){
        var consommable = getInstance();
        consommable.load(ash,pikachu);
        var portefeuille = ash.getPortefeuille();
        portefeuille.setSolde(consommable.price());

        var oldId = pikachu.getPokemonTypeId();

        consommable.applyEffect();

        var newId = pikachu.getPokemonTypeId();

        assertEquals(stoneType(),newId);
        assertNotEquals(stoneType(),oldId);
    }

    @Test
    void should_get_exception_if_there_is_not_evoli(){
        pikachu.setPokemonTypeId(25);/*Pika pika!*/
        var consommable = getInstance();
        consommable.load(ash,pikachu);
        var portefeuille = ash.getPortefeuille();
        portefeuille.setSolde(consommable.price());

        assertThrows(DontHaveEvoliInTeamException.class, consommable::applyEffect);

    }

    int stoneType(){
        return getInstanceElementStone().stoneType();
    }

    protected abstract ElementStone getInstanceElementStone();

}