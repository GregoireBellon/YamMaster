package com.example.yatzy;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntroFragment;*/

public class MenuTuto{}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        addSlide(AppIntroFragment.newInstance("BIENVENUE", "Bienvenue sur Yatzy !", R.drawable.menux,getResources().getColor(R.color.fondJeu,null)));
        addSlide(AppIntroFragment.newInstance("MENU : jouer", "Ce bouton vous permet de lancer une partie !", R.drawable.boutonjouerx,getResources().getColor(R.color.fondJeu,null )));
        addSlide(AppIntroFragment.newInstance("Jouer", "Il vous emmène ici, où vous pouvez choisir votre adversaire !", R.drawable.menujouerx, getResources().getColor(R.color.fondJeu,null)));
        addSlide(AppIntroFragment.newInstance("Jouer", "A gauche, contre un ami, à droite contre un ordinateur !", R.drawable.menujouerx, getResources().getColor(R.color.fondJeu,null)));
        addSlide(AppIntroFragment.newInstance("MENU : options", "C'est ici que vous retrouverez les réglages (musique, police, volume, ...)", R.drawable.boutonparametrex, getResources().getColor(R.color.fondJeu,null)));
        addSlide(AppIntroFragment.newInstance("MENU : personnaliser", "Voici le lieu pour modifier le jeu selon vos goûts et le rendre vôtre !", R.drawable.boutonpersox, getResources().getColor(R.color.fondJeu,null)));
        addSlide(AppIntroFragment.newInstance("Personnaliser ", "Choisissez de modifier soit le plateau soit les dés !", R.drawable.menupersox, getResources().getColor(R.color.fondJeu,null)));
        addSlide(AppIntroFragment.newInstance("MENU : tuto", "Si jamais vous voulez revisionner ce tutoriel !", R.drawable.boutontutox, getResources().getColor(R.color.fondJeu,null)));
    }
    @Override
    public void onSkipPressed(Fragment currentFragment)
    {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(), EcranAccueil.class);
        startActivity(intent);

    @Override
    public void onSkipPressed(Fragment currentFragment)
    {
        super.onSkipPressed(currentFragment);
       // Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
       // startActivity(intent);

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        Intent intent = new Intent(getApplicationContext(), EcranAccueil.class);

        //Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        //startActivity(intent);
    }
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        startActivity(intent);
    }
