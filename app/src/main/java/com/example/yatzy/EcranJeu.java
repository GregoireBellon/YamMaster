package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EcranJeu extends AppCompatActivity {

    TextView texteTourJoueur, texteNomJoueur;
    ImageView  containerDes, lancerRestants1, lancerRestants2, lancerRestants3;
    ImageButton gobelet;
    ConstraintLayout layout;
    Plateau plateau;
    De de1, de2, de3, de4, de5;
    Partie partie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_jeu);

        // ------- Initialisation des éléments de l'application
        texteTourJoueur = findViewById(R.id.texteIndicTourJoueur);
        texteNomJoueur = findViewById(R.id.textIndicNomJoueur);
        containerDes = findViewById(R.id.containerDes);
        lancerRestants1 = findViewById(R.id.lancerRestant1);
        lancerRestants2 = findViewById(R.id.lancerRestant2);
        lancerRestants3 = findViewById(R.id.lancerRestant3);
        gobelet = findViewById(R.id.gobelet);
        layout = findViewById(R.id.layoutJeu);
        plateau = new Plateau(this);
        partie = new Partie(this);

        de1 = new De(plateau);
        de2 = new De(plateau);
        de3 = new De(plateau);
        de4 = new De(plateau);
        de5 = new De(plateau);

        DataHolder.getHolder().setDe1(de1);
        DataHolder.getHolder().setDe2(de2);
        DataHolder.getHolder().setDe3(de3);
        DataHolder.getHolder().setDe4(de4);
        DataHolder.getHolder().setDe5(de5);

        /*Typeface custom_font_player_turn = Typeface.createFromAsset(getAssets(),  "@font/alef_bold.ttf");
        Typeface custom_font_player_name = Typeface.createFromAsset(getAssets(), "font/seguibl.ttf");

        texteTourJoueur.setTypeface(custom_font_player_turn);
        texteNomJoueur.setTypeface(custom_font_player_name);*/

        // Lancement du tour pour le J1
        gobelet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intentPopupDes = new Intent(getApplicationContext(), PopDes.class);
                startActivity(intentPopupDes);
                return true;
            }
        });


    }

    public void ajusterCases(final Case caseAPlacer){
        caseAPlacer.getImageCase().setX(caseAPlacer.getCoordX());
        caseAPlacer.getImageCase().setY(caseAPlacer.getCoordY());
        caseAPlacer.getImageCase().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                partie.placerJeton(caseAPlacer);
                return true;
            }
        });
    }
    public void poserJeton(Jeton jetonAPoser, int coordX, int coordY){
        jetonAPoser.getView().setX(coordX);
        jetonAPoser.getView().setY(coordY);
    }

    public ConstraintLayout getLayout() {
        return layout;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
