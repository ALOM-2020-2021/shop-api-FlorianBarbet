package com.miage.alom.shop_api.bo.item.pokemon.stone;

import com.miage.alom.shop_api.bo.item.pokemon.ElementStone;
import com.miage.alom.shop_api.bo.item.pokemon.ElementStoneTest;

public class ElectricStoneTest extends ElementStoneTest {
    @Override
    protected ElementStone getInstanceElementStone() {
        return new ElectricStone();
    }
}
