package com.example.yatzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EcranEdition extends AppCompatActivity {

    ImageButton btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_edition);

        btnRetour = findViewById(R.id.retourBtn);
        btnRetour.setOnTouchListener(new View.OnTouchListener() {
                                         @Override
                                         public boolean onTouch(View v, MotionEvent event) {
                                             Intent myIntent = new Intent(EcranEdition.this, EcranAccueil.class);
                                             startActivity(myIntent);
                                             return true;
                                         }
                                     }
        );
    }

}

