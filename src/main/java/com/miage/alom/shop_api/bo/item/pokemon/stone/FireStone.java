package com.miage.alom.shop_api.bo.item.pokemon.stone;

import com.miage.alom.shop_api.bo.item.pokemon.ElementStone;
import org.springframework.stereotype.Component;

@Component
public class FireStone extends ElementStone {
    @Override
    public int price() {
        return 75_000;/*On augmenter le prix du favori c'est plus rentable :D*/
    }

    @Override
    protected int stoneType() {
        return 136;
    }
}
