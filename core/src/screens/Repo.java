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
import com.dualyty.cardventure.Carte;

public class Repo implements Screen {

    /*
    Page repo, pour regagner de la vie et de la mana
     */

    final Cardventure game;

    public Sprite fond;
    private Stage stage;

    Carte carte1;
    Carte carte2;
    Carte carte3;

    ImageButton spriteCarte1;
    ImageButton spriteCarte2;
    ImageButton spriteCarte3;

    ImageButton spriteStat;

    BitmapFont font;


    public Repo (Cardventure game) {
        this.game = game;
    }


    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());

        font = new BitmapFont();

        fond = new Sprite( new Texture(game.texture.fond));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        carte1 = new Carte(this.game);
        carte2 = new Carte(this.game);
        carte3 = new Carte(this.game);

        carte1.generateCarte("repo",0);
        carte2.generateCarte("repo",0);
        carte3.generateCarte("repo",0);


        spriteCarte1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
        spriteCarte2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
        spriteCarte3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));



        spriteCarte1.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte1.setPosition(Gdx.graphics.getWidth()*0,0);

        spriteCarte2.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte2.setPosition(Gdx.graphics.getWidth()*1/3,0);

        spriteCarte3.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte3.setPosition(Gdx.graphics.getWidth()*2/3,0);

        stage.addActor(spriteCarte1);
        stage.addActor(spriteCarte2);
        stage.addActor(spriteCarte3);


        spriteStat = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.barreStats))));

        spriteStat.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/4);
        spriteStat.setPosition(0,Gdx.graphics.getHeight()*3/4);

        stage.addActor(spriteStat);


        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        spriteCarte1.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.player.action(carte1);
                game.setScreen(game.choix);
                dispose();
                return true;
            }
        });

        spriteCarte2.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.player.action(carte2);
                game.setScreen(game.choix);
                dispose();
                return true;
            }
        });

        spriteCarte3.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.player.action(carte3);
                game.setScreen(game.choix);
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
        font.draw(game.batch,carte1.texte,spriteCarte1.getWidth()/4,Gdx.graphics.getHeight()/2);
        font.draw(game.batch,carte2.texte,spriteCarte2.getX() + spriteCarte2.getWidth()/4,Gdx.graphics.getHeight()/2);
        font.draw(game.batch,carte3.texte,spriteCarte3.getX() +spriteCarte3.getWidth()/4,Gdx.graphics.getHeight()/2);

        font.draw(game.batch,"  " + game.player.getLifeCurrent() + " / " + game.player.getLifeMax() + " vie",Gdx.graphics.getWidth()*2/16,Gdx.graphics.getHeight()*7/8);
        font.draw(game.batch,"" + game.player.getManaCurrent() + " / " + game.player.getManaMax() + " mana",Gdx.graphics.getWidth()*5/16,Gdx.graphics.getHeight()*7/8);
        font.draw(game.batch,"" + game.player.getArmorCurrent() + " / " + game.player.getArmorMax() + " armure",Gdx.graphics.getWidth()*8/16,Gdx.graphics.getHeight()*7/8);
        font.draw(game.batch,"" + game.player.getRmCurrent() + " / " + game.player.getRmMax() + " ressistance",Gdx.graphics.getWidth()*11/16,Gdx.graphics.getHeight()*7/8);
        font.draw(game.batch,"" + game.player.getOr() + " or",Gdx.graphics.getWidth()*14/16,Gdx.graphics.getHeight()*7/8);
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
