package com.example.yatzy;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.LinkedList;
import java.util.List;

public class PopDes extends Activity {

    ImageButton imageDe1, imageDe2, imageDe3, imageDe4, imageDe5;
    De de1, de2, de3, de4, de5;
    Button boutonLancer;
    ConstraintLayout layout;
    List<De> listeDes;
    List<ImageButton> listeImgDes;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_des);

        listeDes = new LinkedList();
        listeImgDes = new LinkedList();

        imageDe1 = findViewById(R.id.des1);
        imageDe2 = findViewById(R.id.des2);
        imageDe3 = findViewById(R.id.des3);
        imageDe4 = findViewById(R.id.des4);
        imageDe5 = findViewById(R.id.des5);

        de1 = DataHolder.getHolder().getDe1();
        de2 = DataHolder.getHolder().getDe2();
        de3 = DataHolder.getHolder().getDe3();
        de4 = DataHolder.getHolder().getDe4();
        de5 = DataHolder.getHolder().getDe5();

        listeImgDes.add(imageDe1);
        listeImgDes.add(imageDe2);
        listeImgDes.add(imageDe3);
        listeImgDes.add(imageDe4);
        listeImgDes.add(imageDe5);

        listeDes.add(de1);
        listeDes.add(de2);
        listeDes.add(de3);
        listeDes.add(de4);
        listeDes.add(de5);

        boutonLancer = findViewById(R.id.boutonLancer);
        boutonLancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Appuie", "Lancer");
                int i = 0;
                for (De de: listeDes) {
                    de.rouler();
                }

                DataHolder.getHolder().setDe1(de1);
                DataHolder.getHolder().setDe2(de2);
                DataHolder.getHolder().setDe3(de3);
                DataHolder.getHolder().setDe4(de4);
                DataHolder.getHolder().setDe5(de5);

                for (ImageButton img : listeImgDes){
                    De de = listeDes.get(i);
                    Resources res = getResources();
                    int resId = res.getIdentifier(de.getNom_img(), "drawable", getPackageName());
                    img.setImageResource(resId);
                    i++;
                }
            }
        });
    }
}
