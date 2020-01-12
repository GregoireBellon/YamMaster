package com.example.yatzy;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class Case {

    private int coordX, coordY; // Coordonnées par rapport à la grille fictive créée sur le plateau
    private TypeCase type; // Le type de la case (Case brelan, case Full, case Suite, etc...)
    private boolean caseRemplie; // Indique si un jeton est posé sur cette case
    private boolean peutPoser;  // Indique si la case correspond à la combinaison tirée par le joueur
    private Jeton jetonPose; // Contient le jeton posé sur cette case
    private Plateau plateau; // Plateau auquel appartient la case
    private ImageButton imageCase; // Image de la case (pas d'image pour l'instant mais pourra être utilisé pour
                                   // afficher les cases en surbrillance ou pour afficher la case si on enlève l'image du plateau)

    // Quand la case est créée, on considère qu'elle n'a pas de jeton posé et qu'on ne peut pas poser de jeton dessus
    public Case(TypeCase type, Plateau plateau) {
        this.type = type;
        this.caseRemplie = false;
        this.peutPoser = true;
        this.jetonPose = null;
        this.plateau = plateau;
        imageCase = new ImageButton(plateau.getJeu());
        determinerImage();
    }

    // Fonction à utiliser pour poser un jeton sur cette case
    public void poserJeton(Jeton jeton){
        setJetonPose(jeton);
        setCaseRemplie(true);
    }

    // Fonction à utiliser pour retirer le jeton posé sur cette case (quand un des joueurs fait un Yam)
    public void retirerJeton(){
        if (caseRemplie){
            setJetonPose(null);
            setCaseRemplie(true);
        }
    }

    public void determinerImage(){
        Resources res = plateau.getJeu().getResources();
        String nomFichier = "case_"+ type.toString().toLowerCase();
        int resID = res.getIdentifier(nomFichier , "drawable", plateau.getJeu().getPackageName());
        imageCase.setImageResource(resID);
        imageCase.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    // ---------------------------------------- Getters et Setters

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public TypeCase getType() {
        return type;
    }

    public boolean isCaseRemplie() {
        return caseRemplie;
    }

    public boolean isPeutPoser() {
        return peutPoser;
    }

    public Jeton getJetonPose() {
        return jetonPose;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void setCaseRemplie(boolean caseRemplie) {
        this.caseRemplie = caseRemplie;
    }

    public void setPeutPoser(boolean peutPoser) {
        this.peutPoser = peutPoser;
    }

    public void setJetonPose(Jeton jetonPose) {
        this.jetonPose = jetonPose;
    }


    public ImageButton getImageCase() {
        return imageCase;
    }

    public void setImageCase(ImageButton imageCase) {
        this.imageCase = imageCase;
    }

}
