package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dualyty.cardventure.Cardventure;
import com.dualyty.cardventure.Equipement;
import com.dualyty.cardventure.GameManager;

import java.util.ArrayList;

public class FinQuete implements Screen {

    /*
    page lorsque le joueur est venue a bout d'une quete
     */

    private Sprite fond;
    private Sprite warrior;

    final Cardventure game;

    int recompense;
    int or;
    int prestige;
    ArrayList<Equipement> equipement = new ArrayList<Equipement>();

    boolean clicked = false;

    BitmapFont font;

    public FinQuete (Cardventure game, int recompense) {
        System.out.println("ecran fin quete");
        this.game = game;
        this.recompense = recompense;
        this.or = (int)(this.recompense/2);
        this.prestige = (int)(this.recompense/5);
        for (int i = 0; i < (int)(this.recompense/10); i++){    //tout les 10 points de recompense, ajoute un nouvel equipement
            equipement.add(new Equipement(this.game));
        }

        game.player.giveRecompense(or,prestige,equipement);
        System.out.println("don recompense");
    }

    @Override
    public void show() {

        font = new BitmapFont();

        fond = new Sprite( new Texture(game.texture.fond));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        warrior = new Sprite(new Texture(game.texture.victoire));
        warrior.setPosition(0,0);
        warrior.setSize(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        fond.draw(game.batch);
        warrior.draw(game.batch);
        game.batch.end();


        game.batch.begin();
        font.getData().setScale(2);
        font.draw(game.batch," Felicitation, vous avez reussi cette quete ! ",Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*4/5);
        font.draw(game.batch," Votre score est de : " + this.recompense,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*3/5);
        font.draw(game.batch," Votre recompense est de : " + this.or + " or(s) et " + this.equipement.size() +  "  equipement(s) ",Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*2/5);
        font.draw(game.batch," Vos exploits vous aide a vous faire connaitre, vous gagnez " + this.prestige + " prestige ",Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*1/5);
        game.batch.end();


        if (Gdx.input.isTouched()) {
            System.out.println("clicked");
            if (clicked == true){
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.choix = new Choix(game));

                if (game.saveOn == true) {
                    GameManager.getInstance().player.setPlayerData(game.player);
                    GameManager.getInstance().stuff.setStuffData(game.player);

                    GameManager.getInstance().savePlayer();
                }


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
    public void dispose() { //stage.dispose();
        }
}
