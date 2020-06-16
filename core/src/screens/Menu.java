package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dualyty.cardventure.Cardventure;
import com.dualyty.cardventure.GameManager;
import com.dualyty.cardventure.Player;

public class Menu implements Screen {

    /*
    Menu du jeu, avec choix de continuer partie ou d'en commencer une nouvelle
     */

    final Cardventure game;

    private Sprite fond;


    private Stage stage;
    private ImageButton newGame;
    private ImageButton continueGame;


    public Menu(Cardventure game) {
        this.game = game;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui

        Texture fondTexture = new Texture(game.texture.fond);
        fond = new Sprite(fondTexture);
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        newGame = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.nouvellePartie))));
        continueGame = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.continuerPartie))));

        newGame.setPosition(0,0);
        continueGame.setPosition(Gdx.graphics.getWidth()*2/4,0);

        newGame.setSize(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());
        continueGame.setSize(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());


        stage.addActor(newGame); //Add the button to the stage to perform rendering and take input.
        stage.addActor(continueGame);

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui


        newGame.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.manager.get("audio/select.ogg", Sound.class).play();
                game.player = new Player();         //crée un nouveau player
                game.setScreen(game.choix);
                dispose();
                return true;
            }
        });

        continueGame.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.manager.get("audio/select.ogg", Sound.class).play();


                GameManager.getInstance().loadPlayer();
                //game.player = GameManager.getInstance().gameData.getPlayer();
                //game.player.loadPlayer();             //charge la partie precedement sauvegardée

                GameManager.getInstance().player.getPlayerData(game.player);
                GameManager.getInstance().stuff.getStuffData(game.player);

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

        stage.act(delta); //Perform ui logic
        stage.draw(); //Draw the ui

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
    public void dispose() { stage.dispose(); }
}
