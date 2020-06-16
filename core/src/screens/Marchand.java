package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dualyty.cardventure.Cardventure;

public class Marchand implements Screen {

    /*
    page marchand, permet de choisir ce que l'on veut y faire, acheter, vendre ou reparer son equipement
     */

    final Cardventure game;

    public Sprite fond;
    private Stage stage;

    int prixRep;      //prix de la réparation de l'equipement

    ImageButton spriteAchat;
    ImageButton spriteVente;
    ImageButton spriteRep;

    ImageButton spriteStat;

    ImageButton previousScreen;

    BitmapFont font;

    public Marchand (Cardventure game) {
        this.game = game;
        float tarif = 0;
        if (game.player.tete != null) {
            tarif += game.player.tete.or * 0.2;
        }
        if (game.player.jambe != null) {
            tarif += game.player.jambe.or * 0.2;
        }
        if (game.player.plastron != null) {
            tarif += game.player.plastron.or * 0.2;
        }
        if (game.player.maindroite != null) {
            tarif += game.player.maindroite.or * 0.2;
        }
        if (game.player.maingauche != null) {
            tarif += game.player.maingauche.or * 0.2;
        }
        prixRep = 1 + (int) tarif;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());

        font = new BitmapFont();

        fond = new Sprite( new Texture("fond.png"));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        spriteAchat = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carte.png"))));
        spriteVente = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carte.png"))));
        spriteRep = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carte.png"))));




        spriteStat = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("barre_stats.png"))));

        spriteStat.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/4);
        spriteStat.setPosition(Gdx.graphics.getWidth()*1/20,Gdx.graphics.getHeight()*4/5);

        spriteStat.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.manager.get("audio/select.ogg", Sound.class).play();
                game.setScreen(game.perso = new Perso(game));     //envoie sur la page repo
                dispose();
                return true;
            }
        });

        stage.addActor(spriteStat);

        previousScreen = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("back.png"))));
        previousScreen.setPosition(Gdx.graphics.getWidth()*1/20,Gdx.graphics.getHeight()*9/10);
        stage.addActor(previousScreen);

        previousScreen.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.manager.get("audio/select.ogg", Sound.class).play();
                game.setScreen(game.choix);
                dispose();
                return true;
            }
        });


        spriteAchat.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteAchat.setPosition(Gdx.graphics.getWidth()*0,0);

        spriteVente.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteVente.setPosition(Gdx.graphics.getWidth()*1/3,0);

        spriteRep.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteRep.setPosition(Gdx.graphics.getWidth()*2/3,0);

        stage.addActor(spriteAchat);
        stage.addActor(spriteVente);
        stage.addActor(spriteRep);

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui


        spriteAchat.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.manager.get("audio/select.ogg", Sound.class).play();
                game.setScreen(game.marchand_achat = new Marchand_achat(game));
                dispose();
                return true;
            }
        });

        spriteVente.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.manager.get("audio/select.ogg", Sound.class).play();
                game.setScreen(game.marchand_vente = new Marchand_vente(game));
                dispose();
                return true;
            }
        });

        spriteRep.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.manager.get("audio/select.ogg", Sound.class).play();
                game.player.setArmorCurrent(game.player.getArmorMax());
                game.player.setRmCurrent(game.player.getRmMax());
                game.player.setOr(game.player.getOr()-prixRep);
                game.setScreen(game.marchand = new Marchand(game));
                dispose();
                return true;
            }
        });



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        fond.draw(game.batch);
        game.batch.end();

        stage.act(delta);
        stage.draw();

        game.batch.begin();
        font.getData().setScale(2);
        font.draw(game.batch,"  Acheter du \n\n  nouvel équipement  ",spriteAchat.getWidth()/4,Gdx.graphics.getHeight()/2);
        font.draw(game.batch,"  Vendre de \n\n  l'équipement  ",spriteVente.getX() + spriteVente.getWidth()/4,Gdx.graphics.getHeight()/2);
        font.draw(game.batch,"  Réparer son \n\n  équipement  \n\n prix : " + prixRep,spriteRep.getX() +spriteRep.getWidth()/4,Gdx.graphics.getHeight()/2);

        font.draw(game.batch,"  " + game.player.getLifeCurrent() + " / " + game.player.getLifeMax() + " vie",Gdx.graphics.getWidth()*3/16,Gdx.graphics.getHeight()*9/10);
        font.draw(game.batch,"" + game.player.getManaCurrent() + " / " + game.player.getManaMax() + " mana",Gdx.graphics.getWidth()*6/16,Gdx.graphics.getHeight()*9/10);
        font.draw(game.batch,"" + game.player.getArmorCurrent() + " / " + game.player.getArmorMax() + " armure",Gdx.graphics.getWidth()*9/16,Gdx.graphics.getHeight()*9/10);
        font.draw(game.batch,"" + game.player.getRmCurrent() + " / " + game.player.getRmMax() + " ressistance",Gdx.graphics.getWidth()*12/16,Gdx.graphics.getHeight()*9/10);
        font.draw(game.batch,"" + game.player.getOr() + " or",Gdx.graphics.getWidth()*15/16,Gdx.graphics.getHeight()*9/10);

        game.batch.end();




    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
