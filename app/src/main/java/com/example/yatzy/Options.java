package com.example.yatzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    public static boolean dyslexique_font;
    private Switch dyslexique_switch;
    private SharedPreferences optionpref;
    private Typeface used_font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        optionpref = getSharedPreferences("option", MODE_PRIVATE);
        dyslexique_font = optionpref.getBoolean("dyslexique_font", false);

        dyslexique_switch = findViewById(R.id.dyslexique_switch);
        dyslexique_switch.setChecked(dyslexique_font);
        dyslexique_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dyslexique_font=!dyslexique_font;
                Toast.makeText(Options.this,Boolean.toString(dyslexique_font), Toast.LENGTH_SHORT).show();

                save();
            }
        });

    }


    private void save(){
        SharedPreferences.Editor editor = optionpref.edit();

        editor.putBoolean("dyslexique_font", this.dyslexique_font).apply();


    }

    public static void loadFont(View v, Context c, Typeface used_font){

        ViewGroup group = (ViewGroup) v.getParent();

        int i;
        for (i = 0; i<group.getChildCount(); i++){
            View child = group.getChildAt(i);
            if(child instanceof TextView){
                //((TextView) child).setTypeface(tf);
            }
        }

    }
}
