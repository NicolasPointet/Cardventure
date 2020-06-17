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

public class Choix implements Screen {

    /*
    La page qui demande au joueur de choisir
    entre se reposer, aller faire une quete ou aller voir le marchand
     */

    final Cardventure game;

    private Sprite fond;

    private Stage stage;

    /*
    Chaque boutton renvoie vers une page "dédiée"
     */
    private ImageButton repo;       //vers la page repo
    private ImageButton quete;      //ver la page quete
    private ImageButton marchand;   //vers la page marchandd

    private ImageButton spriteStat; //vers la page fiche perso -> pas encore implementée


    BitmapFont font;                //permet d'ecrire du texte par dessus


    public Choix(Cardventure game) {
        this.game = game;
    }


    @Override
    public void show() {

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui

        font = new BitmapFont();

        fond = new Sprite(new Texture(game.texture.fond));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        repo = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carteRepo))));
        quete = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carteQuete))));
        marchand = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carteMarchand))));


        repo.setPosition(Gdx.graphics.getWidth()/6 - repo.getWidth()/2,Gdx.graphics.getHeight()*3/8 - repo.getHeight()/2);
        quete.setPosition(Gdx.graphics.getWidth()/2 - quete.getWidth()/2,Gdx.graphics.getHeight()*3/8 - quete.getHeight()/2);
        marchand.setPosition(Gdx.graphics.getWidth()*5/6 - marchand.getWidth()/2,Gdx.graphics.getHeight()*3/8 - marchand.getHeight()/2);

        stage.addActor(repo);               //Add the button to the stage to perform rendering and take input.
        stage.addActor(quete);
        stage.addActor(marchand);


        spriteStat = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.barreStats))));

        spriteStat.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/4);
        spriteStat.setPosition(0,Gdx.graphics.getHeight()*3/4);

        stage.addActor(spriteStat);


        Gdx.input.setInputProcessor(stage); //Start taking input from the ui


        spriteStat.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.perso = new Perso(game));     //envoie sur la page repo
                dispose();
                return true;
            }
        });

        repo.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.repo = new Repo(game));     //envoie sur la page repo
                dispose();
                return true;
            }
        });

        quete.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.quete = new Quete(game,(int)(Math.random()*7) +  3));
                dispose();                                      //envoie sur la page quete avec le nombre de tour a survivre pour finir la quete
                return true;
            }
        });

        marchand.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.marchand);                  //envoie sur la page marchand
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

        stage.act(delta); //Perform ui logic
        stage.draw(); //Draw the ui

        game.batch.begin();
        font.getData().setScale(2);
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
    public void dispose() { stage.dispose();

    }
}
