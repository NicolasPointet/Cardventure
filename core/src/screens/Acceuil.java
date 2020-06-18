package screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dualyty.cardventure.Cardventure;
import tween.SpriteAccessor;

public class Acceuil implements Screen {

    /*
    page d'accueil du jeu / ecran titre
     */

    private Sprite fond;
    private Sprite titre;
    private Sprite passer;

    private TweenManager tweenManager;          //permet de créer des effets sur des sprites

    final Cardventure game;

    private Music music;

    public Acceuil(Cardventure game) {
        this.game = game;
    }

    @Override
    public void show() {

        if (game.musicOn == true) {
            music = game.manager.get("audio/theme.ogg", Music.class);
            music.setLooping(true);
            music.play();
        }

        fond = new Sprite(new Texture(game.texture.fond));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        titre = new Sprite(new Texture(game.texture.cardventure));
        titre.setCenter(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*3/5);

        passer = new Sprite(new Texture(game.texture.cliquepasse));
        passer.setCenter(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*1/5);


        /*
        SpriteAccessor pour faire des effets sur image
         */
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Tween.set(titre,SpriteAccessor.ROTATE).target(-2).start(tweenManager);
        Tween.to(titre,SpriteAccessor.ROTATE,5).target(2).repeatYoyo(100,0).start(tweenManager);

        Tween.set(passer,SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(passer,SpriteAccessor.ALPHA,5).target(1).repeatYoyo(100,0).start(tweenManager);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.BROWN.r,Color.BROWN.g,Color.BROWN.b,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);         //delta = le temps entre 2 frames

        game.batch.begin();
        fond.draw(game.batch);
        titre.draw(game.batch);
        passer.draw(game.batch);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            if (game.musicOn == true) {
                game.manager.get("audio/welcome.ogg", Sound.class).play();
            }
            game.setScreen(game.menu);
            dispose();
        }

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
