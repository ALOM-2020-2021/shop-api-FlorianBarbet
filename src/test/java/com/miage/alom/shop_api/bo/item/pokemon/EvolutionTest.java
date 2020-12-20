package com.miage.alom.shop_api.bo.item.pokemon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EvolutionTest extends ConsommablePokemonItemTest{

    @Override
    ConsommablePokemonItem getInstance() {
        return new Evolution();
    }

    @Test
    void should_adding_evolution_a_pokemon(){
        var consommable = getInstance();
        consommable.load(ash,pikachu);
        var portefeuille = ash.getPortefeuille();
        portefeuille.setSolde(consommable.price());

        var currentId = pikachu.getPokemonTypeId();

        consommable.applyEffect();

        var newId = pikachu.getPokemonTypeId();

        assertEquals(currentId+1,newId);
    }



}