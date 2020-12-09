package com.miage.alom.shop_api.bo;

import com.miage.alom.shop_api.trainer.bo.Trainer;

import javax.persistence.*;

@Embeddable
public class Portefeuille {

    Integer solde;

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }

    public void approvisionnement(Integer addingAmount){
        setSolde(getSolde()+addingAmount);
    }

    public void achat(Integer price){setSolde(getSolde()-price);}

    public Portefeuille() {
        setSolde(0);
    }
}
