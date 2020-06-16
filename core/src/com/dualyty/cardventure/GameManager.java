package com.dualyty.cardventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class GameManager {

    private static GameManager ourInstance = new GameManager();

    public PlayerData player = new PlayerData();
    public StuffData stuff = new StuffData();
    private Json json = new Json();
    public FileHandle fileHandleP = Gdx.files.local("bin/savePlayer.json");
    public static FileHandle fileHandleS = Gdx.files.local("bin/saveStuff.json");


    private GameManager() {
        System.out.println("new GamaManager");
    }


    public void initializeGameData(Cardventure game) {
        System.out.println("initializeGameData");
        if (!fileHandleP.exists()) {
            player = new PlayerData(game.player);
            //stuff = new StuffData(game.player);
            savePlayer();
        } else {
            loadPlayer();
        }
    }

    public void savePlayer() {
        System.out.println("saveGame");

        if (player != null) {
            fileHandleP.writeString(Base64Coder.encodeString(json.prettyPrint(player)),
                    false);
        }
        if (stuff != null) {
            //stuff.write(json);
            //fileHandleS.writeString(Base64Coder.encodeString(json.prettyPrint(stuff)),false);
        }


    }

    public void loadPlayer() {
        System.out.println("loadGame");

        player = json.fromJson(PlayerData.class,
                Base64Coder.decodeString(fileHandleP.readString()));
        //stuff = json.fromJson(StuffData.class,fileHandleS.readString());
    }


    public static GameManager getInstance() {
        System.out.println("getInstance");
        return ourInstance;
    }

}
