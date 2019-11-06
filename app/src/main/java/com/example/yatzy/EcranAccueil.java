package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class EcranAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_accueil);
        getSupportActionBar().hide();
    }
}
