package com.example.yatzy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yatzy.model.SoundMusic;

public class EcranAccueil extends AppCompatActivity {

    TextView nomJeu;
    ImageButton boutonJouer, boutonOptions, boutonEdit;

    public MediaPlayer themeDeGerardDeRiviere;

    Options options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_accueil);

        options = new Options(this);

        themeDeGerardDeRiviere = MediaPlayer.create(EcranAccueil.this, R.raw.a1_test);
        themeDeGerardDeRiviere.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // ------- Initialisation des éléments de l'application
        nomJeu = findViewById(R.id.nomJeu);
        boutonJouer = findViewById(R.id.boutonJouer);

        boutonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(EcranAccueil.this, EcranJeu.class);
                startActivity(myIntent);
            }
        });

        boutonEdit = findViewById(R.id.boutonEdit);
        boutonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(EcranAccueil.this, EcranEdition.class);
                startActivity(myIntent);
            }
        });

        boutonOptions = findViewById(R.id.boutonOptions);
        boutonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPopup = new Intent(getApplicationContext(), PopOptions.class);
                startActivity(intentPopup);

            }

        });

        //SoundMusic.setVolume(options.getSavedMusicVolume());
        SoundMusic.play(themeDeGerardDeRiviere);

    }

    @Override
    protected void onResume() {
        super.onResume();

        options = new Options(this);
    }
}
