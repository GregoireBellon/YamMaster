package com.example.yatzy;

import com.example.yatzy.model.Jeton;
import com.example.yatzy.model.TypeCase;

public class Case {

    private int coordX, coordY; // Coordonnées par rapport à la grille fictive créée sur le plateau
    private TypeCase type; // Le type de la case (Case brelan, case Full, case Suite, etc...)
    private boolean caseRemplie; // Indique si un jeton est posé sur cette case
    private boolean peutPoser;  // Indique si la case correspond à la combinaison tirée par le joueur
    private Jeton jetonPose; // Contient le jeton posé sur cette case

    // Quand la case est créée, on considère qu'elle n'a pas de jeton posé et qu'on ne peut pas poser de jeton dessus
    public Case(TypeCase type) {
        this.type = type;
        this.caseRemplie = false;
        this.peutPoser = false;
        this.jetonPose = null;
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
}
