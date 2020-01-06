package com.example.yatzy;

import android.widget.ImageButton;

public class De extends com.example.yatzy.model.De{

    private ImageButton boutonDe;
    private Plateau plateau;

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
            case 1: boutonDe.setImageResource(R.drawable.des_deux);
            case 2: boutonDe.setImageResource(R.drawable.des_trois);
            case 3: boutonDe.setImageResource(R.drawable.des_quatre);
            case 4: boutonDe.setImageResource(R.drawable.des_cinq);
            case 5: boutonDe.setImageResource(R.drawable.des_six);
            default: boutonDe.setImageResource(R.drawable.des_un);
        }
        return random;
    }
}
