
public class test {

	public static void main(String[] args) {
		De de = new De();
		de.rouler();

		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		
		Partie p = new Partie(j1,j2);
		p.getPlateau().afficherPlateau();
		
		p.commencerPartie();
	}

}
