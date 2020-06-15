package com.dualyty.cardventure;

public class Carte {

    /*
    classe pour gerer / generer les cartes quete et repo
     */

    final Cardventure game;

    public int life;
    public int mana;
    public int armor;
    public int rm;
    public int or;
    public int niveauRecompense = 0;
    public boolean fin = false;

    public CharSequence texte;

    public String nom;

    public Carte(Cardventure game){
        this.game = game;
    }


    /* Generer une Carte repo ou quete */
    public void generateCarte(String type, int level) {
        //si carte quete, level represente le niveau de difficulte de la quete


        switch ((int) (Math.random()*10)) {             //nombre attendu entre 0 et 10;
            case 10: case 9 :
                if (type == "repo") {                   //remonte les stats a fond
                    this.nom = " Un mecene croit en vous, \n  il veut vous aider";
                    this.life = game.player.getLifeMax() - game.player.getLifeCurrent();
                    this.armor = game.player.getArmorMax() - game.player.getArmorCurrent();
                    this.rm = game.player.getRmMax() - game.player.getRmCurrent();
                    this.mana = game.player.getManaMax() - game.player.getManaCurrent();
                    this.or = 0;
                }
                if (type == "quete") {                  //abandon de la quete et perte de la recompense
                    this.nom = " C'est trop dur, \n    j'abandonne";
                    this.fin =true;
                    this.life = 0;
                    this.armor = 0;
                    this.rm = 0;
                    this.mana = 0;
                    this.or = 0;
                }
                break;
            case 8 : case 7 :
                if (type == "repo") {
                    this.nom = " Dormir a l'auberge";
                    this.life = (int) (Math.random()*20) + 20;     //rend entre 20 et 40 de vie
                    this.mana = (int) (Math.random()*20) + 10;     //rend entre 10 et 30 de mana
                    this.armor = 0;                                //ne rend pas d'armure
                    this.rm = (int) (Math.random()*7);             //peut rendre entre 0 et 7 rm
                    this.or = - (int) (Math.random()*7 + 3);       //coute entre 5 et 12 or

                }
                if (type == "quete") {
                    this.nom = " Y'a quand meme \n  beaucoup d'ennemi";
                    this.life = - ( (int) (Math.random()*15) + 5 + level );
                    this.armor = - ( (int) (Math.random()*12) + 8 + level);
                    this.rm = - ( (int) (Math.random()*10) + 7 + level);
                    this.mana = - ( (int) (Math.random()*8) + 7  + level);
                    this.or = 0;
                    this.niveauRecompense = 4;
                }
                break;
            case 6 : case 5 : case 4 :
                if (type == "repo") {
                    this.nom = " Dormir chez quelqu'un";
                    this.life = (int) (Math.random()*15) + 15;     //rend entre 15 et 30 de vie
                    this.mana = (int) (Math.random()*15) + 10;     //rend entre 10 et 25 de mana
                    this.armor = 0;                                //ne rend pas d'armure
                    this.rm = (int) (Math.random()*7);             //peut rendre entre 0 et 7 rm
                    this.or = - (int) (Math.random()*4);           //peut couter jusq'a 4 or
                }
                if (type == "quete") {
                    this.nom = " Des bandits de \n  grands chemins";
                    this.life = - ( (int) (Math.random()*8) + 3 + level );
                    this.armor = - ( (int) (Math.random()*10) + 5 + level);
                    this.rm = - ( (int) (Math.random()*10) + 5 + level);
                    this.mana = - ( (int) (Math.random()*8) + 5 + level);
                    this.or = 0;
                    this.niveauRecompense = 2;
                }
                break;
            default :
                if (type == "repo") {
                    this.nom = " Dormir a la belle etoile";
                    this.life = (int) (Math.random()*10) + 10;     //rend entre 10 et 20 de vie
                    this.mana = (int) (Math.random()*10) + 5;      //rend entre 5 et 15 de mana
                    this.armor = 0;                                //ne rend pas d'armure
                    this.rm = (int) (Math.random()*5);             //peut rendre en 0 et 5 rm
                    this.or = 0;                                   //ne coute rien
                }
                if (type == "quete") {
                    this.nom = " Ils ne savent meme pas \n  se battre";
                    this.life = - ( (int) (Math.random()*7) + 0 + level);
                    this.armor = - ( (int) (Math.random()*8) + 2 + level);
                    this.rm = - ( (int) (Math.random()*9) + 1 + level);
                    this.mana = - ( (int) (Math.random()*7) + 2 + level);
                    this.or = 0;
                    this.niveauRecompense = 1;
                }
                break;
        }
        this.texte = this.toString();
    }

    public String toString() {
        String texte = " ";
        texte += this.nom;
        if (this.life != 0) { texte += "\n\n  vie : " + this.life;}
        if (this.mana != 0) { texte += "\n\n  mana : " + this.mana;}
        if (this.armor != 0) { texte += "\n\n  armure : " + this.armor;}
        if (this.rm != 0) { texte += "\n\n  rm : " + this.rm;}
        if (this.or != 0) { texte += "\n\n  or : " + this.or;}
        if (this.niveauRecompense != 0) { texte += "\n\n  récompense : " + this.niveauRecompense;}

        return texte;
    }


}
