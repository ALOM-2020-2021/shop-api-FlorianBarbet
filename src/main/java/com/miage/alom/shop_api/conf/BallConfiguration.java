package com.miage.alom.shop_api.conf;

import com.miage.alom.shop_api.trainer.bo.Pokemon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class BallConfiguration {

    @Bean
    List<Pokemon> masterballs(){
        return converter(
                List.of(144,145,146,150,151),
                experience(40)
        );
    }

    @Bean
    List<Pokemon> hyperballs(){
        return converter(
                List.of(147,58,74,95,77,37,109,27,126,63,25,125,66,88,111,100,108,123,127,114,138,140),
                experience(20)
        );
    }

    @Bean
    List<Pokemon> superballs(){
        return converter(
                List.of(35,32,29,23,104,118,60,90,39,81,92,102,79,54,124,120,72,132),
                experience(10)
        );
    }

    @Bean
    List<Pokemon> pokeballs(){
        return converter(
                List.of(10,13,16,19,41,133,48,43,129,96,52,21,69,46,98,116),
                experience(5)
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
}
