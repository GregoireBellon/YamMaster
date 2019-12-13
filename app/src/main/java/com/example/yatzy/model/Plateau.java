package com.example.yatzy.model;

public class Plateau {

	public static Case[][] CASES	= new Case[5][5];
	
	/**
	 * parcours de gauche � droite et de haut en bas
	 */
	public Plateau()
	{
		CASES[0][0]=new Case(Combinaison.BRELAN1);
		CASES[1][0]=new Case(Combinaison.BRELAN3);
		CASES[2][0]=new Case(Combinaison.DEFI);
		CASES[3][0]=new Case(Combinaison.BRELAN4);
		CASES[4][0]=new Case(Combinaison.BRELAN6);
		
		CASES[0][1]=new Case(Combinaison.BRELAN2);
		CASES[1][1]=new Case(Combinaison.CARRE);
		CASES[2][1]=new Case(Combinaison.SEC);
		CASES[3][1]=new Case(Combinaison.FULL);
		CASES[4][1]=new Case(Combinaison.BRELAN5);
		
		CASES[0][2]=new Case(Combinaison.INF8);
		CASES[1][2]=new Case(Combinaison.FULL);
		CASES[2][2]=new Case(Combinaison.YAM);
		CASES[3][2]=new Case(Combinaison.DEFI);
		CASES[4][2]=new Case(Combinaison.SUITE);
		
		CASES[0][3]=new Case(Combinaison.BRELAN6);
		CASES[1][3]=new Case(Combinaison.SEC);
		CASES[2][3]=new Case(Combinaison.SUITE);
		CASES[3][3]=new Case(Combinaison.INF8);
		CASES[4][3]=new Case(Combinaison.BRELAN1);
		
		CASES[0][4]=new Case(Combinaison.BRELAN3);
		CASES[1][4]=new Case(Combinaison.BRELAN2);
		CASES[2][4]=new Case(Combinaison.CARRE);
		CASES[3][4]=new Case(Combinaison.BRELAN5);
		CASES[4][4]=new Case(Combinaison.BRELAN4);
		
		
	}
	public void afficherPlateau()
	{
		for(int i=0; i<5;i++)
		{
			
			for(int k=0; k<5;k++)
			{
				
				System.out.print(this.CASES[k][i].getCombinaison()+ " | ");
			}
			System.out.println();
		}
	}
	
	public int checkAlignement(Couleur couleur)
	{
		return -1;
	}
	
	
}
