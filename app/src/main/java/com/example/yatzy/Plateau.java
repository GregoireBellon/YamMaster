package com.example.yatzy;


import android.text.PrecomputedText;
import android.view.ViewGroup;

public class Plateau {

    private int dimensionCases;
    private EcranJeu jeu;
    private Case[][] dispositionCases;

    public Plateau(EcranJeu jeu) {
        this.jeu = jeu;
        dimensionCases =150 ;
        dispositionCases = new Case[5][5];
        placerCases();
    }

    private void placerCases() {

        dispositionCases[0][0]=new Case(TypeCase.BRELAN1, this);
        /*dispositionCases[1][0]=new Case(TypeCase.BRELAN3, this);
        dispositionCases[2][0]=new Case(TypeCase.DEFI, this);
        dispositionCases[3][0]=new Case(TypeCase.BRELAN4, this);
        dispositionCases[4][0]=new Case(TypeCase.BRELAN6, this);

        dispositionCases[0][1]=new Case(TypeCase.BRELAN2, this);
        dispositionCases[1][1]=new Case(TypeCase.CARRE, this);
        dispositionCases[2][1]=new Case(TypeCase.ONESHOT, this);
        dispositionCases[3][1]=new Case(TypeCase.FULL, this);
        dispositionCases[4][1]=new Case(TypeCase.BRELAN5, this);

        dispositionCases[0][2]=new Case(TypeCase.INF8, this);
        dispositionCases[1][2]=new Case(TypeCase.FULL, this);
        dispositionCases[2][2]=new Case(TypeCase.YAM, this);
        dispositionCases[3][2]=new Case(TypeCase.DEFI, this);
        dispositionCases[4][2]=new Case(TypeCase.SUITE, this);

        dispositionCases[0][3]=new Case(TypeCase.BRELAN6, this);
        dispositionCases[1][3]=new Case(TypeCase.ONESHOT, this);
        dispositionCases[2][3]=new Case(TypeCase.SUITE, this);
        dispositionCases[3][3]=new Case(TypeCase.INF8, this);
        dispositionCases[4][3]=new Case(TypeCase.BRELAN1, this);

        dispositionCases[0][4]=new Case(TypeCase.BRELAN3, this);
        dispositionCases[1][4]=new Case(TypeCase.BRELAN2, this);
        dispositionCases[2][4]=new Case(TypeCase.CARRE, this);
        dispositionCases[3][4]=new Case(TypeCase.BRELAN5, this);
        dispositionCases[4][4]=new Case(TypeCase.BRELAN4, this);*/
    }

    public void ajouterVuesCases() {
        int hauteurPlateau = jeu.getLayout().getHeight() / 4;
        for (int i = 0; i <= 0; i++){
            for (int j = 0 ; j <= 0 ; j++){
                dispositionCases[i][j].setCoordX(i * dimensionCases);
                dispositionCases[i][j].setCoordY(j * dimensionCases + hauteurPlateau);
                ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(getDimensionCases(),getDimensionCases());
                dispositionCases[i][j].getImageCase().setImageResource(R.drawable.des_cinq);
                jeu.addContentView(dispositionCases[i][j].getImageCase(),param);
                dispositionCases[i][j].getImageCase().setX(250);
                dispositionCases[i][j].getImageCase().setY(250);
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
