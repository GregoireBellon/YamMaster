package com.example.yatzy;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class PopChoixCombi extends Activity {


    public static final int DEFI_CHOISI = 10;
    public RelativeLayout layout;
    public Button boutonPasserTour, boutonRevenirDes;
    private Button brelan, yam, carre, suite, sec, inf8, full;
    public Options options;
    private Partie partie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_combi);
        partie = DataHolder.getHolder().getPartie();

        options = new Options(this);

        layout = findViewById(R.id.fenetrePrincipale);

        //positionnement et redimensionnement de l'activité
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.85));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        yam = findViewById(R.id.yam);
        brelan = findViewById(R.id.brelan);
        suite = findViewById(R.id.suite);
        inf8 = findViewById(R.id.inf8);
        carre = findViewById(R.id.carre);
        full = findViewById(R.id.full);
        sec = findViewById(R.id.sec);

        getWindow().setAttributes(params);

        boutonPasserTour= findViewById(R.id.boutonPasserTour);
        boutonRevenirDes = findViewById(R.id.boutonRelancer);

        if (!partie.isChoixDefi()) {
            partie.determinerCombinaisons();
            afficherBoutonsCombi();
        }
        else{
            boutonPasserTour.setEnabled(false);
            boutonPasserTour.setAlpha(0.5f);

            brelan.setEnabled(false);
            brelan.setAlpha(0.5f);

            sec.setEnabled(false);
            sec.setAlpha(0.5f);
        }

        onClickBoutonsCombi();

        if (partie.getJoueurActuel().getNbrLancers() == 3){
            boutonRevenirDes.setEnabled(false);
            boutonRevenirDes.setAlpha(0.5f);
        }else{
            boutonRevenirDes.setEnabled(true);
            boutonRevenirDes.setAlpha(1f);
        }


        boutonPasserTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partie.passerTour();
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                DataHolder.getHolder().setResult(3);
                finish();
            }
        });

        boutonRevenirDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partie.isChoixDefi()){
                    partie.setChoixDefi(false);
                }
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                finish();
            }
        });

    }

    private void onClickBoutonsCombi() {
        yam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partie.isChoixDefi()){
                    partie.getJoueurActuel().setCombinaisonDefi(Combinaison.YAM);
                    partie.setChoixDefi(false);
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(DEFI_CHOISI);
                    finish();
                }else{
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(RESULT_OK);
                    DataHolder.getHolder().setCombChoisie(Combinaison.YAM);
                    finish();
                }

            }
        });

        carre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partie.isChoixDefi()){
                    partie.getJoueurActuel().setCombinaisonDefi(Combinaison.CARRE);
                    partie.setChoixDefi(false);
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(DEFI_CHOISI);
                    finish();
                }else{
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(RESULT_OK);
                    DataHolder.getHolder().setCombChoisie(Combinaison.CARRE);
                    finish();
                }
            }
        });

        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partie.isChoixDefi()){
                    partie.getJoueurActuel().setCombinaisonDefi(Combinaison.FULL);
                    partie.setChoixDefi(false);
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(DEFI_CHOISI);
                    finish();
                }else{
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(RESULT_OK);
                    DataHolder.getHolder().setCombChoisie(Combinaison.FULL);
                    finish();
                }
            }
        });

        brelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!partie.isChoixDefi()){

                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(RESULT_OK);
                    DataHolder.getHolder().setCombChoisie(null);
                    finish();
                }
            }
        });

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!partie.isChoixDefi()){
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(RESULT_OK);
                    DataHolder.getHolder().setCombChoisie(Combinaison.SEC);
                    finish();
                }
            }
        });

        suite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partie.isChoixDefi()){
                    partie.getJoueurActuel().setCombinaisonDefi(Combinaison.SUITE);
                    partie.setChoixDefi(false);
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(DEFI_CHOISI);
                    finish();
                }else{
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(RESULT_OK);
                    DataHolder.getHolder().setCombChoisie(Combinaison.SUITE);
                    finish();
                }
            }
        });

        inf8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partie.isChoixDefi()){
                    partie.getJoueurActuel().setCombinaisonDefi(Combinaison.INF8);
                    partie.setChoixDefi(false);
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(DEFI_CHOISI);
                    finish();
                }else{
                    DataHolder.getHolder().setPartie(partie);
                    DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                    DataHolder.getHolder().setResult(RESULT_OK);
                    DataHolder.getHolder().setCombChoisie(Combinaison.INF8);
                    finish();
                }
            }
        });
    }

    private void afficherBoutonsCombi() {


        if (!partie.getCombinaisonEnCours().contains(Combinaison.YAM)||combinaisonsComplete(Combinaison.YAM)){
            yam.setEnabled(false);
            yam.setAlpha(0.5f);
        }

        if (!partie.getCombinaisonEnCours().contains(Combinaison.SUITE)||combinaisonsComplete(Combinaison.SUITE)){
            suite.setEnabled(false);
            suite.setAlpha(0.5f);
        }

        if (!partie.getCombinaisonEnCours().contains(Combinaison.INF8)||combinaisonsComplete(Combinaison.INF8)){
            inf8.setEnabled(false);
            inf8.setAlpha(0.5f);
        }
        if (!partie.getCombinaisonEnCours().contains(Combinaison.CARRE)||combinaisonsComplete(Combinaison.CARRE)){
            carre.setEnabled(false);
            carre.setAlpha(0.5f);
        }
        if (!partie.getCombinaisonEnCours().contains(Combinaison.SEC)||combinaisonsComplete(Combinaison.SEC)){
            sec.setEnabled(false);
            sec.setAlpha(0.5f);
        }
        if (!partie.getCombinaisonEnCours().contains(Combinaison.FULL)||combinaisonsComplete(Combinaison.FULL)){
            full.setEnabled(false);
            full.setAlpha(0.5f);
        }

        if ((!partie.getCombinaisonEnCours().contains(Combinaison.BRELAN1)||combinaisonsComplete(Combinaison.BRELAN1)) &&
                (!partie.getCombinaisonEnCours().contains(Combinaison.BRELAN2)||combinaisonsComplete(Combinaison.BRELAN2)) &&
                (!partie.getCombinaisonEnCours().contains(Combinaison.BRELAN3)||combinaisonsComplete(Combinaison.BRELAN3)) &&
                (!partie.getCombinaisonEnCours().contains(Combinaison.BRELAN4)||combinaisonsComplete(Combinaison.BRELAN4)) &&
                (!partie.getCombinaisonEnCours().contains(Combinaison.BRELAN5)||combinaisonsComplete(Combinaison.BRELAN5)) &&
                (!partie.getCombinaisonEnCours().contains(Combinaison.BRELAN6)||combinaisonsComplete(Combinaison.BRELAN6))){
            brelan.setEnabled(false);
            brelan.setAlpha(0.5f);
        }
    }

    public boolean combinaisonsComplete(Combinaison combi){
        Case[][] casesPlateau = partie.getPlateau().getDispositionCases();
        int cptCasesOccupees = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (casesPlateau[i][j].getType().toString() == combi.toString() && casesPlateau[i][j].getJetonPose() != null ){
                    cptCasesOccupees++;
                }
            }
        }
        if (combi != Combinaison.YAM && cptCasesOccupees == 2){
            return true;
        }else if (combi == Combinaison.YAM && cptCasesOccupees == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void onBackPressed(){

    }
}
