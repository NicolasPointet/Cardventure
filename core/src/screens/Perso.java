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

public class Perso implements Screen {

    Cardventure game;

    ImageButton sac;
    ImageButton teteButton;
    ImageButton corpButton;
    ImageButton jambeButton;
    ImageButton mainDroiteButton;
    ImageButton mainGaucheButton;

    Sprite tete;
    Sprite corp;
    Sprite jambe;
    Sprite mainDroite;
    Sprite mainGauche;

    public Sprite fond;
    private Stage stage;
    ImageButton previousScreen;
    BitmapFont font;

    public Perso(Cardventure game) {
        this.game = game;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());

        font = new BitmapFont();

        fond = new Sprite( new Texture("fond.png"));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        sac = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("sac.png"))));
        sac.setPosition(Gdx.graphics.getWidth()/2,0);
        corpButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("cartecorp.png"))));
        corpButton.setPosition(Gdx.graphics.getWidth()*2/16,Gdx.graphics.getHeight()*2/6);
        teteButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("cartecorp.png"))));
        teteButton.setPosition(corpButton.getX(),corpButton.getY()+corpButton.getHeight()+20);
        jambeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("cartecorp.png"))));
        jambeButton.setPosition(corpButton.getX(),corpButton.getY()-corpButton.getHeight()-20);
        mainDroiteButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("cartemain.png"))));
        mainDroiteButton.setPosition(corpButton.getX()+corpButton.getWidth()+20,corpButton.getY());
        mainGaucheButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("cartemain.png"))));
        mainGaucheButton.setPosition(corpButton.getX()-mainGaucheButton.getWidth()-20,corpButton.getY());


        stage.addActor(sac);
        stage.addActor(teteButton);
        stage.addActor(corpButton);
        stage.addActor(jambeButton);
        stage.addActor(mainDroiteButton);
        stage.addActor(mainGaucheButton);

        previousScreen = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("back.png"))));
        previousScreen.setPosition(Gdx.graphics.getWidth()*1/20,Gdx.graphics.getHeight()*9/10);
        stage.addActor(previousScreen);

        previousScreen.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.choix);
                dispose();
                return true;
            }
        });

        Gdx.input.setInputProcessor(stage);

        sac.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.sac = new Sac(game,null));
                dispose();
                return true;
            }
        });

        teteButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.sac = new Sac(game,"tete"));
                dispose();
                return true;
            }
        });

        corpButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.sac = new Sac(game,"plastron"));
                dispose();
                return true;
            }
        });

        jambeButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.sac = new Sac(game,"jambe"));
                dispose();
                return true;
            }
        });

        mainDroiteButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.sac = new Sac(game,"baton"));
                dispose();
                return true;
            }
        });

        mainGaucheButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.sac = new Sac(game,"bouclier"));
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

        stage.act(delta);
        stage.draw();

        game.batch.begin();
        font.getData().setScale(2);

        /*
        font.draw(game.batch,"  " + game.player.getLifeCurrent() + " / " + game.player.getLifeMax() + " vie",Gdx.graphics.getWidth()*3/16,Gdx.graphics.getHeight()*9/10);
        font.draw(game.batch,"" + game.player.getManaCurrent() + " / " + game.player.getManaMax() + " mana",Gdx.graphics.getWidth()*6/16,Gdx.graphics.getHeight()*9/10);
        font.draw(game.batch,"" + game.player.getArmorCurrent() + " / " + game.player.getArmorMax() + " armure",Gdx.graphics.getWidth()*9/16,Gdx.graphics.getHeight()*9/10);
        font.draw(game.batch,"" + game.player.getRmCurrent() + " / " + game.player.getRmMax() + " ressistance",Gdx.graphics.getWidth()*12/16,Gdx.graphics.getHeight()*9/10);
        font.draw(game.batch,"" + game.player.getOr() + " or",Gdx.graphics.getWidth()*15/16,Gdx.graphics.getHeight()*9/10);
         */

        if (game.player.tete != null) {
            font.draw(game.batch,game.player.tete.texteEquipe,teteButton.getX(),teteButton.getY()+teteButton.getHeight());
        }
        if (game.player.plastron != null) {
            font.draw(game.batch,game.player.plastron.texteEquipe,corpButton.getX(),corpButton.getY()+corpButton.getHeight());
        }
        if (game.player.jambe != null) {
            font.draw(game.batch,game.player.jambe.texteEquipe,jambeButton.getX(),jambeButton.getY()+jambeButton.getHeight());
        }
        if (game.player.maingauche != null) {
            font.draw(game.batch,game.player.maingauche.texteEquipe,mainGaucheButton.getX(),mainGaucheButton.getY()+mainGaucheButton.getHeight());
        }
        if (game.player.maindroite != null) {
            font.draw(game.batch,game.player.maindroite.texteEquipe,mainDroiteButton.getX(),mainDroiteButton.getY()+mainDroiteButton.getHeight());
        }

        font.draw(game.batch," PRESTIGE : " + game.player.getPrestige(),Gdx.graphics.getWidth()*8/10,Gdx.graphics.getHeight()/10);

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
