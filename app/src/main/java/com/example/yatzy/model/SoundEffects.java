package com.example.yatzy.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class SoundEffects {

    //répertorier les effets sonores utilisés  dans les différents menus / par les différentes actions contextuelles
    //toutes les mettre en statique
    //créer un entier définissant le volume de ces effets sonores  et des méthodes statique permettant de le faire varier
    //selon la seekbar du menu options
    //faire en sorte que ça marche :)


    public static float VOLUME = 1;

    public static void setVolume(float newVolume)
    {
        VOLUME = newVolume;
    }

    public static float getVolume()
    {
        return VOLUME;
    }

    public static void play(MediaPlayer mP)
    {
        mP.setVolume(VOLUME, VOLUME);
        mP.start();
    }

}
