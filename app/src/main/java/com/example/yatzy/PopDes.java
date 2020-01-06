package com.example.yatzy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PopDes extends Activity {

    ImageButton imageDe1, imageDe2, imageDe3, imageDe4, imageDe5;
    De de1, de2, de3, de4, de5;
    Button boutonLancer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_des);
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

        boutonLancer = findViewById(R.id.boutonLancer);
        boutonLancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                de1.rouler();
                de2.rouler();
                de3.rouler();
                de4.rouler();
                de5.rouler();
            }
        });
    }
}
