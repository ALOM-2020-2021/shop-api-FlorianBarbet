package com.miage.alom.shop_api.bo.item.pokemon;

import com.miage.alom.shop_api.bo.item.Item;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class SuperBonbon extends ConsommablePokemonItem {

    @Override
    public int price() {
        return 5_000;
    }

    @Override
    Pair<Trainer, Pokemon> describeEffect() {
        var exp = pokemon.getExperience();
        var currentLevel = (int)Math.cbrt(exp);

        pokemon.setExperience((int)Math.pow(currentLevel+1,3));
        return Pair.of(trainer,pokemon);
    }

}
