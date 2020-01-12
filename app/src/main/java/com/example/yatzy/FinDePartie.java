package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FinDePartie extends AppCompatActivity {

    Button boutonAccueil;
    TextView texteScoreJ1, texteScoreJ2, texteGagnant;
    int scoreJ1, scoreJ2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_fin_de_partie);

        boutonAccueil = findViewById(R.id.boutonAccueil);
        texteScoreJ1 = findViewById(R.id.scoreJ1);
        texteScoreJ2 = findViewById(R.id.scoreJ2);
        texteGagnant = findViewById(R.id.texteGagnant);

        scoreJ1 = DataHolder.getHolder().getScoreFinalJ1();
        scoreJ2 = DataHolder.getHolder().getScoreFinalJ2();

        texteScoreJ1.setText(""+scoreJ1);
        texteScoreJ2.setText(""+scoreJ2);

        if (scoreJ2 > scoreJ1) texteGagnant.setText(R.string.texteJ2Gagnant);
        else if (scoreJ1 > scoreJ2) texteGagnant.setText(R.string.texteJ1Gagnant);
        else texteGagnant.setText(R.string.texteEgalite);
    }
}
