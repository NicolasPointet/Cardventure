package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class Marchand_achat implements Screen {

    /*
    page marchand, pour acheter de l'equipement
    */

    final Cardventure game;

    public Sprite fond;
    private Stage stage;

    ImageButton spriteCarte1;
    ImageButton spriteCarte2;
    ImageButton spriteCarte3;

    Equipement carte1;
    Equipement carte2;
    Equipement carte3;

    ImageButton previousScreen;

    BitmapFont font;

    public Marchand_achat(Cardventure game) {
        this.game = game;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());

        font = new BitmapFont();

        fond = new Sprite( new Texture("fond.png"));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        carte1 = new Equipement(this.game);
        carte2 = new Equipement(this.game);
        carte3 = new Equipement(this.game);

        carte1.generateStuff();
        carte2.generateStuff();
        carte3.generateStuff();


        spriteCarte1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carte.png"))));
        spriteCarte2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carte.png"))));
        spriteCarte3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carte.png"))));



        spriteCarte1.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte1.setPosition(Gdx.graphics.getWidth()*0,0);

        spriteCarte2.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte2.setPosition(Gdx.graphics.getWidth()*1/3,0);

        spriteCarte3.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte3.setPosition(Gdx.graphics.getWidth()*2/3,0);

        stage.addActor(spriteCarte1);
        stage.addActor(spriteCarte2);
        stage.addActor(spriteCarte3);

        previousScreen = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("back.png"))));
        previousScreen.setPosition(Gdx.graphics.getWidth()*1/20,Gdx.graphics.getHeight()*9/10);
        stage.addActor(previousScreen);

        previousScreen.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.marchand);
                dispose();
                return true;
            }
        });



        Gdx.input.setInputProcessor(stage); //Start taking input from the ui


        spriteCarte1.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.player.buy(carte1)) {
                    game.setScreen(game.choix);
                    dispose();
                    return true;
                }
                return false;
            }
        });

        spriteCarte2.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.player.buy(carte2)) {
                    game.setScreen(game.choix);
                    dispose();
                    return true;
                }
                return false;
            }
        });

        spriteCarte3.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
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
        font.getData().setScale(2);
        font.draw(game.batch,carte1.texte,spriteCarte1.getWidth()/4,Gdx.graphics.getHeight()/2);
        font.draw(game.batch,carte2.texte,spriteCarte2.getX() + spriteCarte2.getWidth()/4,Gdx.graphics.getHeight()/2);
        font.draw(game.batch,carte3.texte,spriteCarte3.getX() +spriteCarte3.getWidth()/4,Gdx.graphics.getHeight()/2);
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
