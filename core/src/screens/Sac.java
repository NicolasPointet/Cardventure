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

public class Sac implements Screen {

    final Cardventure game;
    private String type;

    public Sprite fond;
    private Stage stage;

    ArrayList<Equipement> listeStuff = new ArrayList<>();       //liste des equipement avec le type recherché
    ArrayList<Equipement> listeStuffs = new ArrayList<>();      //liste de tout les equipements

    ImageButton previousScreen;

    BitmapFont font;

    ImageButton sizeCarte;
    float size;

    public Sac (Cardventure game, String type) {
        this.game = game;
        this.type = type;
        listeStuffs.addAll(game.player.sac);

        if (game.player.tete != null) {
            listeStuffs.add(game.player.tete);
        }
        if (game.player.plastron != null) {
            listeStuffs.add(game.player.plastron);
        }
        if (game.player.jambe != null) {
            listeStuffs.add(game.player.jambe);
        }
        if (game.player.maingauche != null) {
            listeStuffs.add(game.player.maingauche);
        }
        if (game.player.maindroite != null) {
            listeStuffs.add(game.player.maindroite);
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


        for (Equipement stuff : listeStuffs) {
            if (stuff.getType() == this.type || type == null) {
                listeStuff.add(stuff);
            }
        }


        int compteur = listeStuff.size() + 1;
        for (Equipement stuff: listeStuff) {
            compteur -= 1;
            float positionX = Gdx.graphics.getWidth()*compteur/(listeStuff.size()+1)-size;
            float positionY = Gdx.graphics.getHeight()/2;
            ImageButton spriteCarte;
            if (stuff.equipe == false) {
                spriteCarte = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
            }
            else {
                spriteCarte = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carteEquipe))));
            }
            spriteCarte.setPosition(positionX,positionY-spriteCarte.getHeight());
            stage.addActor(spriteCarte);
            final int finalCompteur = compteur;
            spriteCarte.addListener(new EventListener() {
                @Override
                public boolean handle(Event isClicked) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                    System.out.println("t'as cliqué");
                    if (game.player.equipe(listeStuff.get(listeStuff.size()- finalCompteur))) {
                        if (listeStuff.size() > 0) {
                            game.setScreen(game.sac = new Sac(game,type));
                        }
                        else {
                            game.setScreen(game.perso = new Perso(game));
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
                game.manager.get("audio/select.ogg", Sound.class).play();
                game.setScreen(game.perso = new Perso(game));
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
        game.batch.end();

        stage.act(delta);
        stage.draw();


        game.batch.begin();
        font.getData().setScale(2);
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
