package com.dualyty.cardventure;

import java.util.ArrayList;

public class Player {

    /*
    Classe qui représente le joueur, ses stats, sa progression
     */

    //final Cardventure game;

    /*
    //This will get your preferences from storage
    Preferences save = Gdx.app.getPreferences("save");
    Map<String,Equipement> saveSac;

     */

    //statistiques max

    //statistiques "en cours"
    private int lifeCurrent;
    private int manaCurrent;
    private int armorCurrent;
    private int rmCurrent;


    private int or;             //représente  l'argent du joueur
    private int prestige;       //le prestige du joueur, peut etre considere comme une barre de progression


    //equipement
    public Equipement tete;
    public Equipement plastron;
    public Equipement jambe;
    public Equipement maingauche;
    public Equipement maindroite;

    public ArrayList<Equipement> sac;      //le sac a dos du jour, ou entreposer son stuff

    /*
    public Player(Cardventure game) {       //initialisation de ses stats
        this.game = game;

        this.lifeCurrent = 100;
        this.manaCurrent = 20;
        this.armorCurrent = 10;       //il n'est pas tout nue non plus
        this.rmCurrent = 10;

        this.or  = 0;
        this.prestige = 0;

        this.sac = new ArrayList<Equipement>();     //creation du sac d'equipement / inventaire du personnage
    }*/

    public Player() {       //initialisation de ses stats

        this.lifeCurrent = 100;
        this.manaCurrent = 20;
        this.armorCurrent = 10;       //il n'est pas tout nue non plus
        this.rmCurrent = 10;

        this.or  = 0;
        this.prestige = 0;

        this.sac = new ArrayList<Equipement>();     //creation du sac d'equipement / inventaire du personnage
    }

    /*

    public void loadPlayer() {

        //to do

        //Get value from a preference key "key" (must not be empty)
        game.player.setLifeCurrent(save.getInteger("vie"));
        game.player.setArmorCurrent(save.getInteger("armor"));
        game.player.setManaCurrent(save.getInteger("mana"));
        game.player.setRmCurrent(save.getInteger("rm"));
        game.player.setOr(save.getInteger("or"));
        game.player.setPrestige(save.getInteger("prestige"));

        save.get();
    }

    public void savePlayer(Cardventure game) {

        save.putInteger("vie", game.player.getLifeCurrent());
        save.putInteger("armor", game.player.getArmorCurrent());
        //save.putInteger("armorMax", game.player.getArmorMax());
        save.putInteger("mana", game.player.getManaCurrent());
        //save.putInteger("manaMax", game.player.getManaMax());
        save.putInteger("rm", game.player.getRmCurrent());
        //save.putInteger("rmMax", game.player.getRmMax());
        save.putInteger("or", game.player.getOr());
        save.putInteger("prestige", game.player.getPrestige());


        saveSac = new HashMap<>();
        saveSac.put("tete",game.player.tete);
        saveSac.put("plastron",game.player.plastron);
        saveSac.put("jambe",game.player.jambe);
        saveSac.put("maindroite",game.player.maindroite);
        saveSac.put("maingauche",game.player.maingauche);

        int i = 0;
        for (Equipement stuff: game.player.sac) {
            saveSac.put("sac" + i,stuff);
            i ++;
        }

        save.put(saveSac);


        //This will finally save the changes to storage
        save.flush();

    }

     */



    public boolean sell(Equipement equipement) {                                //vendre un equipement
     if (equipement.equipe == false){                                           //si l'equipement n'est pas equipe
         if (this.sac.contains(equipement)) {                       //s'il est bien dans le sac du joueur
                this.or += (int) (equipement.or * 0.8);             //rapporte 80% de sa valeur en or
                this.sac.remove(equipement);                        //disparait du sac
                return true;
            }
     }
     else {
         this.or += (int) (equipement.or * 0.8);
         switch (equipement.getType()) {                                        //si l'equipement est equipe
             case "tete" :
                 this.tete = null;
                 return true;
             case "jambe" :
                 this.jambe = null;
                 return true;
             case "plastron" :
                 this.plastron = null;
                 return true;
             case "bouclier" :
                 this.maingauche = null;
                 return true;
             case "baton" :
                 this.maindroite = null;
                 return true;
             default :
                 return false;
            }
        }
     return false;
     }

    public boolean buy(Equipement equipement) {                             //acheter un equipement
        if (!this.sac.contains(equipement)) {                   //s'il n'est pas déja dans le sac
            if (equipement.or <= this.or) {                     //si le joueur a les moyen
                this.or -= equipement.or;                           //le joueur paye
                this.sac.add(equipement);                           //ajouté au sac
                return true;
            }
        }
        return false;
    }

    public boolean equipe(Equipement equipement) {
        System.out.println("Je veux equiper un equipement");
        if (equipement.equipe == false) {
            System.out.println("Il n'est pas déja equipé");
            Equipement precedent = null;
            switch (equipement.type) {
                case "tete":
                    System.out.println("tete");
                    if (this.tete == null) {
                        System.out.println("rien a la place");
                        this.tete = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    } else {
                        System.out.println("qqc a la place");
                        this.tete.equipe = false;
                        precedent = this.tete;
                        this.sac.add(precedent);
                        this.tete = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    }
                    break;
                case "plastron":
                    System.out.println("plastron");
                    if (this.plastron == null) {
                        System.out.println("rien a la place");
                        this.plastron = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    } else {
                        System.out.println("qqc a la place");
                        this.plastron.equipe = false;
                        precedent = this.plastron;
                        this.sac.add(precedent);
                        this.plastron = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    }
                    break;
                case "jambe":
                    System.out.println("jambe");
                    if (this.jambe == null) {
                        System.out.println("rien a la place");
                        this.jambe = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    } else {
                        System.out.println("qqc a la place");
                        this.jambe.equipe = false;
                        precedent = this.jambe;
                        this.sac.add(precedent);
                        this.jambe = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    }
                    break;
                case "baton":
                    System.out.println("baton");
                    if (this.maindroite == null) {
                        System.out.println("rien a la place");
                        this.maindroite = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    } else {
                        System.out.println("qqc a la place");
                        this.maindroite.equipe = false;
                        precedent = this.maindroite;
                        this.sac.add(precedent);
                        this.maindroite = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    }
                    break;
                case "bouclier":
                    System.out.println("bouclier");
                    if (this.maingauche == null) {
                        System.out.println("rien a la place");
                        this.maingauche = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    } else {
                        System.out.println("qqc a la place");
                        this.maingauche.equipe = false;
                        precedent = this.maingauche;
                        this.sac.add(precedent);
                        this.maingauche = equipement;
                        equipement.equipe = true;
                        this.sac.remove(equipement);
                    }
                    break;

                default:
                    System.out.println("default");
                    return false;
            }
            if (precedent != null) {
                this.setArmorCurrent(this.getArmorCurrent()-precedent.armor);
                this.setRmCurrent(this.getRmCurrent()-precedent.rm);
                this.setManaCurrent(this.getManaCurrent()-precedent.mana);
            }
            this.setArmorCurrent(this.getArmorCurrent()+equipement.armor);
            this.setRmCurrent(this.getRmCurrent()+equipement.rm);
            this.setManaCurrent(this.getManaCurrent()+equipement.mana);
            return true;
            }
        System.out.println("ah bah nan, il est deja equipe");
        return false;
    }



    /* lorsqu'une carte repo ou quete s'applique au joueur */
    public boolean action(Carte carte) {


        //les stats de la carte sont positives si carte repo, donc elles s'additionnent
        //les stats de la carte sont négatives si carte quete, donc elles se soustraient

        this.setManaCurrent(getManaCurrent() + carte.mana);
        if (this.getManaCurrent() < 0) {                                //Si le joueur n'a plus de mana
            carte.life += (int) (- this.getManaCurrent()/2);            //il prend une penalite
            carte.armor += (int) (- this.getManaCurrent()/2);           //les stats négatives sont
            carte.rm += (int) (- this.getManaCurrent()/2);              //augmente
            this.setManaCurrent(0);                                     //et le mana est remi a 0
        }
        if (this.getManaCurrent() > this.getManaMax()) {    //le joueur ne peut pas recuperer
            this.setManaCurrent(this.getManaMax());         //au dela de ses stats max
        }

        this.setLifeCurrent(getLifeCurrent() + carte.life);
        if (this.getLifeCurrent() > this.getLifeMax()) {
            this.setLifeCurrent(this.getLifeMax());
        }

        //si le joueur n'a plus d'armure ou de rm il n'est plus protege
        //cela affecte directement sa vie

        this.setArmorCurrent(getArmorCurrent() + carte.armor);
        if (this.getArmorCurrent() < 0) {
            this.setLifeCurrent(this.getLifeCurrent() + this.getArmorCurrent());
            this.setArmorCurrent(0);
        }
        if (this.getArmorCurrent() > this.getArmorMax()) {
            this.setArmorCurrent(this.getArmorMax());
        }

        this.setRmCurrent(getRmCurrent() + carte.rm);
        if (this.getRmCurrent() < 0) {
            this.setLifeCurrent(this.getLifeCurrent() + this.getRmCurrent());
            this.setRmCurrent(0);
        }
        if (this.getRmCurrent() > this.getRmMax()) {
            this.setRmCurrent(this.getRmMax());
        }

        this.setOr(getOr() + carte.or);


        if (this.getLifeCurrent() >= 1) { return true; }        //Tant que le joueur est en vie on renvoie true
        return false;                                                       //Si le joueur n'a plus de vie on renvoie false
    }


    public void giveStuff(Equipement stuff) {
        this.sac.add(stuff);
    }

    public void giveRecompense (int or, int prestige, ArrayList<Equipement> equipement) {
        System.out.println("giveRecompense");
        this.setOr(this.getOr() + or);
        this.setPrestige((this.getPrestige() + prestige));
        for (Equipement stuff: equipement) {
            this.giveStuff(stuff);
        }
    }

    /*
    getter and setter
     */

    public int getLifeMax() {
        return 100;
    }

    public int getManaMax() {
        int mana = 20;
        if (this.tete != null) {
            mana += this.tete.mana;
        }
        if (this.plastron != null) {
            mana += this.plastron.mana;
        }
        if (this.jambe != null) {
            mana += this.jambe.mana;
        }
        if (this.maingauche != null) {
            mana += this.maingauche.mana;
        }
        if (this.maindroite != null) {
            mana += this.maindroite.mana;
        }
        return mana;
    }

    public int getArmorMax() {
        int armor = 10;
        if (this.tete != null) {
            armor += this.tete.armor;
        }
        if (this.plastron != null) {
            armor += this.plastron.armor;
        }
        if (this.jambe != null) {
            armor += this.jambe.armor;
        }
        if (this.maingauche != null) {
            armor += this.maingauche.armor;
        }
        if (this.maindroite != null) {
            armor += this.maindroite.armor;
        }
        return armor;
    }


    public int getRmMax() {
        int rm = 10;
        if (this.tete != null) {
            rm += this.tete.rm;
        }
        if (this.plastron != null) {
            rm += this.plastron.rm;
        }
        if (this.jambe != null) {
            rm += this.jambe.rm;
        }
        if (this.maingauche != null) {
            rm += this.maingauche.rm;
        }
        if (this.maindroite != null) {
            rm += this.maindroite.rm;
        }
        return rm;
    }

    public int getLifeCurrent() {
        return lifeCurrent;
    }

    public void setLifeCurrent(int lifeCurrent) {
        if (lifeCurrent > getLifeMax()) {
            this.lifeCurrent = getLifeMax();
        } else {
            this.lifeCurrent = lifeCurrent;
        }
    }

    public int getManaCurrent() {
        return manaCurrent;
    }

    public void setManaCurrent(int manaCurrent) {
        if (manaCurrent > getManaMax()) {
            this.manaCurrent = getManaMax();
        } else {
            this.manaCurrent = manaCurrent;
        }
    }

    public int getArmorCurrent() {
        return armorCurrent;
    }

    public void setArmorCurrent(int armorCurrent) {
        if (armorCurrent > getArmorMax()) {
            this.armorCurrent = getArmorMax();
        } else {
           this.armorCurrent = armorCurrent;
        }
    }

    public int getRmCurrent() {
        return rmCurrent;
    }

    public void setRmCurrent(int rmCurrent) {
        if (rmCurrent > getRmMax()) {
            this.rmCurrent = getRmMax();
        } else {
            this.rmCurrent = rmCurrent;
        }
    }

    public int getOr() {
        return or;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }
    
}
