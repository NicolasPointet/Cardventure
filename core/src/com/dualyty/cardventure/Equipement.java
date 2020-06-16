package com.dualyty.cardventure;

import com.badlogic.gdx.graphics.Texture;

public class Equipement extends Carte  {

    /*
    classe des cartes equipement
     */

    String type;

    public String getType() {
        return type;
    }

    int niveau;
    int prestige;

    public boolean equipe;

    public Texture texture;

    public CharSequence texte;
    public CharSequence texteEquipe;    //texte lors que l'objet est equipe


    public Equipement(Cardventure game) {
        super(game);
        equipe = false;
        this.armor = 0;
        this.rm = 0;
        this.mana = 0;
        this.niveau = 0;
        this.prestige = game.player.getPrestige();
        this.generateStuff();
    }

    /* Generer une "Carte" equipement */
    public void generateStuff() {

        generateType();                        /* Permet de "choisir" le type de l'equipement et de donner de lui generer des stats */

        generateNiveau();                      /* Genere le "niveau" de l'equipement */

        this.armor = (int) (this.armor * this.niveau);
        this.rm = (int) (this.rm * this.niveau);
        this.mana = (int) (this.mana * this.niveau);

        this.or = (int) ((this.armor+this.rm+this.mana)/10) + this.niveau;
                                                    //valeur de l'equipement

        this.texture = new Texture("image/stuff/" + type + niveau + ".png");
                                                    //un "skin" different en fonction de l'objet

        this.texte = this.toString();
        this.texteEquipe = this.toStringEquipe();
    }

    /* Permet de "choisir" le type de l'equipement et de lui generer des stats */
    public void generateType() {
        switch ((int) (Math.random() * 5)) {          //nombre en 0 et 5;
            case 0:
                this.type = "tete";
                this.armor += (int) (prestige / 5) + (int) (Math.random() * 6);
                this.rm += (int) (prestige / 5) + (int) (Math.random() * 5);
                break;
            case 1:
                this.type = "plastron";
                this.armor += (int) (prestige / 5) + (int) (Math.random() * 10);
                this.rm += (int) (prestige / 5) + (int) (Math.random() * 10);
                this.mana += (int) (prestige / 5) + (int) (Math.random() * 15);
                break;
            case 2:
                this.type = "jambe";
                this.armor += (int) (prestige / 5) + (int) (Math.random() * 8);
                this.rm += (int) (prestige / 5) + (int) (Math.random() * 6);
                break;
            case 3:
                this.type = "bouclier";
                this.armor += (int) (prestige / 5) + (int) (Math.random() * 8);
                this.rm += (int) (prestige / 5) + (int) (Math.random() * 2);
                break;
            default:
                this.type = "baton";
                this.armor += (int) (prestige / 5) + (int) (Math.random() * 2);
                this.mana += (int) (prestige / 5) + (int) (Math.random() * 20);
        }
    }

    /* Genere le "niveau" de l'equipement */
    public void generateNiveau () {
        switch ((int) (Math.random()*10)) {           //nombre attendu entre 0 et 10;
            case 10:
            case 9 :
                this.niveau = 6; break;            // objet divin
            case 8 :
            case 7 :
                this.niveau = 4; break;            // objet spectaculaire
            case 6 :
            case 5 :
            case 4 :
                this.niveau = 2; break;            // objet rare
            default : this.niveau = 1; break;      // objet commun
        }
    }


    public String toString(){
        String texte = "  ";
        texte += this.type;
        if (this.armor != 0) { texte += "\n\n  armure : + " + this.armor;}
        if (this.rm != 0) { texte += "\n\n  rm : + " + this.rm;}
        if (this.mana != 0) { texte += "\n\n  mana : + " + this.mana;}
        if (this.or != 0) { texte += "\n\n  valeur : " + this.or + " pieces d'or";}

        return texte;
    }

    public String toStringEquipe(){
        String texte = "  ";
        if (this.armor != 0) { texte += "\n\n  armure : + " + this.armor;}
        if (this.rm != 0) { texte += "\n\n  rm : + " + this.rm;}
        if (this.mana != 0) { texte += "\n\n  mana : + " + this.mana;}

        return texte;
    }

}