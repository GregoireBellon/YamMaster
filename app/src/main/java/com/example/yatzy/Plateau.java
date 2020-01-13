package com.example.yatzy;


import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;

import com.example.yatzy.model.Couleur;
import java.util.ArrayList;
import java.util.List;

public class Plateau {

    private int dimensionCases;
    private int dimensionJetons;
    private EcranJeu jeu;
    public Case[][] dispositionCases;
    private List<Jeton> jetonsPoses;
    private DisplayMetrics tailleEcran;

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
                nouvelleCase.setIndexX(i);
                nouvelleCase.setIndexY(j);
                nouvelleCase.setCoordX(i * getDimensionCases());
                nouvelleCase.setCoordY(j * getDimensionCases() + hauteurPlateau);
                ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(getDimensionCases(),getDimensionCases());
                jeu.getLayoutPlateau().addView(nouvelleCase.getImageCase(),param);
                jeu.ajusterCases(dispositionCases[i][j]);
                dispositionCases[i][j] = nouvelleCase;
            }
        }
    }

    public int compterAlignements(Couleur couleur, int x, int y, int dx, int dy, int tailleAlignement) {
        int nbJetonsAlignes = 0;
        int nbAlignements = 0;
        x += dx;  // Skip the piece at (y, x) to avoid counting it twice
        y += dy;  // when looking in both directions on a line.
        while (x >= 0 && x < 5 && y >= 0 && y < 5 && dispositionCases[x][y].getJetonPose() != null && dispositionCases[x][y].getJetonPose().getCouleur() == couleur) {
            nbJetonsAlignes++;
            if (nbJetonsAlignes == tailleAlignement) {
                nbAlignements++;
            }
            x += dx;  // Move in the direction denoted by (dy, dx)
            y += dy;
        }
        return nbAlignements;
    }

    public void checkTroisJetonsAlignesHorizontal(Couleur couleur, int x, int y) {
        int nbAlignements = compterAlignements(couleur, x, y, -1, 0, 3) + compterAlignements(couleur, x, y, 1, 0, 3);  // horizontal
        if(couleur == Couleur.BLANC) {
            joueur1.setScore(joueur1.getScore() + nbAlignements);
        }
        if(couleur == Couleur.NOIR) {
            joueur2.setScore(joueur2.getScore() + nbAlignements);
        }
    }

    public void checkTroisJetonsAlignesVertical(Couleur couleur, int x, int y) {
        int nbAlignements =  compterAlignements(couleur, x, y, 0, -1, 3) + compterAlignements(couleur, x, y, 0, 1, 3);  // vertical
        if(couleur == Couleur.BLANC) {
            joueur1.setScore(joueur1.getScore() + nbAlignements);
        }
        if(couleur == Couleur.NOIR) {
            joueur2.setScore(joueur2.getScore() + nbAlignements);
        }
    }

    public void checkTroisJetonsAlignesDiagonal(Couleur couleur, int x, int y) {
        int nbAlignements =  compterAlignements(couleur, x, y, -1, -1, 3) + 1 + compterAlignements(couleur, x, y, 1, 1, 3) // diagonal
        + compterAlignements(couleur, x, y, -1, 1, 3) + compterAlignements(couleur, x, y, 1, -1, 3);
        if(couleur == Couleur.BLANC) {
            joueur1.setScore(joueur1.getScore() + nbAlignements);
        }
        if(couleur == Couleur.NOIR) {
            joueur2.setScore(joueur2.getScore() + nbAlignements);
        }
    }

    public void checkQuatreJetonsAlignesHorizontal(Couleur couleur, int x, int y) {
        int nbAlignements =  compterAlignements(couleur, x, y, -1, 0, 4) + compterAlignements(couleur, x, y, 1, 0, 4);  // horizontal
        if(couleur == Couleur.BLANC) {
            joueur1.setScore(joueur1.getScore() + nbAlignements * 2);
        }
        if(couleur == Couleur.NOIR) {
            joueur2.setScore(joueur2.getScore() + nbAlignements * 2);
        }
    }

    public void checkQuatreJetonsAlignesVertical(Couleur couleur, int x, int y) {
        int nbAlignements = compterAlignements(couleur, x, y, 0, -1, 4) + compterAlignements(couleur, x, y, 0, 1, 4);  // vertical
        if(couleur == Couleur.BLANC) {
            joueur1.setScore(joueur1.getScore() + nbAlignements * 2);
        }
        if(couleur == Couleur.NOIR) {
            joueur2.setScore(joueur2.getScore() + nbAlignements * 2);
        }
    }

    public void checkQuatreJetonsAlignesDiagonal(Couleur couleur, int x, int y) {
        int nbAlignements = compterAlignements(couleur, x, y, -1, -1, 4) + 1 + compterAlignements(couleur, x, y, 1, 1, 4)  // diagonals
        + compterAlignements(couleur, x, y, -1, 1, 4) + compterAlignements(couleur, x, y, 1, -1, 4);
        if(couleur == Couleur.BLANC) {
            joueur1.setScore(joueur1.getScore() + nbAlignements * 2);
            Log.e("score joueur 1", "score J1 = " + joueur1.getScore());
        }
        if(couleur == Couleur.NOIR) {
            joueur2.setScore(joueur2.getScore() + nbAlignements * 2);
        }
    }

    public void ajouterUneVueCase(Case caseAPlacer) {
        jeu.getLayoutPlateau().removeView(caseAPlacer.getImageCase());
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(getDimensionCases(),getDimensionCases());
        jeu.getLayoutPlateau().addView(caseAPlacer.getImageCase(),param);
        jeu.ajusterCases(caseAPlacer);
    }

    public void resetPlateau() {
        jeu.getLayoutPlateau().removeAllViews();
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                dispositionCases[i][j].setPeutPoser(true);
                ajouterUneVueCase(dispositionCases[i][j]);
            }
        }
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

    public Case[][] getDispositionCases() {
        return dispositionCases;
    }

    public void setDispositionCases(Case[][] dispositionCases) {
        this.dispositionCases = dispositionCases;
    }
}
