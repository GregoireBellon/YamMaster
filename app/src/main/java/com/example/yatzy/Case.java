package com.example.yatzy;

import com.example.yatzy.model.Jeton;
import com.example.yatzy.model.TypeCase;

public class Case {

    private int coordX, coordY;
    private TypeCase type;
    private boolean caseRemplie;
    private boolean peutPoser;
    private Jeton jetonPose;

    public Case(TypeCase type) {
        this.type = type;
        this.caseRemplie = false;
        this.peutPoser = false;
        this.jetonPose = null;
    }

    public void poserJeton(Jeton jeton){
        setJetonPose(jeton);
        setCaseRemplie(true);
        setPeutPoser(false);
    }

    public void retirerJeton(){
        setJetonPose(null);
        setCaseRemplie(true);
        setPeutPoser(true);
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
