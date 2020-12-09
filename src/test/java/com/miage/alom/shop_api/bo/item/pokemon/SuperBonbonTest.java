package com.miage.alom.shop_api.bo.item.pokemon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperBonbonTest extends ConsommablePokemonItemTest{

    @Override
    ConsommablePokemonItem getInstance() {
        return new SuperBonbon();
    }

    @Test
    void should_adding_a_lvl_to_pokemon(){
        var consommable = getInstance();
        consommable.load(ash,pikachu);
        var portefeuille = ash.getPortefeuille();
        portefeuille.setSolde(consommable.price());

        var currentLvl = (int)Math.cbrt(pikachu.getExperience());

        consommable.applyEffect();

        var newLvl = (int)Math.cbrt(pikachu.getExperience());

        assertEquals(currentLvl+1,newLvl);
    }



}