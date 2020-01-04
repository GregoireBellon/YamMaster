package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
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
        plateau.ajouterVuesCases();

        /*Typeface custom_font_player_turn = Typeface.createFromAsset(getAssets(),  "@font/alef_bold.ttf");
        Typeface custom_font_player_name = Typeface.createFromAsset(getAssets(), "font/seguibl.ttf");

        texteTourJoueur.setTypeface(custom_font_player_turn);
        texteNomJoueur.setTypeface(custom_font_player_name);*/
    }

    public void ajusterCases(Case caseAPlacer){
        caseAPlacer.getImageCase().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("Appui sur une case", "Case touchée");
                return true;
            }
        });
    }

    public ConstraintLayout getLayout() {
        return layout;
    }
}
