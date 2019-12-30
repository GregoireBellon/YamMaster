package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
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
        texteTourJoueur = (TextView)findViewById(R.id.texteIndicTourJoueur);
        texteNomJoueur = (TextView)findViewById(R.id.textIndicNomJoueur);
        containerDes = findViewById(R.id.containerDes);
        lancerRestants1 = findViewById(R.id.lancerRestant1);
        lancerRestants2 = findViewById(R.id.lancerRestant2);
        lancerRestants3 = findViewById(R.id.lancerRestant3);
        gobelet = findViewById(R.id.gobelet);
        layout = findViewById(R.id.layoutJeu);
        plateau = new Plateau(this);
        plateau.ajouterVuesCases();

        Typeface custom_font_player_turn = Typeface.createFromAsset(getAssets(),  "fonts/Alef-Bold.ttf");
        Typeface custom_font_player_name = Typeface.createFromAsset(getAssets(), "fonts/seguibl.ttf");

        texteTourJoueur.setTypeface(custom_font_player_turn);
        texteNomJoueur.setTypeface(custom_font_player_name);
    }

    public void ajusterCases(Case caseAPlacer){
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(plateau.getDimensionCases(),plateau.getDimensionCases());
        addContentView(caseAPlacer.getImageCase(),param);
        caseAPlacer.getImageCase().setX(caseAPlacer.getCoordX());
        caseAPlacer.getImageCase().setY(caseAPlacer.getCoordY());
        final String test = caseAPlacer.getType().toString();
        caseAPlacer.getImageCase().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Dialog dialog = new Dialog(EcranJeu.this);
                dialog.setContentView(R.layout.test_cases);
                TextView texte = dialog.findViewById(R.id.typeCase);
                texte.setText("Combinaison : "+ test);
                dialog.show();
                return true;
            }
        });
    }

    public ConstraintLayout getLayout() {
        return layout;
    }
}
