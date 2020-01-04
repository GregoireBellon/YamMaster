package com.example.yatzy;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntroFragment;

public class MenuTuto extends com.github.paolorotolo.appintro.AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        /*addSlide(AppIntroFragment.newInstance("VOTRE PERSONNAGE", "Ça c'est vous", R.drawable.perso_mieux, ContextCompat.getColor(getApplicationContext(), R.color.marronClair)));
        addSlide(AppIntroFragment.newInstance("VOS DÉPLACEMENTS", "Pour vous déplacer, cliquez une fois à un endroit", R.drawable.clic1, ContextCompat.getColor(getApplicationContext(), R.color.marronSombre)));
        addSlide(AppIntroFragment.newInstance("VOTRE BESOIN DE VITESSE", "Votre besoin de vitesse est maintenant à satiété", R.drawable.clic2, ContextCompat.getColor(getApplicationContext(), R.color.marronClair)));
        addSlide(AppIntroFragment.newInstance("VOS ENNEMIS", "Voici un des ennemis que vous pourrez rencontrer.\nVous avez de la chance, il est idiot, et ne peut pas voir et se déplacer en même temps.", R.drawable.ennemi_aveugle, ContextCompat.getColor(getApplicationContext(), R.color.marronSombre)));
        addSlide(AppIntroFragment.newInstance("VOS ENNEMIS", "Pour lui échapper, vous pouvez vous cacher dans cette boite en verre, en cliquant dessus.", R.drawable.test_item, ContextCompat.getColor(getApplicationContext(), R.color.marronClair)));
        addSlide(AppIntroFragment.newInstance("VOS ENNEMIS", "Voici un second ennemi.\nCelui-ci peut vous voir.", R.drawable.gros_mechant, ContextCompat.getColor(getApplicationContext(), R.color.marronSombre)));
        addSlide(AppIntroFragment.newInstance("VOS ENNEMIS", "Obstruez votre capteur de luminosité pour vaincre sa seule force !", R.drawable.obstruer, ContextCompat.getColor(getApplicationContext(), R.color.marronClair)));
        addSlide(AppIntroFragment.newInstance("ENJOY", "C'EST TARPI", R.drawable.perso_mieux, ContextCompat.getColor(getApplicationContext(), R.color.marronClair)));
*/
    }
    @Override
    public void onSkipPressed(Fragment currentFragment)
    {
        super.onSkipPressed(currentFragment);
       // Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
       // startActivity(intent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment)
    {
        super.onDonePressed(currentFragment);
        //Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        //startActivity(intent);
    }
}
