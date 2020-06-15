package com.dualyty.cardventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class GameManager {

    private static GameManager ourInstance = new GameManager();

    public Player player;
    private Json json = new Json();
    private FileHandle fileHandle = Gdx.files.local("bin/saved2.json");


    private GameManager() {
        System.out.println("new GamaManager");
    }


    public void initializeGameData(Cardventure game) {
        System.out.println("initializeGameData");
        if (!fileHandle.exists()) {
            player = new Player();
            savePlayer();
        } else {
            loadPlayer();
        }
    }

    public void savePlayer() {
        System.out.println("saveGame");

        if (player != null) {
            fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(player)),
                    false);
        }

    }

    public void loadPlayer() {
        System.out.println("loadGame");

        player = json.fromJson(Player.class,
                Base64Coder.decodeString(fileHandle.readString()));
    }


    public static GameManager getInstance() {
        System.out.println("getInstance");
        return ourInstance;
    }

}
