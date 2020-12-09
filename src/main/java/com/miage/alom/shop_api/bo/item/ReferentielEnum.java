package com.miage.alom.shop_api.bo.item;

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

public enum ReferentielEnum {

    SUPERBONBON(SuperBonbon.class,"Augmente un niveau d'un pokemon au choix !"),
    POKEBALL(PokeBall.class,"Deviens le meilleur dresseur et obtiens un pokemon commun de niveau 5 au hasard !"),
    SUPERBALL(SuperBall.class,"Super un pokemon de niveau 10 non-commun arrive !"),
    HYPERBALL(HyperBall.class,"Hyperball, t'es adresseurs n'ont plus aucune chance avec ton futur pokemon rare niveau 20"),
    MASTERBALL(MasterBall.class,"Usage unique, deviens une legende en obtenant l'un des pokemons legendaire de ta generation.. En plus il est niveau 40"),
    ;

    Class<? extends Item<?>> type;

    String description;

    ReferentielEnum(Class<? extends Item<?>> type, String description) {
        this.type = type;
        this.description = description;
    }

    /*J'aurai pu utiliser une map mais j'aime me compliquer la vie ahah*/
    public static List<Pair<String,Pair<String,Integer>>> getReferentiel() {
        return Arrays.stream(values())
                .map(refE -> {
                    try {
                        var typeE = refE.type;
                        var item = typeE.getDeclaredConstructor().newInstance();
                        return Pair.of(typeE.getSimpleName(),
                                    Pair.of(refE.description,item.price())
                        );
                    } catch (ReflectiveOperationException ignored) {
                        return null;
                    }
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
