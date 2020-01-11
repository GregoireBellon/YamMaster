package com.example.yatzy;

import android.app.Activity;
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

import com.example.yatzy.model.SoundEffects;

public class PopChoixCombi extends Activity {


    public RelativeLayout layout;
    public RelativeLayout.LayoutParams layoutParams;

    public Switch switchDyslex, switchDalton;
    public SeekBar seekbarMusic, seekbarEffects;
    public Button boutonParDefaut, boutonAccepter;
    public ImageButton boutonExit;

    public Options options;

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

    }



}
