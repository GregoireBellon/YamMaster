package com.example.yatzy.model;
public class Case {
	
	private Combinaison combinaison;
	private boolean estRemplie;
	private boolean joueurPeutPoser;
	private Jeton jeton;
	
	public Case(Combinaison combinaison) {
		this.combinaison=combinaison;
		this.estRemplie=false;
		this.jeton=null;
		this.joueurPeutPoser=false;
	}
	
	public boolean isJoueurPeutPoser() {
		return joueurPeutPoser;
	}

	public void setJoueurPeutPoser(boolean joueurPeutPoser) {
		this.joueurPeutPoser = joueurPeutPoser;
	}
	public Combinaison getCombinaison() {
		return combinaison;
	}
	public void setCombinaison(Combinaison combinaison) {
		this.combinaison = combinaison;
	}
	public boolean isEstRemplie() {
		return estRemplie;
	}
	public void setEstRemplie(boolean estRemplie) {
		this.estRemplie = estRemplie;
	}
	public Jeton getJeton() {
		return jeton;
	}
	public void setJeton(Jeton jeton) {
		this.jeton = jeton;
		if(jeton!=null)
		{
			this.setEstRemplie(true);
		}
		else this.setEstRemplie(false);
	}
	
	
}