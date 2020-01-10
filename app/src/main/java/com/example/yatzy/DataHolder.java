package com.example.yatzy;

public class DataHolder {
    private De de1, de2, de3, de4, de5;
    private Partie partie;

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getHolder() {
        return holder;
    }

    public De getDe1() {
        return de1;
    }

    public void setDe1(De de1) {
        this.de1 = de1;
    }

    public De getDe2() {
        return de2;
    }

    public void setDe2(De de2) {
        this.de2 = de2;
    }

    public De getDe3() {
        return de3;
    }

    public void setDe3(De de3) {
        this.de3 = de3;
    }

    public De getDe4() {
        return de4;
    }

    public void setDe4(De de4) {
        this.de4 = de4;
    }

    public De getDe5() {
        return de5;
    }

    public void setDe5(De de5) {
        this.de5 = de5;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}
