package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dualyty.cardventure.Cardventure;

public class Tutoriel implements Screen {

    final Cardventure game;

    boolean clicked;

    int numero;

    Sprite ecran;

    String texture;



    public Tutoriel (Cardventure game, int numero) {
        this.game = game;
        this.numero = numero;

        texture = "image/tuto/tuto" + numero + ".png";

    }

    @Override
    public void show() {

        ecran = new Sprite(new Texture("image/tuto/tuto"+numero+".png"));
        ecran.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        ecran.draw(game.batch);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            System.out.println("clicked");
            if (clicked == true){
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                if (numero == 5) {
                    game.setScreen(game.choix = new Choix(game));
                }
                else {
                    game.setScreen(game.tutoriel = new Tutoriel(game,numero+1));
                }

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
