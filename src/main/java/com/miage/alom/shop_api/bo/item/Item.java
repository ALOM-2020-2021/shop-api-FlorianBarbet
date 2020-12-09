package com.miage.alom.shop_api.bo.item;

public interface Item<T> {
    int price();
    T applyEffect();
    void load(T toLoad);
}
