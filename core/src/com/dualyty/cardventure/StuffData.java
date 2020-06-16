package com.dualyty.cardventure;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

public class StuffData implements Json.Serializable {

    public Equipement tete;
    public Equipement plastron;
    public Equipement jambe;
    public Equipement maingauche;
    public Equipement maindroite;

    public ArrayList<Equipement> sac;

    //JSONObject stuffDetails = new JSONObject();

    public StuffData () {
        Player player = new Player();
        this.tete = player.tete;
        this.plastron = player.plastron;
        this.jambe = player.jambe;
        this.maindroite = player.maindroite;
        this.maingauche = player.maingauche;

        this.sac = player.sac;
    }

    public StuffData(Player player) {
        this.tete = player.tete;
        this.plastron = player.plastron;
        this.jambe = player.jambe;
        this.maindroite = player.maindroite;
        this.maingauche = player.maingauche;

        this.sac = player.sac;
    }

    public void setStuffData(Player player) {
        this.tete = player.tete;
        this.plastron = player.plastron;
        this.jambe = player.jambe;
        this.maindroite = player.maindroite;
        this.maingauche = player.maingauche;

        this.sac = player.sac;
    }

    public void getStuffData (Player player){
        player.tete = this.tete;
        player.plastron = this.plastron;
        player.jambe = this.jambe;
        player.maindroite = this.maindroite;
        player.maingauche = this.maingauche;

        player.sac = this.sac;

    }

    @Override
    public void write(Json json) {
        GameManager.fileHandleS.writeString((json.prettyPrint(this)),
                false);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
