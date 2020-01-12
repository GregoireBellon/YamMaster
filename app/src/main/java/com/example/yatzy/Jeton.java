package com.example.yatzy;

import android.widget.ImageView;
import com.example.yatzy.model.Couleur;

public class Jeton {

	private Couleur couleur;
	private ImageView view;
	private Plateau plateau;
	
	public Jeton(Couleur couleur, Plateau plateau)
	{
		this.couleur = couleur;
		this.plateau = plateau;
		view = new ImageView(plateau.getJeu());
		determinerCouleurJeton();
	}

	private void determinerCouleurJeton() {
		switch (couleur){
			case NOIR:
				view.setImageResource(R.drawable.jeton_bleu);
				break;
			case BLANC:
				view.setImageResource(R.drawable.jeton_blanc);
				break;
		}
		view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	}

	public Couleur getCouleur()
	{
		return this.couleur;
	}

	public ImageView getView() {
		return view;
	}
}
