package com.example.yatzy;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
public class Ecrantuto extends AppIntro {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        addSlide(AppIntroFragment.newInstance("BIENVENUE", "Bienvenue sur Yatzy !", R.mipmap.icone_enervee, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Tutoriel", "Voici un petit tutoriel pour apprendre à jouer à Yatzy!", R.mipmap.icone_enervee, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Tutoriel", "Je vais vous présenter un tour type de Yatzy! Les tours s'enchainent de la même façon joueur par joueur", R.mipmap.icone_enervee, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Tutoriel", "La partie s'arrête dès qu'un joueur a utilisé ses 12 jetons ou qu'il a fait une ligne de 5 sur le plateau avec ses jetons", R.mipmap.icone_enervee, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Lancer les dés", "En cliquant sur le gobelet orange en haut à droite vous pouvez lancer les dés une première fois", R.drawable.gobelet_orange, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Etape 1", "Il vous emmène ici, où vous devez lancer les dés une première fois", R.drawable.choixdes_zoom, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Etape 2 ", "En cliquant sur lancer les dés vous arrivez ici, après que vos dés aient été lancés", R.drawable.choixdes_zoom, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Etape 2 possibilités", "Ici, vous pouvez soit relancer vos dés, soit choisir un défi, soit choisir une combinaison", R.drawable.choixdes_zoom, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Défi", "Ici, vous pouvez choisir une combinaison que vous pensez pouvoir faire au tours 2 ou 3, si vous y parvenez, vous pourrez poser un jeton sur la case défi !", R.drawable.choix_combi2, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Combinaison ", "En cliquant sur le bouton Combinaisons, vous arrivez sur cette fenêtre", R.drawable.choix_combi2, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Combinaison ", "Les combinaisons assombries sont celles sur lesquelles vous pouvez poser un jeton", R.drawable.choix_combi2, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Poser jeton ", "En cliquant sur une combinaison assombrie, vous arrivez sur le plateau, les cases claires sont celles où vous pouvez poser un jeton", R.drawable.choix_plateau1, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Etape 3 ", "C'est votre dernier tour, si vous n'avez pas de combinaison, vous ne pouvez pas poser de pion ce tour ci", R.drawable.choix_plateau2, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Bouton Options ", "En cliquant sur l'icône orange en  haut à gauche, vous pouvez accéder aux options", R.drawable.bouton_menu_jaune, getResources().getColor(R.color.fondJeu, null)));
        addSlide(AppIntroFragment.newInstance("Options ", "Ici, vous avez accès aux options du jeu, vous pouvez y changer le volume de la musique, ou activer différents modes", R.drawable.options1, getResources().getColor(R.color.fondJeu, null)));

        //addSlide(AppIntroFragment.newInstance("MENU : tuto", "Si jamais vous voulez revisionner ce tutoriel !", R.drawable.boutontutox, getResources().getColor(R.color.fondJeu, null)));
    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        }
        @Override
        public void onDonePressed (Fragment currentFragment){
            super.onDonePressed(currentFragment);
            Intent intent = new Intent(getApplicationContext(), EcranAccueil.class);
            startActivity(intent);
        }
    }
