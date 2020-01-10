package com.example.yatzy;

import android.widget.ImageButton;

public class De extends com.example.yatzy.model.De{

    private ImageButton boutonDe;
    private String nom_img;
    private Plateau plateau;
    private Boolean selected = false;
    private int face = 1;

    public De(Plateau plateau) {
        super();
        boutonDe = new ImageButton(plateau.getJeu());
        boutonDe.setImageResource(R.drawable.des_un);
    }

    @Override
    public int rouler()
    {
        int random = (int) (Math.random() * 5);
        switch (random){
            case 0: boutonDe.setImageResource(R.drawable.des_un);
                    nom_img="des_un";
                    break;
            case 1: boutonDe.setImageResource(R.drawable.des_deux);
                    nom_img="des_deux";
                    break;
            case 2: boutonDe.setImageResource(R.drawable.des_trois);
                    nom_img="des_trois";
                    break;
            case 3: boutonDe.setImageResource(R.drawable.des_quatre);
                    nom_img="des_quatre";
                    break;
            case 4: boutonDe.setImageResource(R.drawable.des_cinq);
                nom_img="des_cinq";
                break;
            case 5: boutonDe.setImageResource(R.drawable.des_six);
                nom_img="des_six";
                break;
        }
        face = random;
        return random;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public ImageButton getBoutonDe() {
        return boutonDe;
    }

    public void setBoutonDe(ImageButton boutonDe) {
        this.boutonDe = boutonDe;
    }

    public String getNom_img() {
        return nom_img;
    }
}
