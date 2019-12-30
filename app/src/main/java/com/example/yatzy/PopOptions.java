package com.example.yatzy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yatzy.model.SoundEffects;
import com.example.yatzy.model.SoundMusic;

public class PopOptions extends Activity {


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
        setContentView(R.layout.activity_pop_options);

        options = new Options(this);

        layout = findViewById(R.id.fenetrePrincipale);

        switchDyslex = findViewById(R.id.switchDyslex);
        switchDalton = findViewById(R.id.switchDalton);

        boutonParDefaut = findViewById(R.id.bouton_par_defaut);
        boutonAccepter = findViewById(R.id.bouton_accepter);
        boutonExit = findViewById(R.id.boutonExit);

        seekbarMusic = findViewById(R.id.seekbarMusic);
        seekbarMusic.setMax(options.maxMusicVolume());

        seekbarEffects = findViewById(R.id.seekbarEffects);

        boutonParDefaut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer sfxTest = MediaPlayer.create(PopOptions.this, R.raw.ok);
                SoundEffects.play(sfxTest);
                setDefault();
            }
        });

        boutonAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                finish();
                load();
            }
        });

        boutonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        seekbarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                options.setVolume(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {/* TODO Auto-generated method stub */}

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekbarEffects.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SoundEffects.setVolume((float) progress / 100f);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {/* TODO Auto-generated method stub */}

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        switchDyslex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
            }
        });

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

        load();
        update();
    }

    /**
     * Reset par défaut les paramètres (volumes à 50%, handicaps désactivés)
     */
    public void setDefault() {
        if (this.switchDalton.isChecked())
            this.switchDalton.toggle();
        if (this.switchDyslex.isChecked())
            this.switchDyslex.toggle();

        this.seekbarMusic.setProgress(seekbarMusic.getMax() / 2);
        this.seekbarEffects.setProgress(seekbarEffects.getMax() / 2);
    }

    /**
     * Permet de sauvegarder les paramètres dans les sharedPreferences
     */
    public void save() {
        options.save(seekbarEffects.getProgress(), seekbarMusic.getProgress(), switchDalton.isChecked(), switchDyslex.isChecked());

/*        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(SEEKBAR_EFFECTS, seekbarEffects.getProgress());
        editor.putInt(SEEKBAR_MUSIC, seekbarMusic.getProgress());

        editor.putBoolean(SWITCH_DALTON, switchDalton.isChecked());
        editor.putBoolean(SWITCH_DYSLEX, switchDyslex.isChecked());

        editor.putFloat(SoundMusic.MUSIC_VOLUME, SoundMusic.getVolume());
        editor.putFloat(SoundEffects.EFFECTS_VOLUME, SoundEffects.getVolume());

        editor.apply();*/

    }

    /**
     * Permet d'instancier les variables d'options d'après les sharedPreferences
     */
    public void load() {
        options.load();
/*
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        progressSeekbarEffects = sharedPreferences.getInt(SEEKBAR_EFFECTS, (seekbarEffects.getMax()/2));
        progressSeekbarMusic = sharedPreferences.getInt(SEEKBAR_MUSIC, (seekbarMusic.getMax()/2));

        switchDaltonState = sharedPreferences.getBoolean(SWITCH_DALTON, false);
        switchDyslexState = sharedPreferences.getBoolean(SWITCH_DYSLEX, false);

        SoundMusic.setVolume((float) progressSeekbarMusic/100f);
        SoundEffects.setVolume((float) progressSeekbarEffects/100f);

 */
    }


    /**
     * Permet de mettre à jour les paramètres de la fenêtre d'après les sharedPreferences
     */
    public void update() {
        seekbarEffects.setProgress(options.PROGRESS_SEEKBAR_EFFECTS);
        seekbarMusic.setProgress(options.PROGRESS_SEEKBAR_MUSIC);

        switchDalton.setChecked(options.SWITCH_DALTON_STATE);
        switchDyslex.setChecked(options.SWITCH_DYSLEX_STATE);


        loadFont();
    }

    public void loadFont()
    {
        ViewGroup group = getWindow().getDecorView().findViewById(android.R.id.content);

        int i;
        for (i = 0; i < group.getChildCount(); i++)
        {
            View child = group.getChildAt(i);
            if(child instanceof TextView)
            {
                ((TextView) child).setTypeface(options.CURRENT_FONT);
            }
            if(child instanceof Button)
            {
                ((Button) child).setTypeface(options.CURRENT_FONT);
            }
        }

    }

}
