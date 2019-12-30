package com.example.yatzy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.yatzy.model.SoundEffects;
import com.example.yatzy.model.SoundMusic;

import static android.content.Context.MODE_PRIVATE;

public class Options {

    public Activity context;

    public static String SHARED_PREFS = "sharedPrefs";
    public static String SEEKBAR_EFFECTS = "seekbarEffects";
    public static String SEEKBAR_MUSIC = "seekbarMusic";
    public static String SWITCH_DYSLEX = "switchDyslex";
    public static String SWITCH_DALTON = "switchDalton";

    public static int PROGRESS_SEEKBAR_EFFECTS, PROGRESS_SEEKBAR_MUSIC;
    public static boolean SWITCH_DALTON_STATE, SWITCH_DYSLEX_STATE;

    public static Typeface CURRENT_FONT;

    public static AudioManager audioManagerMusic;

    public Options (Activity context)
    {
        this.context = context;
        audioManagerMusic = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
        load();

        audioManagerMusic.setStreamVolume(AudioManager.STREAM_MUSIC, PROGRESS_SEEKBAR_MUSIC, 1);
    }

    /**
     * Sauvegarde les paramètres dans les sharedPreferences
     * @param volumeEffects
     * Volume des effets à sauvegarder
     * @param volumeMusic
     * Volume de la musique à sauvegarder
     * @param switchDalton
     * Etat du switch daltonien à sauvegarder
     * @param switchDyslex
     * Etat du switch dyslexique à sauvegarder
     */
    public void save(int volumeEffects, int volumeMusic, boolean switchDalton, boolean switchDyslex)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(SEEKBAR_EFFECTS, volumeEffects);
        editor.putInt(SEEKBAR_MUSIC, volumeMusic);

        editor.putBoolean(SWITCH_DALTON, switchDalton);
        editor.putBoolean(SWITCH_DYSLEX, switchDyslex);

        editor.apply();

    }

    /**
     * Permet de charger les valeurs des sharedPreferences, et d'instancier / attribuer
     * les valeurs aux champs statiques de la classe
     */
    public void load()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        PROGRESS_SEEKBAR_EFFECTS = sharedPreferences.getInt(SEEKBAR_EFFECTS,  (maxMusicVolume()/2));
        PROGRESS_SEEKBAR_MUSIC = sharedPreferences.getInt(SEEKBAR_MUSIC, (maxMusicVolume()/2));

        SWITCH_DALTON_STATE = sharedPreferences.getBoolean(SWITCH_DALTON, false);
        SWITCH_DYSLEX_STATE = sharedPreferences.getBoolean(SWITCH_DYSLEX, false);

        if(SWITCH_DYSLEX_STATE)
            setDyslexFont();
        else
            setRegularFont();


        SoundEffects.setVolume((float) PROGRESS_SEEKBAR_EFFECTS /100f);
    }

    /**
     * Permet de modifier le volume de la musique
     * @param newVolume
     * Nouveau volume de la musique
     */
    public void setVolume(int newVolume)
    {
        audioManagerMusic.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 1);
    }

    /**
     * Permet de renvoyer le max du stream 'music' pour définir le max de la SeekBar de musique
     * @return
     * Valeur max du stream 'music"
     */
    public int maxMusicVolume()
    {
        return audioManagerMusic.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * Fonction permettant de passer à la police adaptée aux dyslexiques
     */
    public void setDyslexFont()
    {
        CURRENT_FONT = Typeface.createFromAsset(this.context.getAssets(),"res/font/open_dyslexic_regular.otf");
    }

    /**
     * Fonction permettant de passer à la police standard
     */
    public void setRegularFont()
    {
        CURRENT_FONT = Typeface.createFromAsset(this.context.getAssets(),"res/font/landasans_demo.ttf");
    }

}