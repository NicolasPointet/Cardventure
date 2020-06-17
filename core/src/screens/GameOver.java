package screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dualyty.cardventure.Cardventure;
import tween.SpriteAccessor;

public class GameOver implements Screen {

    /*
    fin du jeu, le joueur est mort
     */

    final Cardventure game;

    boolean clicked = false;

    public Sprite fond;
    public Sprite fondfin;
    public Sprite warrior;
    private TweenManager tweenManager;


    public GameOver (Cardventure game) {
        this.game = game;
    }

    @Override
    public void show() {

        if (game.musicOn == true) {
            game.manager.get("audio/defeat.ogg", Sound.class).play();
        }
        Texture fondTexture = new Texture(game.texture.fond);
        Texture fondFinTexture = new Texture(game.texture.ecranFin);

        fond = new Sprite(fondTexture);
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        fondfin = new Sprite(fondFinTexture);
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        warrior = new Sprite(new Texture(game.texture.defaite));
        warrior.setPosition(0,0);
        warrior.setSize(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Tween.set(fondfin,SpriteAccessor.ROTATE).target(-2).start(tweenManager);
        Tween.to(fondfin,SpriteAccessor.ROTATE,5).target(2).repeatYoyo(100,0).start(tweenManager);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.BROWN.r,Color.BROWN.g,Color.BROWN.b,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);         //delta = le temps (s) entre 2 frames

        game.batch.begin();
        fond.draw(game.batch);
        fondfin.draw(game.batch);
        warrior.draw(game.batch);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            System.out.println("clicked");
            if (clicked == true){
                game.setScreen(game.menu = new Menu(game));
                dispose();
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 1000){}
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        clicked = true;
                    }
                });
            }
        }).start();

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
