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

public class Perso implements Screen {

    Cardventure game;

    ImageButton sac;
    ImageButton teteButton;
    ImageButton corpButton;
    ImageButton jambeButton;
    ImageButton mainDroiteButton;
    ImageButton mainGaucheButton;

    Sprite vie;
    Sprite armor;
    Sprite rm;
    Sprite mana;
    Sprite or;

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

        fond = new Sprite( new Texture(game.texture.fond));
        fond.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        vie = new Sprite(new Texture(game.texture.vie));
        vie.setPosition(Gdx.graphics.getWidth()*3/4,Gdx.graphics.getHeight()*5/6);
        vie.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        armor = new Sprite(new Texture(game.texture.armure));
        armor.setPosition(Gdx.graphics.getWidth()*3/4,Gdx.graphics.getHeight()*4/6);
        armor.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        rm = new Sprite(new Texture(game.texture.rm));
        rm.setPosition(Gdx.graphics.getWidth()*3/4,Gdx.graphics.getHeight()*3/6);
        rm.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        mana = new Sprite(new Texture(game.texture.mana));
        mana.setPosition(Gdx.graphics.getWidth()*3/4,Gdx.graphics.getHeight()*2/6);
        mana.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);
        or = new Sprite(new Texture(game.texture.or));
        or.setPosition(Gdx.graphics.getWidth()*3/4,Gdx.graphics.getHeight()*1/6);
        or.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/7);


        sac = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.sac))));
        sac.setPosition(Gdx.graphics.getWidth()/2,0);
        corpButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.persoTronc))));
        corpButton.setPosition(Gdx.graphics.getWidth()*2/16,Gdx.graphics.getHeight()*2/6);
        teteButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.persoTronc))));
        teteButton.setPosition(corpButton.getX(),corpButton.getY()+corpButton.getHeight()+20);
        jambeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.persoTronc))));
        jambeButton.setPosition(corpButton.getX(),corpButton.getY()-corpButton.getHeight()-20);
        mainDroiteButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.persoMain))));
        mainDroiteButton.setPosition(corpButton.getX()+corpButton.getWidth()+20,corpButton.getY());
        mainGaucheButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.persoMain))));
        mainGaucheButton.setPosition(corpButton.getX()-mainGaucheButton.getWidth()-20,corpButton.getY());


        stage.addActor(sac);
        stage.addActor(teteButton);
        stage.addActor(corpButton);
        stage.addActor(jambeButton);
        stage.addActor(mainDroiteButton);
        stage.addActor(mainGaucheButton);

        previousScreen = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(game.texture.back))));
        previousScreen.setPosition(Gdx.graphics.getWidth()*1/20,Gdx.graphics.getHeight()*9/10);
        stage.addActor(previousScreen);

        previousScreen.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.choix);
                dispose();
                return true;
            }
        });

        Gdx.input.setInputProcessor(stage);

        sac.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.sac = new Sac(game,null));
                dispose();
                return true;
            }
        });

        teteButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.sac = new Sac(game,"tete"));
                dispose();
                return true;
            }
        });

        corpButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.sac = new Sac(game,"plastron"));
                dispose();
                return true;
            }
        });

        jambeButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.sac = new Sac(game,"jambe"));
                dispose();
                return true;
            }
        });

        mainDroiteButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
                game.setScreen(game.sac = new Sac(game,"baton"));
                dispose();
                return true;
            }
        });

        mainGaucheButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event isClicked) {
                if (game.musicOn == true) {
                    game.manager.get("audio/select.ogg", Sound.class).play();
                }
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
        vie.draw(game.batch);
        armor.draw(game.batch);
        rm.draw(game.batch);
        mana.draw(game.batch);
        or.draw(game.batch);
        game.batch.end();

        stage.act(delta);
        stage.draw();

        game.batch.begin();
        font.getData().setScale(2);


        if (game.player.tete != null) {
            Sprite sprite = new Sprite(game.player.tete.texture);
            sprite.setPosition(teteButton.getX(),teteButton.getY());
            sprite.draw(game.batch);
            font.draw(game.batch,game.player.tete.texteEquipe,teteButton.getX(),teteButton.getY()+teteButton.getHeight());
        }
        if (game.player.plastron != null) {
            Sprite sprite = new Sprite(game.player.plastron.texture);
            sprite.setPosition(corpButton.getX(),corpButton.getY());
            sprite.draw(game.batch);
            font.draw(game.batch,game.player.plastron.texteEquipe,corpButton.getX(),corpButton.getY()+corpButton.getHeight());
        }
        if (game.player.jambe != null) {
            Sprite sprite = new Sprite(game.player.jambe.texture);
            sprite.setPosition(jambeButton.getX(),jambeButton.getY());
            sprite.draw(game.batch);
            font.draw(game.batch,game.player.jambe.texteEquipe,jambeButton.getX(),jambeButton.getY()+jambeButton.getHeight());
        }
        if (game.player.maingauche != null) {
            Sprite sprite = new Sprite(game.player.maingauche.texture);
            sprite.setPosition(mainGaucheButton.getX(),mainGaucheButton.getY());
            sprite.draw(game.batch);
            font.draw(game.batch,game.player.maingauche.texteEquipe,mainGaucheButton.getX(),mainGaucheButton.getY()+mainGaucheButton.getHeight());
        }
        if (game.player.maindroite != null) {
            Sprite sprite = new Sprite(game.player.maindroite.texture);
            sprite.setPosition(mainDroiteButton.getX(),mainDroiteButton.getY());
            sprite.draw(game.batch);
            font.draw(game.batch,game.player.maindroite.texteEquipe,mainDroiteButton.getX(),mainDroiteButton.getY()+mainDroiteButton.getHeight());
        }

        font.draw(game.batch," " + game.player.getLifeCurrent() + " / " + game.player.getLifeMax(),vie.getX() + vie.getWidth() + 100, vie.getY() + vie.getHeight()/2);
        font.draw(game.batch," " + game.player.getArmorCurrent() + " / " + game.player.getArmorMax(),armor.getX() + armor.getWidth() + 100, armor.getY() + armor.getHeight()/2);
        font.draw(game.batch," " + game.player.getRmCurrent() + " / " + game.player.getRmMax(),rm.getX() + rm.getWidth() + 100, rm.getY() + rm.getHeight()/2);
        font.draw(game.batch," " + game.player.getManaCurrent() + " / " + game.player.getManaMax(),mana.getX() + mana.getWidth() + 100, mana.getY() + mana.getHeight()/2);
        font.draw(game.batch," " + game.player.getOr(),or.getX() + or.getWidth() + 100, or.getY() + or.getHeight()/2);

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
