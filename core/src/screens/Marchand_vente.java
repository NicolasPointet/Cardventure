package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
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

public class Marchand_vente implements Screen {

    /*
    page marchand, pour vendre de l'equipement
    */

    final Cardventure game;

    public Sprite fond;
    private Stage stage;

    private Sprite or;

    ArrayList<Equipement> listeStuff = new ArrayList<>();

    ImageButton previousScreen;

    ImageButton sizeCarte;
    float size;

    BitmapFont font;

    public Marchand_vente(Cardventure game) {
        this.game = game;
        listeStuff.addAll(game.player.sac);

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

        or = new Sprite(new Texture(game.texture.or));
        or.setCenter(Gdx.graphics.getWidth()*15/16,Gdx.graphics.getHeight()*15/16);

        int compteur = listeStuff.size()+1;
        for (Equipement stuff: listeStuff) {
            compteur -= 1;

            ImageButton spriteCarte;
            if (stuff.equipe == false) {
                spriteCarte = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
            }
            else {
                spriteCarte = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carteEquipe))));
            }
            float positionX = Gdx.graphics.getWidth()*compteur/(listeStuff.size()+1)-size;
            float positionY = Gdx.graphics.getHeight()/2;
            spriteCarte.setPosition(positionX,positionY-spriteCarte.getHeight());
            stage.addActor(spriteCarte);
            final int finalCompteur = compteur;
            spriteCarte.addListener(new EventListener() {
                @Override
                public boolean handle(Event isClicked) {
                    if (game.musicOn == true) {
                        game.manager.get("audio/select.ogg", Sound.class).play();
                    }
                    if (game.player.sell(listeStuff.get(listeStuff.size()- finalCompteur))) {
                        if (listeStuff.size() > 0) {
                            game.setScreen(game.marchand_vente = new Marchand_vente(game));
                        }
                        else {
                            game.setScreen(game.choix);
                        }
                        dispose();
                        return true;
                    }
                    return false;
                }
            });
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



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        fond.draw(game.batch);
        or.draw(game.batch);
        game.batch.end();

        stage.act(delta);
        stage.draw();


        game.batch.begin();
        font.getData().setScale(2);
        font.setColor(Color.WHITE);
        int compteur = listeStuff.size();
        for (Equipement stuff: listeStuff) {
            float positionX = Gdx.graphics.getWidth()*compteur/(listeStuff.size()+1)-size;
            float positionY = Gdx.graphics.getHeight()/2;
            font.draw(game.batch,stuff.texte,positionX,positionY);
            Sprite sprite = new Sprite(stuff.texture);
            sprite.setPosition(positionX,positionY-sizeCarte.getHeight());
            sprite.draw(game.batch);
            compteur -= 1;
        }

        font.setColor(Color.BLACK);

        font.draw(game.batch,"" + game.player.getOr() + " or",Gdx.graphics.getWidth()*15/16,Gdx.graphics.getHeight()*15/16);

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

