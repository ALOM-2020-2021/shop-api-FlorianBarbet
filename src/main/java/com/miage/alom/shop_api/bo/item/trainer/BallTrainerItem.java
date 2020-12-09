package com.miage.alom.shop_api.bo.item.trainer;

import com.miage.alom.shop_api.bo.item.trainer.TrainerItem;
import com.miage.alom.shop_api.exception.NotEnoughtFundException;
import com.miage.alom.shop_api.exception.TypeNeverLoadException;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public abstract class BallTrainerItem implements TrainerItem {


    Trainer trainer;

    public abstract  List<Pokemon> getPokemons();

    Pokemon randomize(){
        var pokemonList = getPokemons();
        int randomElementIndex
                = ThreadLocalRandom.current().nextInt(pokemonList.size()) % pokemonList.size();
        return pokemonList.get(randomElementIndex);
    }

    @Override
    public Trainer applyEffect() {
        if(trainer == null)
            throw new TypeNeverLoadException("Trainer never been set to apply this effect.");
        var portefeuille = trainer.getPortefeuille();

        if(portefeuille.getSolde()<=0)
            throw new NotEnoughtFundException("Your sold is really low");

        var prix = price();

        if(portefeuille.getSolde() < prix)
            throw new NotEnoughtFundException("Your sold can't handle the price of this article");

        var team = trainer.getTeam();

        portefeuille.achat(prix);
        team.add(randomize());

        return trainer;
    }

    @Override
    public void load(Trainer trainer) {
        this.trainer = trainer;
    }
}
