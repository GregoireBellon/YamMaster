package com.example.yatzy;


import android.text.PrecomputedText;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;

public class Plateau {

    private int dimensionCases;
    private EcranJeu jeu;
    private Case[][] dispositionCases;
    DisplayMetrics tailleEcran;

    public Plateau(EcranJeu jeu) {
        tailleEcran = new DisplayMetrics();
        jeu.getWindowManager().getDefaultDisplay().getMetrics(tailleEcran);
        this.jeu = jeu;
        dimensionCases = tailleEcran.widthPixels / 5 ;
        dispositionCases = new Case[5][5];
        placerCases();
        ajouterVuesCases();
    }

    private void placerCases() {

        dispositionCases[0][0]=new Case(TypeCase.BRELAN1, this);
        dispositionCases[1][0]=new Case(TypeCase.BRELAN3, this);
        dispositionCases[2][0]=new Case(TypeCase.DEFI, this);
        dispositionCases[3][0]=new Case(TypeCase.BRELAN4, this);
        dispositionCases[4][0]=new Case(TypeCase.BRELAN6, this);

        dispositionCases[0][1]=new Case(TypeCase.BRELAN2, this);
        dispositionCases[1][1]=new Case(TypeCase.CARRE, this);
        dispositionCases[2][1]=new Case(TypeCase.SEC, this);
        dispositionCases[3][1]=new Case(TypeCase.FULL, this);
        dispositionCases[4][1]=new Case(TypeCase.BRELAN5, this);

        dispositionCases[0][2]=new Case(TypeCase.INF8, this);
        dispositionCases[1][2]=new Case(TypeCase.FULL, this);
        dispositionCases[2][2]=new Case(TypeCase.YAM, this);
        dispositionCases[3][2]=new Case(TypeCase.DEFI, this);
        dispositionCases[4][2]=new Case(TypeCase.SUITE, this);

        dispositionCases[0][3]=new Case(TypeCase.BRELAN6, this);
        dispositionCases[1][3]=new Case(TypeCase.SEC, this);
        dispositionCases[2][3]=new Case(TypeCase.SUITE, this);
        dispositionCases[3][3]=new Case(TypeCase.INF8, this);
        dispositionCases[4][3]=new Case(TypeCase.BRELAN1, this);

        dispositionCases[0][4]=new Case(TypeCase.BRELAN3, this);
        dispositionCases[1][4]=new Case(TypeCase.BRELAN2, this);
        dispositionCases[2][4]=new Case(TypeCase.CARRE, this);
        dispositionCases[3][4]=new Case(TypeCase.BRELAN5, this);
        dispositionCases[4][4]=new Case(TypeCase.BRELAN4, this);
    }

    public void ajouterVuesCases() {
        int hauteurPlateau = tailleEcran.heightPixels / 4;
        for (int i = 0; i <= 4; i++){
            for (int j = 0 ; j <= 4 ; j++){
                Case nouvelleCase = dispositionCases[i][j];
                nouvelleCase.setCoordX(i * getDimensionCases());
                nouvelleCase.setCoordY(j * getDimensionCases() + hauteurPlateau);
                ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(getDimensionCases(),getDimensionCases());
                jeu.addContentView(nouvelleCase.getImageCase(),param);
                jeu.ajusterCases(dispositionCases[i][j]);
            }

        }
    }

    public EcranJeu getJeu() {
        return jeu;
    }

    public int getDimensionCases() {
        return dimensionCases;
    }
}
