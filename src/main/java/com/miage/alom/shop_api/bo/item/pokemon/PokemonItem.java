package com.miage.alom.shop_api.bo.item.pokemon;

import com.miage.alom.shop_api.bo.item.Item;
import com.miage.alom.shop_api.trainer.bo.Pokemon;
import com.miage.alom.shop_api.trainer.bo.Trainer;
import org.springframework.data.util.Pair;

public interface PokemonItem extends Item<Pair<Trainer,Pokemon>> {
    void setPokemon(Pokemon pokemon);
    void setTrainer(Trainer trainer);
    void load(Trainer trainer,Pokemon pokemon);
}
