package com.example.yatzy;

public class Plateau {

    private int dimensionCases;
    private EcranJeu jeu;
    private int[] dispositionCases;

    public Plateau(EcranJeu jeu) {
        this.jeu = jeu;
        dimensionCases = jeu.getLayout().getWidth();
        dimensionCases = dimensionCases / 5;

    }
}
