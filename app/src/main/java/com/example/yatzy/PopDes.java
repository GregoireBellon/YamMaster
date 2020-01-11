package com.example.yatzy;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PopDes extends Activity {

    ImageButton imageDe1, imageDe2, imageDe3, imageDe4, imageDe5;
    ImageView tick1, tick2, tick3, tick4, tick5;
    Button boutonLancer;
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

        tick1 = findViewById(R.id.tick1);
        tick2 = findViewById(R.id.tick2);
        tick3 = findViewById(R.id.tick3);
        tick4 = findViewById(R.id.tick4);
        tick5 = findViewById(R.id.tick5);


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

        imageDe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.getHolder().getDe1().isSelected()){
                    DataHolder.getHolder().getDe1().setSelected(false);
                    tick1.setVisibility(View.INVISIBLE);
                }else {
                    DataHolder.getHolder().getDe1().setSelected(true);
                    tick1.setVisibility(View.VISIBLE);
                }
            }
        });

        imageDe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.getHolder().getDe2().isSelected()){
                    DataHolder.getHolder().getDe2().setSelected(false);
                    tick2.setVisibility(View.INVISIBLE);
                }else {
                    DataHolder.getHolder().getDe2().setSelected(true);
                    tick2.setVisibility(View.VISIBLE);
                }
            }
        });

        imageDe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.getHolder().getDe3().isSelected()){
                    DataHolder.getHolder().getDe3().setSelected(false);
                    tick3.setVisibility(View.INVISIBLE);
                }else {
                    DataHolder.getHolder().getDe3().setSelected(true);
                    tick3.setVisibility(View.VISIBLE);
                }
            }
        });

        imageDe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.getHolder().getDe4().isSelected()){
                    DataHolder.getHolder().getDe4().setSelected(false);
                    tick4.setVisibility(View.INVISIBLE);
                }else {
                    DataHolder.getHolder().getDe4().setSelected(true);
                    tick4.setVisibility(View.VISIBLE);
                }
            }
        });

        imageDe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataHolder.getHolder().getDe5().isSelected()){
                    DataHolder.getHolder().getDe5().setSelected(false);
                    tick5.setVisibility(View.INVISIBLE);
                }else {
                    DataHolder.getHolder().getDe5().setSelected(true);
                    tick5.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
