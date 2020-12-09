package com.miage.alom.shop_api.trainer.bo;

import javax.persistence.Embeddable;

@Embeddable
public class Pokemon {

    int pokemonTypeId;

    int experience;

    public Pokemon(){
    }

    public Pokemon(int pokemonTypeId, int experience) {
        this.pokemonTypeId = pokemonTypeId;
        this.experience = experience;
    }

    public int getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(int pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int level) {
        this.experience = level;
    }

    public int level(){
        return (int)Math.cbrt(experience);
    }
}
