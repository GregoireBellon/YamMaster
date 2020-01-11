package com.example.yatzy;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PopDes extends Activity {

    ImageButton imageDe1, imageDe2, imageDe3, imageDe4, imageDe5;
    De de1, de2, de3, de4, de5;
    Button boutonLancer;
    ConstraintLayout layout;
    List<ImageButton> listeImgDes;
    Partie partie;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_des);

        listeImgDes = new ArrayList<>();

        partie = DataHolder.getHolder().getPartie();

        imageDe1 = findViewById(R.id.des1);
        imageDe2 = findViewById(R.id.des2);
        imageDe3 = findViewById(R.id.des3);
        imageDe4 = findViewById(R.id.des4);
        imageDe5 = findViewById(R.id.des5);

        listeImgDes.add(imageDe1);
        listeImgDes.add(imageDe2);
        listeImgDes.add(imageDe3);
        listeImgDes.add(imageDe4);
        listeImgDes.add(imageDe5);

        boutonLancer = findViewById(R.id.boutonLancer);
        boutonLancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                partie.lancersDes();
                for (ImageButton img : listeImgDes){
                    De de = partie.getListeDes().get(i);
                    Resources res = getResources();
                    int resId = res.getIdentifier(de.getNom_img(), "drawable", getPackageName());
                    img.setImageResource(resId);
                    i++;
                }
            }
        });
    }
}
