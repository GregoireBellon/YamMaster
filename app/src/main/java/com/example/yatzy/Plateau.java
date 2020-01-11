package com.example.yatzy;


import android.text.PrecomputedText;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yatzy.model.Couleur;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Plateau {

    private int dimensionCases;
    private int dimensionJetons;
    private EcranJeu jeu;
    private Case[][] dispositionCases;
    private List<Jeton> jetonsPoses;
    private Joueur joueur1;
    private Joueur joueur2;
    DisplayMetrics tailleEcran;

    public Plateau(EcranJeu jeu) {
        tailleEcran = new DisplayMetrics();
        jeu.getWindowManager().getDefaultDisplay().getMetrics(tailleEcran);
        this.jeu = jeu;
        dimensionCases = tailleEcran.widthPixels / 5;
        dimensionJetons = tailleEcran.widthPixels / 6;
        dispositionCases = new Case[5][5];
        jetonsPoses = new ArrayList<>();
        placerCases();
        ajouterVuesCases();
    }

    private void placerCases() {

        dispositionCases[0][0] = new Case(TypeCase.BRELAN1, this);
        dispositionCases[1][0] = new Case(TypeCase.BRELAN3, this);
        dispositionCases[2][0] = new Case(TypeCase.DEFI, this);
        dispositionCases[3][0] = new Case(TypeCase.BRELAN4, this);
        dispositionCases[4][0] = new Case(TypeCase.BRELAN6, this);

        dispositionCases[0][1] = new Case(TypeCase.BRELAN2, this);
        dispositionCases[1][1] = new Case(TypeCase.CARRE, this);
        dispositionCases[2][1] = new Case(TypeCase.SEC, this);
        dispositionCases[3][1] = new Case(TypeCase.FULL, this);
        dispositionCases[4][1] = new Case(TypeCase.BRELAN5, this);

        dispositionCases[0][2] = new Case(TypeCase.INF8, this);
        dispositionCases[1][2] = new Case(TypeCase.FULL, this);
        dispositionCases[2][2] = new Case(TypeCase.YAM, this);
        dispositionCases[3][2] = new Case(TypeCase.DEFI, this);
        dispositionCases[4][2] = new Case(TypeCase.SUITE, this);

        dispositionCases[0][3] = new Case(TypeCase.BRELAN6, this);
        dispositionCases[1][3] = new Case(TypeCase.SEC, this);
        dispositionCases[2][3] = new Case(TypeCase.SUITE, this);
        dispositionCases[3][3] = new Case(TypeCase.INF8, this);
        dispositionCases[4][3] = new Case(TypeCase.BRELAN1, this);

        dispositionCases[0][4] = new Case(TypeCase.BRELAN3, this);
        dispositionCases[1][4] = new Case(TypeCase.BRELAN2, this);
        dispositionCases[2][4] = new Case(TypeCase.CARRE, this);
        dispositionCases[3][4] = new Case(TypeCase.BRELAN5, this);
        dispositionCases[4][4] = new Case(TypeCase.BRELAN4, this);
    }

    public void ajouterVuesCases() {
        int hauteurPlateau = tailleEcran.heightPixels / 4;
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                Case nouvelleCase = dispositionCases[i][j];
                nouvelleCase.setCoordX(i * getDimensionCases());
                nouvelleCase.setCoordY(j * getDimensionCases() + hauteurPlateau);
                ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(getDimensionCases(), getDimensionCases());
                jeu.addContentView(nouvelleCase.getImageCase(), param);
                jeu.ajusterCases(dispositionCases[i][j]);
            }

        }
    }

    public int compterPoints(Couleur couleur) {
        int cpt1 = 0; // init du compteur
        int score = 0; // score nul

        // colonne
        // parcourt chaque colonne
        for (int i = 0; i < 3; i++) {
            // parcourt chaque ligne
            for (int h = 0; h < 3; h++) {
                // verifie presence d'un jeton ; si oui, compare couleur du jeton et couleur du
                // joueur
                if (dispositionCases[i][h].getJetonPose().getCouleur() == couleur) {
                    cpt1++;
                }
            }
            if (cpt1 == 3)
                score += 1;
            if (cpt1 == 4)
                score += 2;
        }

        // ligne
        cpt1 = 0;
        // parcourt chaque colonne
        for (int i = 0; i < 3; i++) {
            // parcourt chaque ligne
            for (int h = 0; h < 3; h++) {
                if (dispositionCases[h][i].getJetonPose().getCouleur() == couleur) {
                    cpt1++;
                }
            }
            if (cpt1 == 3)
                score += 1;
            if (cpt1 == 4)
                score += 2;
        }

        // ===================================
        // diago
        // on verifie si 4 jetons sont alignes
        // les 2 diagonales de 5 cases
        if ((dispositionCases[0][0].getJetonPose().getCouleur() == couleur && dispositionCases[1][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][2].getJetonPose().getCouleur() == couleur && dispositionCases[3][3].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[1][1].getJetonPose().getCouleur() == couleur && dispositionCases[2][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[3][3].getJetonPose().getCouleur() == couleur && dispositionCases[4][4].getJetonPose().getCouleur() == couleur)) {
            score += 4;
        }

        if ((dispositionCases[4][0].getJetonPose().getCouleur() == couleur && dispositionCases[3][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][2].getJetonPose().getCouleur() == couleur && dispositionCases[1][3].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[3][1].getJetonPose().getCouleur() == couleur && dispositionCases[2][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[1][3].getJetonPose().getCouleur() == couleur && dispositionCases[0][4].getJetonPose().getCouleur() == couleur)) {
            score += 4;
        }

        // les 4 diagonales de 4 cases
        if (dispositionCases[3][0].getJetonPose().getCouleur() == couleur && dispositionCases[2][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[1][2].getJetonPose().getCouleur() == couleur && dispositionCases[0][3].getJetonPose().getCouleur() == couleur) {
            score += 4;
        }

        if (dispositionCases[1][0].getJetonPose().getCouleur() == couleur && dispositionCases[2][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[3][2].getJetonPose().getCouleur() == couleur && dispositionCases[4][3].getJetonPose().getCouleur() == couleur) {
            score += 4;
        }

        if (dispositionCases[0][1].getJetonPose().getCouleur() == couleur && dispositionCases[1][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][3].getJetonPose().getCouleur() == couleur && dispositionCases[3][4].getJetonPose().getCouleur() == couleur) {
            score += 4;
        }

        if (dispositionCases[4][1].getJetonPose().getCouleur() == couleur && dispositionCases[3][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][3].getJetonPose().getCouleur() == couleur && dispositionCases[1][4].getJetonPose().getCouleur() == couleur) {
            score += 4;
        }

        // ===================================
        // on verifie si 3 jetons sont alignes
        // les 2 diagonales de 5 cases
        if ((dispositionCases[0][0].getJetonPose().getCouleur() == couleur && dispositionCases[1][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][2].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[1][1].getJetonPose().getCouleur() == couleur && dispositionCases[2][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[3][3].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[2][2].getJetonPose().getCouleur() == couleur && dispositionCases[3][3].getJetonPose().getCouleur() == couleur
                && dispositionCases[4][4].getJetonPose().getCouleur() == couleur)) {
            score += 1;
        }

        if ((dispositionCases[4][0].getJetonPose().getCouleur() == couleur && dispositionCases[3][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][2].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[3][1].getJetonPose().getCouleur() == couleur && dispositionCases[2][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[1][3].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[2][2].getJetonPose().getCouleur() == couleur && dispositionCases[1][3].getJetonPose().getCouleur() == couleur
                && dispositionCases[0][4].getJetonPose().getCouleur() == couleur)) {
            score += 1;
        }

        // les 4 diagonales de 3 cases
        if ((dispositionCases[2][0].getJetonPose().getCouleur() == couleur && dispositionCases[1][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[0][2].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[2][0].getJetonPose().getCouleur() == couleur && dispositionCases[3][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[4][2].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[0][2].getJetonPose().getCouleur() == couleur && dispositionCases[1][3].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][4].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[4][2].getJetonPose().getCouleur() == couleur && dispositionCases[3][3].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][4].getJetonPose().getCouleur() == couleur)) {
            score += 1;
        }

        // les 4 diagonales de 4 cases
        if ((dispositionCases[3][0].getJetonPose().getCouleur() == couleur && dispositionCases[2][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[1][2].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[2][1].getJetonPose().getCouleur() == couleur && dispositionCases[1][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[0][3].getJetonPose().getCouleur() == couleur)) {
            score += 1;
        }

        if ((dispositionCases[4][1].getJetonPose().getCouleur() == couleur && dispositionCases[3][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][3].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[3][2].getJetonPose().getCouleur() == couleur && dispositionCases[2][3].getJetonPose().getCouleur() == couleur
                && dispositionCases[1][4].getJetonPose().getCouleur() == couleur)) {
            score += 1;
        }

        if ((dispositionCases[1][0].getJetonPose().getCouleur() == couleur && dispositionCases[2][1].getJetonPose().getCouleur() == couleur
                && dispositionCases[3][2].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[2][1].getJetonPose().getCouleur() == couleur && dispositionCases[3][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[4][3].getJetonPose().getCouleur() == couleur)) {
            score += 1;
        }

        if ((dispositionCases[0][1].getJetonPose().getCouleur() == couleur && dispositionCases[1][2].getJetonPose().getCouleur() == couleur
                && dispositionCases[2][3].getJetonPose().getCouleur() == couleur)
                || (dispositionCases[1][2].getJetonPose().getCouleur() == couleur && dispositionCases[2][3].getJetonPose().getCouleur() == couleur
                && dispositionCases[3][4].getJetonPose().getCouleur() == couleur)) {
            score += 1;
        }

        return -1;
    }


    public EcranJeu getJeu() {
        return jeu;
    }

    public int getDimensionCases() {
        return dimensionCases;
    }

    public int getDimensionJetons() {
        return dimensionJetons;
    }

    public List<Jeton> getJetonsPoses() {
        return jetonsPoses;
    }
}
