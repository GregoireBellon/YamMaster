package com.example.yatzy.model;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import com.example.yatzy.EcranAccueil;
import com.example.yatzy.R;

public class SoundMusic {

    //répertorier les effets sonores utilisés  dans les différents menus / par les différentes actions contextuelles
    //toutes les mettre en statique
    //créer un entier définissant le volume de ces effets sonores  et des méthodes statique permettant de le faire varier
    //selon la seekbar du menu options
    //faire en sorte que ça marche :)


    public static int VOLUME = 1;

    public static void setVolume(int newVolume)
    {
        VOLUME = newVolume;
    }

    public static int getVolume()
    {
        return VOLUME;
    }

    public static void play(MediaPlayer mP)
    {
        mP.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mP.start();
    }

}
