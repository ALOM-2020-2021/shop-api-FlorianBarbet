package com.miage.alom.shop_api.trainer.bo;

import com.miage.alom.shop_api.bo.Portefeuille;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trainer {
    @Id
    String name;

    @Column
    String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Pokemon> team;

    @Embedded
    Portefeuille portefeuille;

    @Column(columnDefinition = "boolean default false")
    Boolean buyedMasterBall;

    public Trainer(String name) {
        this.name = name;
    }

    public Trainer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(Portefeuille portefeuille) {
        this.portefeuille = portefeuille;
    }

    public Boolean getBuyedMasterBall() {
        return buyedMasterBall;
    }

    public void setBuyedMasterBall(Boolean buyedMasterBall) {
        this.buyedMasterBall = buyedMasterBall;
    }
}
