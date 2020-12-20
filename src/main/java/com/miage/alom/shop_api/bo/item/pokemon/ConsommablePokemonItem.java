package com.miage.alom.shop_api.bo.item.pokemon;

import com.miage.alom.shop_api.exception.NotEnoughtFundException;
import com.miage.alom.shop_api.exception.TypeNeverLoadException;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.data.util.Pair;

public abstract class ConsommablePokemonItem implements PokemonItem {
    Pokemon pokemon;
    Trainer trainer;

    @Override
    public Pair<Trainer,Pokemon> applyEffect() {
        required();
        return describeEffect();
    }

    protected void required(){
        if(pokemon == null || trainer == null )
            throw new TypeNeverLoadException("Pokemon or/and Trainer never set to apply this effect");

        var portefeuille = trainer.getPortefeuille();

        if(portefeuille.getSolde()<=0)
            throw new NotEnoughtFundException("Your sold is really low");

        var prix = price();

        if(portefeuille.getSolde() < prix)
            throw new NotEnoughtFundException("Your sold can't handle the price of this article");

        specificRequirement();

        portefeuille.achat(prix);
    }

    void specificRequirement(){
        /*Here to be overwritten*/
    }

    @Override
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public void load(Trainer trainer, Pokemon pokemon) {
        setTrainer(trainer);
        setPokemon(pokemon);
    }

    @Override
    public void load(Pair<Trainer,Pokemon> toLoad){
        load(toLoad.getFirst(),toLoad.getSecond());
    }

    abstract Pair<Trainer,Pokemon> describeEffect();
}
