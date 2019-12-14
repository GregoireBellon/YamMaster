package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class EcranAccueil extends AppCompatActivity {

    ImageButton boutonJouer;
    ImageButton boutonOptions;
    ImageButton boutonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_accueil);

        boutonJouer = findViewById(R.id.boutonJouer);
        boutonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(EcranAccueil.this, MenuJouer.class);
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
                Intent go_options = new Intent(EcranAccueil.this, Options.class);
                startActivity(go_options);
            }

        });
    }
}
