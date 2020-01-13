package com.example.yatzy;

import android.content.Intent;
import android.util.Log;

import com.example.yatzy.model.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Partie extends com.example.yatzy.model.Partie{

    private Joueur joueurActuel;
    private Joueur joueur1;
    private Joueur joueur2;
    private int numJoueurActuel;
    private List<Jeton> jetonsPoses;
    private Plateau plateau;
    private EcranJeu jeu;
    private List<De> listeDes;
    private List<Combinaison> combinaisonEnCours = new ArrayList<>();
    private boolean choixDefi;
    private boolean defiReussi;

    private De de1, de2 , de3, de4, de5;

    public Partie(EcranJeu jeu) {
        this.jeu = jeu;
        plateau = jeu.getPlateau();
        joueur1 = new com.example.yatzy.Joueur(Couleur.BLANC, this);
        joueur2 = new com.example.yatzy.Joueur(Couleur.NOIR, this);
        joueurActuel = joueur1;
        numJoueurActuel = 1;
        jetonsPoses = new ArrayList<>();
        listeDes = new ArrayList<>();
        choixDefi = false;
        defiReussi = false;

        de1 = DataHolder.getHolder().getDe1();
        de2 = DataHolder.getHolder().getDe2();
        de3 = DataHolder.getHolder().getDe3();
        de4 = DataHolder.getHolder().getDe4();
        de5 = DataHolder.getHolder().getDe5();

        listeDes.add(de1);
        listeDes.add(de2);
        listeDes.add(de3);
        listeDes.add(de4);
        listeDes.add(de5);

    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void passerTour(){
        if(numJoueurActuel == 1) {
            jeu.getTexteNomJoueur().setText(R.string.nomJoueur2);
            joueur1 = joueurActuel;
            joueurActuel = joueur2;
            numJoueurActuel = 2;
            joueurActuel.setNbrLancers(0);
            joueurActuel.setCombinaisonDefi(null);
            plateau.resetPlateau();
            for (De de : listeDes){
                de.setSelected(false);
            }
            setChoixDefi(false);
            setDefiReussi(false);
            return;
        }
        else if(numJoueurActuel == 2) {
            joueur2 = joueurActuel;
            joueurActuel = joueur1;
            numJoueurActuel = 1;
            joueurActuel.setNbrLancers(0);
            joueurActuel.setCombinaisonDefi(null);
            jeu.getTexteNomJoueur().setText(R.string.nomJoueur1);
            plateau.resetPlateau();
            for (De de : listeDes){
                de.setSelected(false);
            }
            setChoixDefi(false);
            setDefiReussi(false);
            return;
        }

    }

    public void lancersDes(){

        listeDes = DataHolder.getHolder().getListeDes();
        if (joueurActuel.getNbrLancers() < 3){
            int nbrSelectionnes = 0;
            for (De de: listeDes) {
                if (de.isSelected()){
                    nbrSelectionnes++;
                }
                de.rouler();
            }

            DataHolder.getHolder().setDe1(de1);
            DataHolder.getHolder().setDe2(de2);
            DataHolder.getHolder().setDe3(de3);
            DataHolder.getHolder().setDe4(de4);
            DataHolder.getHolder().setDe5(de5);
            DataHolder.getHolder().setPartie(Partie.this);
            if (nbrSelectionnes < 5) {
                joueurActuel.setNbrLancers(joueurActuel.getNbrLancers() + 1);
            }
        }
    }

    public void determinerCombinaisons() {

        joueurActuel.setCombinaisonDefi(DataHolder.getHolder().getPartie().getJoueurActuel().getCombinaisonDefi());
        boolean secos = false; // v�rifie que sec et d�fi �taient dans la liste de combinaisons avant
        // si oui, les rajoute dans la nouvelle liste
        boolean defi = false;
        combinaisonEnCours = new ArrayList();
        if (joueurActuel.getNbrLancers() > 1) {
            for (Combinaison c : combinaisonEnCours) {
                if (c.equals(Combinaison.SEC)) secos = true;
                if (c.equals(Combinaison.DEFI)) defi = true;
            }
        }

        if (secos) {
            combinaisonEnCours.add(Combinaison.SEC);
        }
        if (defi) {
            combinaisonEnCours.add(Combinaison.DEFI);
        }
        int[] tableauCombi = new int[6];

        int somme = 0;
        for (int i = 0; i < 5; i++) {
            tableauCombi[i] = 0;
        }
        // recupere dans un tableau le nombre d'it�rations d'un chiffre sur les d�s
        for (De de: listeDes) {
            switch (de.getFace()) {
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
            somme += de.getFace();

        }
        // v�rifie chaque combinaison

        int compteurSuite = 0;
        for (int i = 0; i <= 5; i++) {

            // yam
            if (tableauCombi[i] == 5)
                this.combinaisonEnCours.add(Combinaison.YAM);
            // carre
            if (tableauCombi[i] >= 4)
                this.combinaisonEnCours.add(Combinaison.CARRE);
            // brelan
            if (tableauCombi[i] >= 3) {
                int num = i + 1;
                Combinaison combi = Combinaison.valueOf("BRELAN" + num);

                this.combinaisonEnCours.add(combi);
                //attention
                for (int k = 0; k < 5; k++) {
                    if (k != i && tableauCombi[k] == 2) // si k est diff�rent de la face "brelan" et face k+1 pr�sentes 2 fois
                        this.combinaisonEnCours.add(Combinaison.FULL);
                }
            }

            // suite
            // 0 � la fin ou au d�but
            if (tableauCombi[i] == 1)
                compteurSuite++;
            else if (tableauCombi[i] != 1 && compteurSuite < 5) {
                compteurSuite = 0;
            }

        }
        if (compteurSuite == 5)
            this.combinaisonEnCours.add(Combinaison.SUITE);
        // somme totale des faces inferieure ou �gale � 8
        if (somme <= 8)
            this.combinaisonEnCours.add(Combinaison.INF8);
        // sec : n'importe quelle combinaison mais des le premier tour
        if (joueurActuel.getNbrLancers() == 1 && this.combinaisonEnCours.size() != 0) {
            this.combinaisonEnCours.add(Combinaison.SEC);
        }

        // si une combinaison de la liste des combinaisons r�alis�es par le joueur est
        // �gale � celle qu'il avait annonc�e pour son d�fi, alors
        // il peut poser son jeton sur la case d�fi
        for (Combinaison b : combinaisonEnCours) {
            if (b.equals(joueurActuel.getCombinaisonDefi())) {
                this.setDefiReussi(true);
                this.combinaisonEnCours.add(Combinaison.DEFI);
                joueurActuel.setScore(joueurActuel.getScore() + 3);
            }else if (joueurActuel.getCombinaisonDefi() == Combinaison.AUCUNE){
                if (b.equals(Combinaison.BRELAN1)||b.equals(Combinaison.BRELAN2)||b.equals(Combinaison.BRELAN3)||
                        b.equals(Combinaison.BRELAN4)||b.equals(Combinaison.BRELAN5)||b.equals(Combinaison.BRELAN6)){
                    this.setDefiReussi(true);
                    this.combinaisonEnCours.add(Combinaison.DEFI);
                    joueurActuel.setScore(joueurActuel.getScore() + 3);
                }
            }
            Log.e("Défi Réussi", "Le défi a été ajouté à la liste des combinaisons réussies");
        }
    }

    public void calculerScoresJoueurs(){

    }

    public List<De> getListeDes() {
        return listeDes;
    }

    public List<Combinaison> getCombinaisonEnCours() {
        return combinaisonEnCours;
    }

    public boolean isChoixDefi() {
        return choixDefi;
    }

    public void setChoixDefi(boolean choixDefi) {
        this.choixDefi = choixDefi;
    }

    public boolean isDefiReussi() {
        return defiReussi;
    }

    public void setDefiReussi(boolean defiReussi) {
        this.defiReussi = defiReussi;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }
}
