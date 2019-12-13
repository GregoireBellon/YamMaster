
package com.example.yatzy.model;

import java.util.Random;

public class De {

	private int[] faces;
	private boolean estSelectionne;
	
	

	public De()
	{
		faces= new int[6];
		faces[0]=1;
		faces[1]=2;
		faces[2]=3;
		faces[3]=4;
		faces[4]=5;
		faces[5]=6;
		
	}
	
	/**
	 * renvoie une face al�atoire du d� (entre 0 et 5 pour acc�der au tableau)
	 * @return
	 */
	public int rouler()
	{
		int random = (int) (Math.random() * 6);
		//System.out.println(random);
		return faces[random];
	}
	
	
	public boolean isEstSelectionne() {
		return estSelectionne;
	}

	public void setEstSelectionne(boolean estSelectionne) {
		this.estSelectionne = estSelectionne;
	}
	
	
}
