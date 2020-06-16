package com.dualyty.cardventure;

public class PlayerData {

    private int lifeCurrent;
    private int manaCurrent;
    private int armorCurrent;
    private int rmCurrent;


    private int or;
    private int prestige;

    public PlayerData () {
        Player player = new Player();
        this.lifeCurrent = player.getLifeCurrent();
        this.manaCurrent = player.getManaCurrent();
        this.armorCurrent = player.getArmorCurrent();
        this.rmCurrent = player.getRmCurrent();
        this.or = player.getOr();
        this.prestige = player.getPrestige();
    }

    public PlayerData(Player player) {
        this.lifeCurrent = player.getLifeCurrent();
        this.manaCurrent = player.getManaCurrent();
        this.armorCurrent = player.getArmorCurrent();
        this.rmCurrent = player.getRmCurrent();
        this.or = player.getOr();
        this.prestige = player.getPrestige();
    }

    public void setPlayerData (Player player) {
        this.lifeCurrent = player.getLifeCurrent();
        this.manaCurrent = player.getManaCurrent();
        this.armorCurrent = player.getArmorCurrent();
        this.rmCurrent = player.getRmCurrent();
        this.or = player.getOr();
        this.prestige = player.getPrestige();
    }

    public void getPlayerData (Player player) {
        player.setLifeCurrent(this.lifeCurrent);
        player.setManaCurrent(this.manaCurrent);
        player.setArmorCurrent(this.armorCurrent);
        player.setRmCurrent(this.rmCurrent);
        player.setOr(this.or);
        player.setPrestige(this.prestige);
    }

}
