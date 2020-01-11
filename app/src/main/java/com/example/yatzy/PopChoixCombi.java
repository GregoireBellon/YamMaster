package com.example.yatzy;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.yatzy.model.SoundEffects;

public class PopChoixCombi extends Activity {


    public RelativeLayout layout;
    public RelativeLayout.LayoutParams layoutParams;

    public Switch switchDyslex, switchDalton;
    public SeekBar seekbarMusic, seekbarEffects;
    public Button boutonParDefaut, boutonAccepter;
    public Button boutonPasserTour, boutonRevenirDes;
    public ImageButton boutonExit;
    private Button brelan, yam, carre, suite, sec, inf8, full;
    public Options options;
    private Partie partie;

   /* public static String SHARED_PREFS = "sharedPrefs";
    public static String SEEKBAR_EFFECTS = "seekbarEffects";
    public static String SEEKBAR_MUSIC = "seekbarMusic";
    public static String SWITCH_DYSLEX = "switchDyslex";
    public static String SWITCH_DALTON = "switchDalton";

    private int progressSeekbarEffects, progressSeekbarMusic;
    private boolean switchDaltonState, switchDyslexState;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_combi);
        partie = DataHolder.getHolder().getPartie();
        partie.determinerCombinaisons();

        afficherBoutonsCombi();

        onClickBoutonsCombi();

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

        getWindow().setAttributes(params);

        boutonPasserTour= findViewById(R.id.boutonPasserTour);
        boutonRevenirDes = findViewById(R.id.boutonRelancer);

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
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                DataHolder.getHolder().setResult(RESULT_OK);
                finish();
            }
        });

        carre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                DataHolder.getHolder().setResult(RESULT_OK);
                finish();
            }
        });

        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                DataHolder.getHolder().setResult(RESULT_OK);
                finish();
            }
        });

        brelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                DataHolder.getHolder().setResult(RESULT_OK);
                finish();
            }
        });

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                DataHolder.getHolder().setResult(RESULT_OK);
                finish();
            }
        });

        suite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                DataHolder.getHolder().setResult(RESULT_OK);
                finish();;
            }
        });

        inf8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.getHolder().setPartie(partie);
                DataHolder.getHolder().getPartieEnCours().setPartie(partie);
                DataHolder.getHolder().setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void afficherBoutonsCombi() {
        yam = findViewById(R.id.yam);
        brelan = findViewById(R.id.brelan);
        suite = findViewById(R.id.suite);
        inf8 = findViewById(R.id.inf8);
        carre = findViewById(R.id.carre);
        full = findViewById(R.id.full);
        sec = findViewById(R.id.sec);

        if (!partie.getCombinaisonEnCours().contains(Combinaison.YAM)){
            yam.setEnabled(false);
            yam.setAlpha(0.5f);
        }

        if (!partie.getCombinaisonEnCours().contains(Combinaison.SUITE)){
            suite.setEnabled(false);
            suite.setAlpha(0.5f);
        }

        if (!partie.getCombinaisonEnCours().contains(Combinaison.INF8)){
            inf8.setEnabled(false);
            inf8.setAlpha(0.5f);
        }
        if (!partie.getCombinaisonEnCours().contains(Combinaison.CARRE)){
            carre.setEnabled(false);
            carre.setAlpha(0.5f);
        }
        if (!partie.getCombinaisonEnCours().contains(Combinaison.SEC)){
            sec.setEnabled(false);
            sec.setAlpha(0.5f);
        }
        if (!partie.getCombinaisonEnCours().contains(Combinaison.FULL)){
            full.setEnabled(false);
            full.setAlpha(0.5f);
        }

        if (!partie.getCombinaisonEnCours().contains(Combinaison.BRELAN1) &&
                !partie.getCombinaisonEnCours().contains(Combinaison.BRELAN2) &&
                !partie.getCombinaisonEnCours().contains(Combinaison.BRELAN3) &&
                !partie.getCombinaisonEnCours().contains(Combinaison.BRELAN4) &&
                !partie.getCombinaisonEnCours().contains(Combinaison.BRELAN5) &&
                !partie.getCombinaisonEnCours().contains(Combinaison.BRELAN6)){
            brelan.setEnabled(false);
            brelan.setAlpha(0.5f);
        }
    }


}
