package com.example.yatzy;

import android.util.Log;
import android.widget.ImageButton;

public class De extends com.example.yatzy.model.De{

    private ImageButton boutonDe;
    private String nom_img;
    private Boolean selected = false;
    private int face = 1;

    public De(EcranJeu jeu) {
        super();
        boutonDe = new ImageButton(jeu);
        boutonDe.setImageResource(R.drawable.des_un);
        nom_img = "des_un";
    }

    @Override
    public int rouler()
    {
        int random;
        if (!isSelected()) {
            random = (int) (Math.random() * 6);
            switch (random) {
                case 0:
                    boutonDe.setImageResource(R.drawable.des_un);
                    nom_img = "des_un";
                    face = 1;
                    break;
                case 1:
                    boutonDe.setImageResource(R.drawable.des_deux);
                    nom_img = "des_deux";
                    face = 2;
                    break;
                case 2:
                    boutonDe.setImageResource(R.drawable.des_trois);
                    nom_img = "des_trois";
                    face = 3;
                    break;
                case 3:
                    boutonDe.setImageResource(R.drawable.des_quatre);
                    nom_img = "des_quatre";
                    face = 4;
                    break;
                case 4:
                    boutonDe.setImageResource(R.drawable.des_cinq);
                    nom_img = "des_cinq";
                    face = 5;
                    break;
                case 5:
                    boutonDe.setImageResource(R.drawable.des_six);
                    nom_img = "des_six";
                    face = 6;
                    break;
                default:
                    boutonDe.setImageResource(R.drawable.des_six);
                    nom_img = "des_six";
                    face = 6;
                    Log.e("Chance anormale", "Vous avez eu beaucoup de chance :O");
                    break;
            }
        }else{
             random = face;
        }
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

    public int getFace() {
        return face;
    }
}
