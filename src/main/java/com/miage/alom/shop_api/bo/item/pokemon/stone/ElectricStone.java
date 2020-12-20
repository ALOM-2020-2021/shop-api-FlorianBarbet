package com.miage.alom.shop_api.bo.item.pokemon.stone;

import com.miage.alom.shop_api.bo.item.pokemon.ElementStone;
import org.springframework.stereotype.Component;

@Component
public class ElectricStone extends ElementStone {
    @Override
    protected int stoneType() {
        return 135;
    }
}
