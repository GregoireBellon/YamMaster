package com.example.yatzy.model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

	private int score;
	private De[] des;
	private int[] actu;
	private int nbLancer;
	private ArrayList<Jeton> jetons;
	private List<Combinaison> combinaisonEnCours;


	private Combinaison combinaisonDefi;
	private Couleur couleurJeton;
	private boolean peutJouer;




	Scanner_perso sc = new Scanner_perso();
	

	public Joueur() {
		//cr��e les d�s du joueur et les stocke dans un tableau
		des = new De[6];
		for (int i = 0; i < 5; i++) {
			des[i] = new De();
		}
		
		//cr��e les jetons du joueur, leur donne une couleur et les stocke
		jetons = new ArrayList();
		for (int i = 0; i < 12; i++) { 
			jetons.add(new Jeton(this.getCouleurJeton()));
		}
		actu = new int[6];
		nbLancer = 0;
		
		this.setPeutJouer(true);
		this.setScore(0);
		

	}
	

	
	
	public void jouerUnTour() {

		System.out.println("DEBUT TOUR JOUEUR");
		//1er lancer
		lancerDes();
		afficherDesActuel();
		verifierCombinaison();
		afficherCombinaisonsActuelles();
		
		//d�fi +2e lancer
		choisirDefi();
		lancerDes();
		afficherDesActuel();
		verifierCombinaison();
		afficherCombinaisonsActuelles();
		
		//3e tour
		lancerDes();
		afficherDesActuel();
		verifierCombinaison();
		afficherCombinaisonsActuelles();
		verifJeton();
		
		System.out.println("FIN TOUR JOUEUR");
	}
	
	public void choisirDefi() {
		// Annoncer d�fi
	
		System.out.println("Voulez vous tenter un d�fi ? oui/non");
				// scanner oui ou non
				String reponse = sc.Reponse();
				if (reponse.equals("oui")) {
					// scanner si oui : quel d�fi
					System.out.println("S�lectionner le num�ro du d�fi que vous voulez r�aliser :");
					System.out.println("1 : carr�");
					System.out.println("2 : suite");
					System.out.println("3 : full");
					System.out.println("4 : <= 8");
					System.out.println("5 : Yam");

					// choix du type de d�fi
					switch (sc.Reponse()) {
					case "1":
						System.out.println("vous avez choisi le carr�");
						combinaisonDefi = Combinaison.CARRE;
						break;

					case "2":
						System.out.println("vous avez choisi la suite");
						combinaisonDefi = Combinaison.SUITE;
						break;

					case "3":
						System.out.println("vous avez choisi le full");
						combinaisonDefi = Combinaison.FULL;
						break;
					case "4":
						System.out.println("vous avez choisi inf�rieur ou �gal � 8");
						combinaisonDefi = Combinaison.INF8;
						break;
					case "5":
						System.out.println("vous avez choisi le Yam");
						combinaisonDefi = Combinaison.YAM;
						break;
					default:
						System.out.println("Vous n'avez pas s�lectionn� un d�fi valable, d�fi annul�");
						break;

					}
				}
				// choix de pas de d�fi ou r�ponse rat�e
				else if (reponse.equals("non")) {
				} else {
					System.out.println("vous avez �chou� � choisir un choix valable : pas de d�fi pour vous");
				}
	}

	public void lancerDes() {
		
		if(nbLancer==0)
		{
			nbLancer++;
			System.out.println("Lancer n�"+this.getNbLancer());
			for (int i = 0; i < 5; i++) {
				//actu[i] = 1; //des[i].rouler();
				System.out.print(actu[i] + " / ");
			}
			actu[0]=2;
			actu[1]=2;
			actu[2]=3;
			actu[3]=3;
			actu[4]=3;
			
			System.out.println();
			
		}
		else if(nbLancer<=2){
		System.out.println("Voulez vous gardez des d�s ? Indiquez la position du d� ou des d�s � relancer "
				+ "chiffre par chiffre � la suite, n'importe quoi si non");
		nbLancer++;
		
		 String rep = sc.Reponse();
		 System.out.println("Lancer n�"+this.getNbLancer());
		 boolean quit =false;
		 while(!quit) {
		for (int i = 0; i < rep.length(); i++) {
			switch (rep.charAt(i)) {
			case '1':
				System.out.println("vous avez relanc� " + actu[0]);
				this.actu[0] = des[0].rouler();
				System.out.println("nouveau d� : " +this.actu[0]);
				break;

			case '2':
				System.out.println("vous avez relanc� " + actu[1]);
				this.actu[1] = des[1].rouler();
				System.out.println("nouveau d� : " +this.actu[1]);
				break;

			case '3':
				System.out.println("vous avez relanc� " + actu[2]);
				this.actu[2] = des[2].rouler();
				System.out.println("nouveau d� : " +this.actu[2]);
				break;
			case '4':
				System.out.println("vous avez relanc� " + actu[3]);
				this.actu[3] = des[3].rouler();
				System.out.println("nouveau d� : " +this.actu[3]);
				break;
			case '5':
				System.out.println("vous avez relanc� " + actu[4]);
				this.actu[4] = des[4].rouler();
				System.out.println("nouveau d� : " +this.actu[4]);
				break;
			default:
				System.out.println("Vous n'avez pas s�lectionn� un chiffre valable, tous les d�s sont gard�s");
				quit=true;
				break;

			}
		}
		quit=true;

			
		 }
		
		}
		
	}

	/**
	 * v�rifie la combinaison
	 */
	public void verifierCombinaison() {
		boolean secos = false; // v�rifie que sec et d�fi �taient dans la liste de combinaisons avant
		// si oui, les rajoute dans la nouvelle liste
		boolean defi = false;
		if(nbLancer>1)
		{
			for(Combinaison c : combinaisonEnCours)
			{
				if(c.equals(Combinaison.SEC)) secos=true;
				if(c.equals(Combinaison.DEFI)) defi=true;
			}
		}
		
		combinaisonEnCours = new ArrayList();
		if(secos)
		{
			combinaisonEnCours.add(Combinaison.SEC);
		}
		if(defi)
		{
			combinaisonEnCours.add(Combinaison.DEFI);
		}
		int[] tableauCombi = new int[6];

		// test

		//afficherDesActuel();

		int somme = 0;
		for (int i = 0; i < 5; i++) {
			tableauCombi[i] = 0;
		}
		// recupere dans un tableau le nombre d'it�rations d'un chiffre sur les d�s
		for (int i = 0; i < 5; i++) {
			switch (actu[i]) {
			case 1:
				tableauCombi[0]++;
				break;
			case 2:
				tableauCombi[1]++;
				break;
			case 3:
				tableauCombi[2]++;
				break;
			case 4:
				tableauCombi[3]++;
				break;
			case 5:
				tableauCombi[4]++;
				break;
			case 6:
				tableauCombi[5]++;
				break;

			}
			somme += actu[i];

		}
		System.out.println("somme :" + somme);
		// v�rifie chaque combinaison

		int compteurSuite = 0;
		for (int i = 0; i <= 5; i++) {

			// yam
			if (tableauCombi[i] == 5)
				this.combinaisonEnCours.add(Combinaison.YAM);
			// carre
			if (tableauCombi[i] == 4)
				this.combinaisonEnCours.add(Combinaison.CARRE);
			// brelan
			if (tableauCombi[i] == 3) {
				int num = i + 1;
				Combinaison combi = Combinaison.valueOf("BRELAN" + num);

				this.combinaisonEnCours.add(combi);
				//attention
				for (int k = 0; k < 5; k++) {
					if (k!=i && tableauCombi[k] ==2) // si k est diff�rent de la face "brelan" et face k+1 pr�sentes 2 fois
						this.combinaisonEnCours.add(Combinaison.FULL);
				}

			}
			// suite
			// 0 � la fin ou au d�but 
			if (tableauCombi[i] == 1)
				compteurSuite++;
			else if (tableauCombi[i] != 1) {
				compteurSuite = 0;
			}

		}
		if (compteurSuite == 5)
			this.combinaisonEnCours.add(Combinaison.SUITE);
		// somme totale des faces inferieure ou �gale � 8
		if (somme <= 8)
			this.combinaisonEnCours.add(Combinaison.INF8);
		// sec : n'importe quelle combinaison mais des le premier tour
		if (nbLancer == 1 && this.combinaisonEnCours.size() != 0) {
			this.combinaisonEnCours.add(Combinaison.SEC);
		}

		// si une combinaison de la liste des combinaisons r�alis�es par le joueur est
		// �gale � celle qu'il avait annonc�e pour son d�fi, alors
		// il peut poser son jeton sur la case d�fi
		/*for (Combinaison b : combinaisonEnCours) {
			if (b.equals(combinaisonDefi)) {
				this.combinaisonEnCours.add(Combinaison.DEFI);
				this.setScore(this.getScore()+3);
			}
		}*/
		
	

	}
	// afficher les combinaisons que le joueur a valid� actuellement
	public void afficherCombinaisonsActuelles() {
		for (int i = 0; i < combinaisonEnCours.size(); i++) {
			System.out.print(this.combinaisonEnCours.get(i) + " / ");
		}
		System.out.println();
	}
	//afficher les faces des d�s du joueur
	public void afficherDesActuel() {
		System.out.println("D�s actuels : ");
		for (int i = 0; i < 5; i++) {
			System.out.println(this.actu[i]);
		}
	}
	
	public void verifJeton()
	{
		peutJouer=!jetons.isEmpty();
	}
	
	public List<Combinaison> getCombinaisonEnCours() {
		return combinaisonEnCours;
	}

	/**
	 * methode qui permet � un joueur de poser un de ses jetons sur une des cases du
	 * plateau o� il a le droit d'en poser
	 * 
	 * @param c
	 */
	public void poserJeton(Case c) {
			System.out.println("le joueur pose un jeton sur la case " + c.getCombinaison());
			c.setJeton(this.jetons.get(this.jetons.size()-1));
			this.jetons.remove(this.jetons.size()-1);
	}

	public void retirerJeton(Case c) {

	}
	
	public boolean peutJouer() {
		return peutJouer;
	}

	public void setPeutJouer(boolean peutJouer) {
		this.peutJouer = peutJouer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbLancer() {
		return nbLancer;
	}

	public void setNbLancer(int nbLancer) {
		this.nbLancer = nbLancer;
	}
	public Couleur getCouleurJeton() {
		return couleurJeton;
	}

	public void setCouleurJeton(Couleur couleurJeton) {
		this.couleurJeton = couleurJeton;
	}
}
