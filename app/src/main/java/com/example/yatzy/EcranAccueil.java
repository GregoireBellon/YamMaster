package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class EcranAccueil extends AppCompatActivity {

    ImageButton boutonJouer;

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
    }
}
