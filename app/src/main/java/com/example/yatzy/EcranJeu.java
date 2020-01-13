package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EcranJeu extends AppCompatActivity {

    TextView texteTourJoueur, texteNomJoueur;
    ImageView  containerDes;
    ImageButton gobelet, boutonMenu;
    ImageView jetonBlanc, jetonNoir;
    ConstraintLayout layoutPlateau;
    Plateau plateau;
    TextView jetonsBlancsRestants, jetonsNoirsRestants;
    De de1, de2, de3, de4, de5;
    Partie partie;
    Options options;
    DisplayMetrics tailleEcran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_jeu);

        options = new Options(this);
        tailleEcran = new DisplayMetrics();
        jetonBlanc = findViewById(R.id.jetonBlancPlateau);
        jetonBlanc.setY(tailleEcran.heightPixels / 9 );

        jetonNoir = findViewById(R.id.jetonNoirPlateau);
        jetonNoir.setY(tailleEcran.heightPixels / 9 );

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
        gobelet = findViewById(R.id.gobelet);
        layoutPlateau = findViewById(R.id.layoutPlateau);
        jetonsBlancsRestants = findViewById(R.id.jetonsBlancsRestants);
        jetonsNoirsRestants = findViewById(R.id.jetonsNoirsRestants);
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
        }else if(DataHolder.getHolder().getResult() == 5){
            setPartie(DataHolder.getHolder().getPartie());
            plateau = partie.getPlateau();
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
        if (caseAPlacer.getJetonPose() == null) {
            caseAPlacer.getImageCase().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (partie.getJoueurActuel().isPeutPoser()){
                        partie.getJoueurActuel().placerJeton(caseAPlacer);
                    }
                }
            });
        }
    }

    public void poserJeton(Jeton jetonAPoser, int coordX, int coordY){
        jetonAPoser.getView().setX(coordX);
        jetonAPoser.getView().setY(coordY);
        partie.getJoueurActuel().setPeutPoser(false);
        jetonsBlancsRestants.setText(""+partie.getJoueur1().getJetonsJoueurs().size());
        jetonsNoirsRestants.setText(""+partie.getJoueur2().getJetonsJoueurs().size());

        if (partie.getJoueur1().getJetonsJoueurs().size() == 0 && partie.getJoueur2().getJetonsJoueurs().size() == 0){
            partie.calculerScoresJoueurs();
            Intent finPartie = new Intent(EcranJeu.this, FinDePartie.class);
            DataHolder.getHolder().setScoreFinalJ1(partie.getJoueur1().getScore());
            DataHolder.getHolder().setScoreFinalJ2(partie.getJoueur2().getScore());
            startActivity(finPartie);
        }
    }

    public void afficherCasePosables(){
        Case[][] casesPlateau = plateau.getDispositionCases();
        layoutPlateau.removeAllViews();

        for (int i = 0 ; i < 5; i++){
            for (int j = 0; j < 5; j++){

                String typeCase = casesPlateau[i][j].getType().toString();
                String combChoisie;
                if (DataHolder.getHolder().getCombChoisie() == null)  combChoisie = "";
                else  combChoisie = DataHolder.getHolder().getCombChoisie().toString();

                if (casesPlateau[i][j].getJetonPose() != null){
                    casesPlateau[i][j].setPeutPoser(false);
                    plateau.ajouterUneVueCase(casesPlateau[i][j]);

                } else if (typeCase != combChoisie && combChoisie != ""){
                    casesPlateau[i][j].setPeutPoser(false);
                    plateau.ajouterUneVueCase(casesPlateau[i][j]);

                } else if(combChoisie == ""){
                    for (Combinaison combi : partie.getCombinaisonEnCours()){
                        if (typeCase != combi.toString()){
                            casesPlateau[i][j].setPeutPoser(false);
                            plateau.ajouterUneVueCase(casesPlateau[i][j]);
                        }else{
                            casesPlateau[i][j].setPeutPoser(true);
                            plateau.ajouterUneVueCase(casesPlateau[i][j]);
                        }
                    }

                }else{
                    casesPlateau[i][j].setPeutPoser(true);
                    plateau.ajouterUneVueCase(casesPlateau[i][j]);

                }
                if (partie.isDefiReussi() && typeCase == Combinaison.DEFI.toString()) {
                    casesPlateau[i][j].setPeutPoser(true);
                    plateau.ajouterUneVueCase(casesPlateau[i][j]);
                }
            }
        }
    }

<<<<<<< HEAD
    @Override
    public void onBackPressed(){

    }

=======
>>>>>>> 40e6453118a86880f6d4923be77ebe6c3ce43fdf
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
