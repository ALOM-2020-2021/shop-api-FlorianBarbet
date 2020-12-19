package com.miage.alom.shop_api.bo.item;

import com.miage.alom.shop_api.bo.ItemUI;
import com.miage.alom.shop_api.bo.item.pokemon.SuperBonbon;
import com.miage.alom.shop_api.bo.item.trainer.HyperBall;
import com.miage.alom.shop_api.bo.item.trainer.MasterBall;
import com.miage.alom.shop_api.bo.item.trainer.PokeBall;
import com.miage.alom.shop_api.bo.item.trainer.SuperBall;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
On aurait pu utiliser une base de données
Mais vue le nombre de données je ne penses pas que cela aurait été très optimisé
* */
public enum ReferentielEnum {

    SUPERBONBON(SuperBonbon.class,"Augmente un niveau d'un pokemon au choix !"),
    POKEBALL(PokeBall.class,
            "Deviens le meilleur dresseur et obtiens un pokemon commun de niveau 5 au hasard !",
            "https://www.flaticon.com/svg/static/icons/svg/188/188918.svg"),
    SUPERBALL(SuperBall.class,
            "Super un pokemon de niveau 10 non-commun arrive !",
            "https://www.flaticon.com/svg/static/icons/svg/188/188916.svg"),
    HYPERBALL(HyperBall.class,
            "Hyperball, t'es adresseurs n'ont plus aucune chance avec ton futur pokemon rare niveau 20",
            "https://www.flaticon.com/svg/static/icons/svg/188/188954.svg"),
    MASTERBALL(MasterBall.class,
            "Usage unique, deviens une legende en obtenant l'un des pokemons legendaire de ta generation.. En plus il est niveau 40",
            "https://www.flaticon.com/svg/static/icons/svg/188/188955.svg"),
    ;

    Class<? extends Item<?>> type;
    String image;
    String description;

    ReferentielEnum(Class<? extends Item<?>> type, String description) {
        this.type = type;
        this.description = description;
        this.image = "https://www.flaticon.com/svg/static/icons/svg/188/188924.svg";
    }

    ReferentielEnum(Class<? extends Item<?>> type, String description,String image) {
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public static ReferentielEnum fromName(String key){
        return Arrays.stream(values())
                .filter( re -> key != null && key.toUpperCase().equals(re.name()) )
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<ItemUI> getReferentiel() {
        return Arrays.stream(values())
                .map(refE -> {
                    try {
                        var typeE = refE.type;
                        var item = typeE.getDeclaredConstructor().newInstance();
                        var itemToSend = new ItemUI();
                        itemToSend.setDescription(refE.description);
                        itemToSend.setName(typeE.getSimpleName());
                        itemToSend.setPrice(item.price());
                        itemToSend.setImage(refE.image);
                        return itemToSend;
                    } catch (ReflectiveOperationException ignored) {
                        return null;
                    }
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Class<? extends Item<?>> getType() {
        return type;
    }
}
