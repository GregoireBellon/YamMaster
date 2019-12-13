package com.example.yatzy.model;

import java.util.Enumeration;

public class Partie {

	private Plateau plateau;
	private Joueur joueur1;
	private Joueur joueur2;

	public Partie(Joueur joueur1, Joueur joueur2) {
		plateau = new Plateau();
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
	}

	public void commencerPartie() {
		joueur1.setCouleurJeton(Couleur.BLANC);
		joueur2.setCouleurJeton(Couleur.NOIR);

		while (joueur1.peutJouer() && joueur2.peutJouer()) {
			joueur1.jouerUnTour();
			plateau.checkAlignement(joueur1.getCouleurJeton());
			plateauJeu(joueur1);
			plateauJeuPoserJeton(joueur1);
			// joueur2.jouerUnTour();
			// plateau.checkAlignement(joueur2.getCouleurJeton());
			// plateauJeu(joueur2);
		}
	}

	public void finPartie() {
	}

	public Plateau getPlateau() {
		return this.plateau;
	}

	/**
	 * affiche le plateau d'un joueur apr�s son tour, lui montre les jetons d�j� sur
	 * le plateau mais aussi � quel endroit il peut en poser ce tour l�
	 * 
	 * @param joueur
	 */
	public void plateauJeu(Joueur joueur) {

		// this.plateau.CASES[0].setJeton( new Jeton(Couleur.BLANC));
		String s = "";
		System.out.println("Vous pouvez placer des jetons aux endroits ou un X est indiqu�");
		for (int i = 0; i < 5; i++) {

			for (int k = 0; k < 5; k++) {

				if (joueur.getCombinaisonEnCours().size() > 0) {
					s = "";
					// joueur.afficherCombinaisonsActuelles();
					for (Combinaison c : joueur.getCombinaisonEnCours()) {

						if (c.equals(this.getPlateau().CASES[k][i].getCombinaison())) {
							// System.out.println(c);
							// System.out.println(this.getPlateau().CASES[i + k].getCombinaison());
							s = "X";
						}

					}
				}
				if (this.getPlateau().CASES[k][i].isEstRemplie()) {
					Couleur c = this.getPlateau().CASES[k][i].getJeton().getCouleur();
					s = c.name().toString();
				}
				System.out.print(getPlateau().CASES[k][i].getCombinaison() + "[" + s + "] | ");

			}
			System.out.println();
		}

	}

	public void plateauJeuPoserJeton(Joueur j)
		{
			boolean bonneCaseSelect = false;
			Scanner_perso sc = new Scanner_perso();
			String rep ="";
			String s ="  ";
			
			System.out.println();
			for(int i=0; i<5; i++)
			{
				for(int k=0; k<5; k++) {
					s="  ";
					for(Combinaison c  : j.getCombinaisonEnCours())
					{
						if((c.equals(this.getPlateau().CASES[k][i].getCombinaison()))&&(!this.getPlateau().CASES[k][i].isEstRemplie())) {
							s=String.valueOf(k)+String.valueOf(i);
							this.getPlateau().CASES[k][i].setJoueurPeutPoser(true);
						}
					}
					System.out.print( "[" + s + "]");
				}
				System.out.println();
				
			}
			while(!bonneCaseSelect) {
				
				
			System.out.println("Choisissez un des nombres qui va apparaitre pour placer votre jeton : tout autre nombre"
					+ "non affich� ne sera pas efficient ");
				rep=sc.Reponse();
				int repInt = Integer.parseInt(rep);
				if(rep.length()==2 && repInt<=45)
				{
					char c1 =(char) Character.getNumericValue(rep.charAt(0));
					char c2 =(char) Character.getNumericValue(rep.charAt(1));
					if(this.getPlateau().CASES[c1][c2].isJoueurPeutPoser())
					{
						j.poserJeton(this.getPlateau().CASES[c1][c2]);
						this.getPlateau().CASES[c1][c2].setEstRemplie(true);
						bonneCaseSelect=true;
						}
						
				}
				
				
				
		}
	}

}
