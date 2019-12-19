package com.example.yatzy;

import com.example.yatzy.model.Couleur;

public class Jeton {

	public Couleur couleur;
	
	public Jeton(Couleur couleur)
	{
		this.couleur=couleur;
	}
	
	public Couleur getCouleur()
	{
		return this.couleur;
	}
}
