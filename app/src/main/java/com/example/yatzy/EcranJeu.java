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
    ImageButton gobelet, boutonMenu;
    ConstraintLayout layoutPlateau;
    Plateau plateau;
    De de1, de2, de3, de4, de5;
    Partie partie;
    Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_jeu);

        options = new Options(this);

        boutonMenu = findViewById(R.id.boutonMenu);
        boutonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPopup = new Intent(getApplicationContext(), PopOptions.class);
                startActivity(intentPopup);

            }
        });

        // ------- Initialisation des éléments de l'application
        texteTourJoueur = findViewById(R.id.texteIndicTourJoueur);
        texteNomJoueur = findViewById(R.id.textIndicNomJoueur);
        containerDes = findViewById(R.id.containerDes);
        lancerRestants1 = findViewById(R.id.lancerRestant1);
        lancerRestants2 = findViewById(R.id.lancerRestant2);
        lancerRestants3 = findViewById(R.id.lancerRestant3);
        gobelet = findViewById(R.id.gobelet);
        layoutPlateau = findViewById(R.id.layoutPlateau);

        de1 = new De(this);
        de2 = new De(this);
        de3 = new De(this);
        de4 = new De(this);
        de5 = new De(this);

        initDataHolder();

        plateau = new Plateau(this);
        partie = new Partie(this);


        texteNomJoueur.setText(R.string.nomJoueur1);
        gobelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().setPartieEnCours(EcranJeu.this);
                Intent intentPopupDes = new Intent(getApplicationContext(), PopDes.class);
                startActivity(intentPopupDes);
            }
        });
    }

    private void initDataHolder() {
        DataHolder.getHolder().getListeDes().add(de1);
        DataHolder.getHolder().getListeDes().add(de2);
        DataHolder.getHolder().getListeDes().add(de3);
        DataHolder.getHolder().getListeDes().add(de4);
        DataHolder.getHolder().getListeDes().add(de5);

        DataHolder.getHolder().setDe1(de1);
        DataHolder.getHolder().setDe2(de2);
        DataHolder.getHolder().setDe3(de3);
        DataHolder.getHolder().setDe4(de4);
        DataHolder.getHolder().setDe5(de5);

        DataHolder.getHolder().setPartie(partie);
        DataHolder.getHolder().setResult(RESULT_FIRST_USER);
        DataHolder.getHolder().setPartieEnCours(EcranJeu.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        options = new Options(this);
        if (DataHolder.getHolder().getResult() == RESULT_CANCELED){
            setPartie(DataHolder.getHolder().getPartie());
            plateau = partie.getPlateau();
            afficherCasePosables();
            partie.getJoueurActuel().setPeutPoser(true);
        }
    }

    public void ajusterCases(final Case caseAPlacer){
        caseAPlacer.getImageCase().setX(caseAPlacer.getCoordX());
        caseAPlacer.getImageCase().setY(caseAPlacer.getCoordY());
        if (!caseAPlacer.isPeutPoser()){
            caseAPlacer.getImageCase().setEnabled(false);
            caseAPlacer.getImageCase().setAlpha(0.5f);
        }else{
            caseAPlacer.getImageCase().setEnabled(true);
            caseAPlacer.getImageCase().setAlpha(1f);
        }
        caseAPlacer.getImageCase().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partie.getJoueurActuel().isPeutPoser()){
                    partie.getJoueurActuel().placerJeton(caseAPlacer);
                }
            }
        });
    }

    public void poserJeton(Jeton jetonAPoser, int coordX, int coordY){
        jetonAPoser.getView().setX(coordX);
        jetonAPoser.getView().setY(coordY);
        partie.getJoueurActuel().setPeutPoser(false);
        partie.passerTour();
    }

    public void afficherCasePosables(){
        Case[][] casesPlateau = plateau.getDispositionCases();
        layoutPlateau.removeAllViews();
        for (int i = 0 ; i < 5; i++){
            for (int j = 0; j < 5; j++){
                for (Combinaison combi : partie.getCombinaisonEnCours()){
                    if (casesPlateau[i][j].getType().toString() != combi.toString()){
                       casesPlateau[i][j].setPeutPoser(false);
                       plateau.ajouterUneVueCase(casesPlateau[i][j]);
                    }else{
                       casesPlateau[i][j].setPeutPoser(true);
                       plateau.ajouterUneVueCase(casesPlateau[i][j]);
                    }
                }
            }
        }
    }

    /*public void compterPoints*/

    public ConstraintLayout getLayoutPlateau() {
        return layoutPlateau;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public TextView getTexteNomJoueur() {
        return texteNomJoueur;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}
