package com.miage.alom.shop_api.bo.item.pokemon;

import com.miage.alom.shop_api.exception.DontHaveEvoliInTeamException;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public abstract class ElementStone extends ConsommablePokemonItem {

    @Override
    public int price() {
        return 50_000;
    }

    @Override
    Pair<Trainer, Pokemon> describeEffect() {
        pokemon.setPokemonTypeId(stoneType());
        return Pair.of(trainer,pokemon);
    }

    @Override
    void specificRequirement() {
        super.specificRequirement();
        if (pokemon == null || pokemon.getPokemonTypeId() != 133) {
                throw new DontHaveEvoliInTeamException("You don't get us Evoli, maybe you don't get it, try to buy some pokeballs !");
        }
    }

    protected abstract int stoneType();
}
