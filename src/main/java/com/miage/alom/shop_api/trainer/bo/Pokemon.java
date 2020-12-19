package com.miage.alom.shop_api.trainer.bo;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon)) return false;
        Pokemon pokemon = (Pokemon) o;
        return getPokemonTypeId() == pokemon.getPokemonTypeId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPokemonTypeId());
    }
}
