package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuJouer extends AppCompatActivity {

    private final int VERSUS = 2;
    private final int IA = 1;
    private final int NO_SELECT = 0;

    private int selected_button;

    private ImageButton retour;
    private Button vs;
    private Button ia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jouer);
        initValues();

        retour = findViewById(R.id.imageButton2);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retournerEcranAccueil();
            }
        });

    }

    private void retournerEcranAccueil() {
        Intent intent = new Intent(this, EcranAccueil.class);
        startActivity(intent);
        finish();
    }

    private void initValues(){

        selected_button = NO_SELECT;

        vs = findViewById(R.id.versus_button);

        vs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected_button != VERSUS){
                    selected_button = VERSUS;
                }
                else{
                    selected_button = NO_SELECT;

                }

                Log.e("Button","CHANGED VS");


                refreshButtonColor();
            }
        });


        ia = findViewById(R.id.is_button);

        ia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected_button != IA){

                    selected_button = IA;

                }
                else{
                    selected_button = NO_SELECT;
                }

                Log.e("Button","CHANGED IA");

                refreshButtonColor();

            }
        });


    }

    private void refreshButtonColor(){

        Drawable not_clicked = getDrawable(R.drawable.background_click_button);
        Drawable clicked = getDrawable(R.drawable.background_clicked_button);

        switch (selected_button){
            case NO_SELECT : vs.setBackground(not_clicked);
                ia.setBackground(not_clicked);
                Log.e("Button color","no select");
                break;
            case IA : vs.setBackground(not_clicked);
                ia.setBackground(clicked);
                Log.e("Button color","IA color");
                break;
            case VERSUS: ia.setBackground(not_clicked);
                vs.setBackground(clicked);
                Log.e("Button color","VS color");
                break;

            default: vs.setBackground(not_clicked); ia.setBackground(not_clicked);

        }
    }
}
