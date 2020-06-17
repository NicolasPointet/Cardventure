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
import com.dualyty.cardventure.Carte;

public class Quete implements Screen {

    /*
    page quete
     */

    final Cardventure game;

    public Sprite fond;
    private Stage stage;

    int compteur;           //le nombre de tour avant de finir la quete, décroit
    int recompense;         //determine le niveau de la recompense lorsaue le joueur reussi une quete

    ImageButton spriteCarte1;
    ImageButton spriteCarte2;
    ImageButton spriteCarte3;

    ImageButton spriteStat;

    Carte carte1;
    Carte carte2;
    Carte carte3;

    BitmapFont font;

    public Quete (Cardventure game, int compteur) {                     //lance une nouvelle quete
        this.game = game;
        this.compteur = compteur;
        if (compteur < 0) {
            game.setScreen(game.choix = new Choix(this.game));
        }
        this.recompense = 0;
    }

    public Quete (Cardventure game, int compteur, int recompense) {     //utiliser lorsaue la quete est en cours
        this.game = game;
        this.compteur = compteur;
        if (compteur < 0) {
            game.setScreen(game.choix = new Choix(this.game));
        }
        this.recompense = recompense;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());

        font = new BitmapFont();

        fond = new Sprite( new Texture(game.texture.fond));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        carte1 = new Carte(this.game);
        carte2 = new Carte(this.game);
        carte3 = new Carte(this.game);


        carte1.generateCarte("quete",0);
        carte2.generateCarte("quete",0);
        carte3.generateCarte("quete",0);


        spriteCarte1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
        spriteCarte2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));
        spriteCarte3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.carte))));



        spriteCarte1.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte1.setPosition(Gdx.graphics.getWidth()*0,0);

        spriteCarte2.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte2.setPosition(Gdx.graphics.getWidth()*1/3,0);

        spriteCarte3.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()*3/4);
        spriteCarte3.setPosition(Gdx.graphics.getWidth()*2/3,0);

        stage.addActor(spriteCarte1);
        stage.addActor(spriteCarte2);
        stage.addActor(spriteCarte3);


        spriteStat = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.barreStats))));

        spriteStat.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/4);
        spriteStat.setPosition(0,Gdx.graphics.getHeight()*3/4);

        stage.addActor(spriteStat);


        Gdx.input.setInputProcessor(stage); //Start taking input from the ui


        spriteCarte1.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                compteur -= 1;      //decremente le compteur
                if (compteur < 0) {
                    System.out.println("compteur negatif");
                    return true;
                }
                System.out.println("compteur = " + compteur);
                if (carte1.fin) {                   //si le joueur choisie une carte qui met fin a la quete sans la valider
                    game.setScreen(game.choix);     //renvoie au menu, perd la recompense
                }
                else {
                    if (game.player.action(carte1)){
                        recompense += carte1.niveauRecompense;          //ajoute la recompense de la carte au total
                        if (compteur == 0){                             //si le joueur est arrive au bout de la quete
                            System.out.println("quete vers fin quete");
                            game.setScreen(game.finQuete = new FinQuete(game,recompense));  //ecran de fin de quete -> donne la recompense au joueur
                            stage.dispose();
                            }
                        else {
                            game.setScreen(game.quete = new Quete(game, compteur,recompense));
                                                                        //sinon on continue en donnant le compteur et la recompense actuelle
                        }
                    }
                    else {game.setScreen(game.gameOver);            //s'il est mort, fin de la partie
                    }
                }
                dispose();
                System.out.println("dispose");
                return true;
            }
        });

        spriteCarte2.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                compteur -= 1;
                if (compteur < 0) {
                    System.out.println("compteur negatif");
                    return true;
                }
                System.out.println("compteur = " + compteur);
                if (carte2.fin) {
                    game.setScreen(game.choix);
                }
                else {
                    if (game.player.action(carte2)){
                        recompense += carte2.niveauRecompense;
                        if (compteur == 0){
                            System.out.println("quete vers fin quete");
                            game.setScreen(game.finQuete = new FinQuete(game,recompense));
                            stage.dispose();
                        }
                        else {game.setScreen(game.quete = new Quete(game, compteur, recompense));}
                    }
                    else {game.setScreen(game.gameOver);
                    }
                }

                dispose();
                System.out.println("dispose");
                return true;
            }
        });

        spriteCarte3.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                compteur -= 1;
                if (compteur < 0) {
                    System.out.println("compteur negatif");
                    return true;
                }
                System.out.println("compteur = " + compteur);
                if (carte3.fin) {
                    game.setScreen(game.choix);
                }
                else {
                    if (game.player.action(carte3)){
                        recompense += carte3.niveauRecompense;
                        if (compteur == 0){
                            System.out.println("quete vers fin quete");
                            game.setScreen(game.finQuete = new FinQuete(game,recompense));
                            stage.dispose();
                        }
                        else {game.setScreen(game.quete = new Quete(game, compteur, recompense));}
                    }
                    else {game.setScreen(game.gameOver);
                    }
                }

                dispose();
                System.out.println("dispose");
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

        stage.act(delta);
        stage.draw();

        game.batch.begin();
        font.getData().setScale(2);
        font.draw(game.batch,carte1.texte,spriteCarte1.getWidth()/4,spriteCarte1.getHeight()*3/4);
        font.draw(game.batch,carte2.texte,spriteCarte2.getX() + spriteCarte2.getWidth()/4,spriteCarte2.getHeight()*3/4);
        font.draw(game.batch,carte3.texte,spriteCarte3.getX() +spriteCarte3.getWidth()/4,spriteCarte3.getHeight()*3/4);

        font.draw(game.batch,"" + this.compteur + " " + this.recompense,500,500);

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
    public void dispose() { }
}
