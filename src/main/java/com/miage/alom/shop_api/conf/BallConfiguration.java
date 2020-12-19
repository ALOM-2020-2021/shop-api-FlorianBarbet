package com.miage.alom.shop_api.conf;

import com.miage.alom.shop_api.trainer.bo.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class BallConfiguration {

    List<Integer> masterballs;
    List<Integer> hyperballs;
    List<Integer> superballs;
    List<Integer> pokeballs;
    List<Integer> starters;

    @Bean
    List<Pokemon> masterballs(){
        return converter(
                this.masterballs,
                experience(40)
        );
    }

    @Bean
    List<Pokemon> hyperballs(){
        return converter(
                this.hyperballs,
                experience(20)
        );
    }

    @Bean
    List<Pokemon> superballs(){
        return converter(
                this.superballs,
                experience(10)
        );
    }

    @Bean
    List<Pokemon> pokeballs(){
        return converter(
                this.pokeballs,
                experience(5)
        );
    }

    @Bean
    List<Pokemon> starter(){
        return converter(
                this.starters,
                experience(1)
        );
    }

    List<Pokemon> converter(
            List<Integer> pokemonIds,
            final Integer baseLvl
    ){
        return pokemonIds.stream().map(id -> {
            var pokemon = new Pokemon();
            pokemon.setPokemonTypeId(id);
            pokemon.setExperience(baseLvl);
            return pokemon;
        }).collect(Collectors.toList());
    }

    int experience(int level){
        return (int)Math.pow(level,3);
    }

    @Value("${pokemon.masterballs.id}")
    public void setMasterballs(List<Integer> masterballs) {
        this.masterballs = masterballs;
    }

    @Value("${pokemon.hyperballs.id}")
    public void setHyperballs(List<Integer> hyperballs) {
        this.hyperballs = hyperballs;
    }

    @Value("${pokemon.superballs.id}")
    public void setSuperballs(List<Integer> superballs) {
        this.superballs = superballs;
    }
    @Value("${pokemon.pokeballs.id}")
    public void setPokeballs(List<Integer> pokeballs) {
        this.pokeballs = pokeballs;
    }
    @Value("${pokemon.starters.id}")
    public void setStarters(List<Integer> starters) {
        this.starters = starters;
    }
}
