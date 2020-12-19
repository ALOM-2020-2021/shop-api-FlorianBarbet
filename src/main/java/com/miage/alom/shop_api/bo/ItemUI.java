package com.miage.alom.shop_api.bo;

public class ItemUI {
    String name;
    String description;
    String image;
    int price;
    boolean isPokemonConsommable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPokemonConsommable() {
        return isPokemonConsommable;
    }

    public void setPokemonConsommable(boolean pokemonConsommable) {
        isPokemonConsommable = pokemonConsommable;
    }
}
