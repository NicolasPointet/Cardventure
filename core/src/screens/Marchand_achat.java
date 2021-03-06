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
import com.dualyty.cardventure.Equipement;

import java.util.ArrayList;

public class Marchand_achat implements Screen {

    /*
    page marchand, pour acheter de l'equipement
    */

    final Cardventure game;

    public Sprite fond;
    private Stage stage;

    ImageButton buttonCarte1;
    ImageButton buttonCarte2;
    ImageButton buttonCarte3;

    Equipement carte1;
    Equipement carte2;
    Equipement carte3;

    Equipement myCarte4;
    Equipement myCarte5;
    Equipement myCarte6;

    Sprite sprite1;
    Sprite sprite2;
    Sprite sprite3;

    ImageButton previousScreen;

    ImageButton sizeCarte;
    float size;

    ArrayList<Equipement> listeStuff = new ArrayList<>();

    BitmapFont font;

    public Marchand_achat(Cardventure game) {
        this.game = game;

        if (game.player.tete != null) {
            listeStuff.add(game.player.tete);
        }
        if (game.player.plastron != null) {
            listeStuff.add(game.player.plastron);
        }
        if (game.player.jambe != null) {
            listeStuff.add(game.player.jambe);
        }
        if (game.player.maingauche != null) {
            listeStuff.add(game.player.maingauche);
        }
        if (game.player.maindroite != null) {
            listeStuff.add(game.player.maindroite);
        }
    }

    @Override
    public void show() {

        sizeCarte = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
        size = sizeCarte.getWidth()/2;

        stage = new Stage(new ScreenViewport());

        font = new BitmapFont();

        fond = new Sprite( new Texture(game.texture.fond));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        carte1 = new Equipement(this.game);
        carte2 = new Equipement(this.game);
        carte3 = new Equipement(this.game);

        carte1.generateStuff();
        carte2.generateStuff();
        carte3.generateStuff();

        buttonCarte1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
        buttonCarte2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
        buttonCarte3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));


        buttonCarte1.setPosition(Gdx.graphics.getWidth()/6 - buttonCarte1.getWidth()/2,Gdx.graphics.getHeight()/2 - buttonCarte1.getHeight());
        buttonCarte2.setPosition(Gdx.graphics.getWidth()/2 - buttonCarte2.getWidth()/2,Gdx.graphics.getHeight()/2 - buttonCarte2.getHeight());
        buttonCarte3.setPosition(Gdx.graphics.getWidth()*5/6 - buttonCarte3.getWidth()/2,Gdx.graphics.getHeight()/2 - buttonCarte3.getHeight());


        stage.addActor(buttonCarte1);
        stage.addActor(buttonCarte2);
        stage.addActor(buttonCarte3);

        sprite1 = new Sprite(carte1.texture);
        sprite1.setPosition(buttonCarte1.getX(),buttonCarte1.getY());
        sprite2 = new Sprite(carte2.texture);
        sprite2.setPosition(buttonCarte2.getX(),buttonCarte2.getY());
        sprite3 = new Sprite(carte3.texture);
        sprite3.setPosition(buttonCarte3.getX(),buttonCarte3.getY());


        int compteur = listeStuff.size()+1;
        for (Equipement stuff: listeStuff) {
            compteur -= 1;

            ImageButton spriteCarte;
            spriteCarte = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carteEquipe))));

            float positionX = Gdx.graphics.getWidth()*compteur/(listeStuff.size()+1)-spriteCarte.getWidth()/2;
            float positionY = Gdx.graphics.getHeight()/2;

            spriteCarte.setPosition(positionX,positionY);
            stage.addActor(spriteCarte);
        }





        previousScreen = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.back))));
        previousScreen.setPosition(Gdx.graphics.getWidth()*1/20,Gdx.graphics.getHeight()*9/10);
        stage.addActor(previousScreen);

        previousScreen.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.marchand);
                dispose();
                return true;
            }
        });



        Gdx.input.setInputProcessor(stage); //Start taking input from the ui


        buttonCarte1.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                if (game.player.buy(carte1)) {
                    game.setScreen(game.choix);
                    dispose();
                    return true;
                }
                return false;
            }
        });

        buttonCarte2.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                if (game.player.buy(carte2)) {
                    game.setScreen(game.choix);
                    dispose();
                    return true;
                }
                return false;
            }
        });

        buttonCarte3.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                if (game.player.buy(carte3)) {
                    game.setScreen(game.choix);
                    dispose();
                    return true;
                }
                return false;
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
        sprite1.draw(game.batch);
        sprite2.draw(game.batch);
        sprite3.draw(game.batch);
        game.batch.end();



        game.batch.begin();
        font.getData().setScale(2);
        font.draw(game.batch,carte1.texte,buttonCarte1.getX(),buttonCarte1.getY()+buttonCarte1.getHeight());
        font.draw(game.batch,carte2.texte,buttonCarte2.getX(),buttonCarte2.getY()+buttonCarte2.getHeight());
        font.draw(game.batch,carte3.texte,buttonCarte3.getX(),buttonCarte3.getY()+buttonCarte3.getHeight());

        int compteur = listeStuff.size();
        for (Equipement stuff: listeStuff) {
            float positionX = Gdx.graphics.getWidth()*compteur/(listeStuff.size()+1)-size;
            float positionY = Gdx.graphics.getHeight()/2+sizeCarte.getHeight();
            font.draw(game.batch,stuff.texte,positionX,positionY);
            Sprite sprite = new Sprite(stuff.texture);
            sprite.setPosition(positionX,positionY-sizeCarte.getHeight());
            sprite.draw(game.batch);
            compteur -= 1;
        }
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
