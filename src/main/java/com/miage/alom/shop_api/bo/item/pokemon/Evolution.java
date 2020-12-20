package com.miage.alom.shop_api.bo.item.pokemon;

import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Evolution extends ConsommablePokemonItem {

    @Override
    public int price() {
        return 15_000;
    }

    @Override
    Pair<Trainer, Pokemon> describeEffect() {
        var maxEvolution = EvolutionEnum.findPalier(pokemon.getPokemonTypeId());
        if(pokemon.getPokemonTypeId() < maxEvolution){
            pokemon.setPokemonTypeId(pokemon.getPokemonTypeId()+1);
        }else {
            var exp = pokemon.getExperience();
            var currentLevel = (int)Math.cbrt(exp);
            pokemon.setExperience((int) Math.pow(currentLevel + 3, 3));
        }
        return Pair.of(trainer,pokemon);
    }

    enum EvolutionEnum{
        PALIER2(2,
                List.of(1,4,7,10,13,16,29,32,43,60,63,66,69,74,92)),
        PALIER1(1,
                List.of(19,21,23,25,27,35,37,39,41,46,48,50,52,54,56,58,72,77,79,81,84,86,88,90,96,98,100,102,104,106,109,111,116,118,120,129,138,140,147))
        ;

        Integer nbUp;
        List<Integer> pokemonId;

        EvolutionEnum(Integer nbUp, List<Integer> pokemonId) {
            this.nbUp = nbUp;
            this.pokemonId = pokemonId;
        }

        static Integer findPalier(int id) {
            return Arrays.stream(values())
                    .filter(evolutionEnum -> evolutionEnum.pokemonId != null
                            && evolutionEnum.pokemonId.stream().anyMatch(pkId -> id == pkId || (id > pkId && id < (pkId + evolutionEnum.nbUp))))
                    .map(evolutionEnum -> id+evolutionEnum.nbUp)
                    .findFirst()
                    .orElse(0);
        }
    }

}
