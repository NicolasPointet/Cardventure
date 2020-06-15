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
import com.dualyty.cardventure.Carte;


public class Test implements Screen {

    /*
    page de test, est vouée a disparaitre
     */

    final Cardventure game;

    public Sprite fond;
    private String type;
    private Stage stage;

    ImageButton spriteCarte1;
    ImageButton spriteCarte2;
    ImageButton spriteCarte3;

    Carte carte1;
    Carte carte2;
    Carte carte3;

    ImageButton spriteStat;
    ImageButton spriteStatLife;
    ImageButton spriteStatMana;
    ImageButton spriteStatArmor;
    ImageButton spriteStatRm;
    ImageButton spriteStatOr;

    BitmapFont font;

    public Test(Cardventure game) {
        this.game = game;
    }

    public Test (Cardventure game, String type) {
        this.game = game;
        this.type = type;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());

        font = new BitmapFont();

        fond = new Sprite( new Texture("fond.png"));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        /*
        switch (type) {
            case "repo" : carte1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carteRepo.png"))));
                break;
            case "quete" : carte1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carteQuete.png"))));
                break;
            case "marchand" : carte1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carteMarchand.png"))));
                break;
            default: carte1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("carte.png"))));
                break;
        }
        */

        carte1 = new Carte(this.game);
        carte2 = new Carte(this.game);
        carte3 = new Carte(this.game);

        carte1.generateCarte(type,0);
        carte2.generateCarte(type,0);
        carte3.generateCarte(type,0);


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


        spriteStat = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("barre_stats.png"))));

        spriteStat.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/4);
        spriteStat.setPosition(0,Gdx.graphics.getHeight()*3/4);

        stage.addActor(spriteStat);


        /*

        spriteStatLife = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("stat_vie.png"))));
        spriteStatMana = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("stat_mana.png"))));
        spriteStatArmor = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("stat_armure.png"))));
        spriteStatRm = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("stat_resistanceMagique.png"))));
        spriteStatOr = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("stat_or.png"))));

        spriteStatLife.setSize(Gdx.graphics.getWidth()*1/10,Gdx.graphics.getHeight()/4);
        spriteStatLife.setPosition(Gdx.graphics.getWidth()*0 + Gdx.graphics.getWidth()*1/50,Gdx.graphics.getHeight()*3/4);

        spriteStatMana.setSize(Gdx.graphics.getWidth()*1/10,Gdx.graphics.getHeight()/4);
        spriteStatMana.setPosition(Gdx.graphics.getWidth()*2/10 + Gdx.graphics.getWidth()*1/50,Gdx.graphics.getHeight()*3/4);

        spriteStatArmor.setSize(Gdx.graphics.getWidth()*1/10,Gdx.graphics.getHeight()/4);
        spriteStatArmor.setPosition(Gdx.graphics.getWidth()*4/10 + Gdx.graphics.getWidth()*1/50,Gdx.graphics.getHeight()*3/4);

        spriteStatRm.setSize(Gdx.graphics.getWidth()*1/10,Gdx.graphics.getHeight()/4);
        spriteStatRm.setPosition(Gdx.graphics.getWidth()*6/10 + Gdx.graphics.getWidth()*1/50,Gdx.graphics.getHeight()*3/4);

        spriteStatOr.setSize(Gdx.graphics.getWidth()*1/10,Gdx.graphics.getHeight()/4);
        spriteStatOr.setPosition(Gdx.graphics.getWidth()*8/10 + Gdx.graphics.getWidth()*1/50,Gdx.graphics.getHeight()*3/4);


        stage.addActor(spriteStatLife);
        stage.addActor(spriteStatMana);
        stage.addActor(spriteStatArmor);
        stage.addActor(spriteStatRm);
        stage.addActor(spriteStatOr);

        spriteStatLife.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.repo = new Repo(game));
                dispose();
                return true;
            }
        });

        spriteStatMana.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.repo = new Repo(game));
                dispose();
                return true;
            }
        });

        spriteStatArmor.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.repo = new Repo(game));
                dispose();
                return true;
            }
        });

        spriteStatRm.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.repo = new Repo(game));
                dispose();
                return true;
            }
        });

        spriteStatOr.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.repo = new Repo(game));
                dispose();
                return true;
            }
        });

         */

        spriteStat.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                game.setScreen(game.repo = new Repo(game));
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
        font.draw(game.batch,carte1.texte,spriteCarte1.getWidth()/4,Gdx.graphics.getHeight()/2);
        font.draw(game.batch,carte2.texte,spriteCarte2.getX() + spriteCarte2.getWidth()/4,Gdx.graphics.getHeight()/2);
        font.draw(game.batch,carte3.texte,spriteCarte3.getX() +spriteCarte3.getWidth()/4,Gdx.graphics.getHeight()/2);

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
    public void dispose() {
        stage.dispose();
    }
}
