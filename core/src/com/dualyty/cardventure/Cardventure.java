package com.dualyty.cardventure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screens.*;

public class Cardventure extends Game {

	public final static boolean musicOn = true;					//permet de choisir si on veut qu'il y ait de la music
	public final static boolean saveOn = true;					//permet de choisir si on veut sauvegarder notre progression

	public static final String NAME = "Cardventure", VERSION = "0.9";
																//en cours de devellopement

	public Player player =  new Player();						//le joueur

	public Acceuil acc = new Acceuil(this);				//la page d'accueil / ecran titre
	public Tutoriel tutoriel = new Tutoriel(this,1);
																//tutoriel pour expliquer rapidement le jeu
	public Menu menu = new Menu(this);					//le menu, ou on choisi si on veut continuer ou créer une nouvelle partie
	public Choix choix = new Choix(this);				//page de choix d'une carte parmis 3
	public Repo repo = new Repo (this);					//page de choix d'une carte repo
	public Quete quete = new Quete(this,0);	//page de choix d'une carte quete
	public Perso perso = new Perso(this);				//page personnage
	public Sac sac = new Sac(this,null);			//page inventaire

	public Marchand marchand = new Marchand(this);		//page marchand
	public Marchand_achat marchand_achat = new Marchand_achat(this);
	public Marchand_vente marchand_vente = new Marchand_vente(this);
																//pages pour acheter ou vendre de l'equipement

	public GameOver gameOver = new GameOver(this);				//page si plus de vie
	public FinQuete finQuete = new FinQuete(this,0);
																//page de fin de quete

	public SpriteBatch batch;

	public Image texture = new Image();							//la ou les .png sont gérés

	public static AssetManager manager;							//assetManager pour gérer la musique

	Cardventure(){}
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		if (musicOn == true) {

			manager = new AssetManager();

			manager.load("audio/theme.ogg", Music.class);

			manager.load("audio/welcome.ogg", Sound.class);
			manager.load("audio/defeat.ogg", Sound.class);
			manager.load("audio/select.ogg", Sound.class);

			manager.finishLoading();
		}


		if (saveOn == true) {
			GameManager.getInstance().initializeGameData(this);
		}

		this.setScreen(acc);									//envoi vers la page d'accueil
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}

	@Override
	public void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.pause();
	}

}
