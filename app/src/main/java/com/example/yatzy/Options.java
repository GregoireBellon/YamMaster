package com.example.yatzy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.yatzy.model.SoundEffects;

import static android.content.Context.MODE_PRIVATE;

public class Options {

    public Activity context;

    public static String SHARED_PREFS = "sharedPrefs";
    public static String SEEKBAR_EFFECTS = "seekbarEffects";
    public static String SEEKBAR_MUSIC = "seekbarMusic";
    public static String SWITCH_DYSLEX = "switchDyslex";
    public static String SWITCH_DALTON = "switchDalton";
    public static String FONT_SELECTED = "";


    public static int PROGRESS_SEEKBAR_EFFECTS, PROGRESS_SEEKBAR_MUSIC;
    public static boolean SWITCH_DALTON_STATE, SWITCH_DYSLEX_STATE;

    public static Typeface CURRENT_FONT;

    public static AudioManager audioManagerMusic;

    public Options(Activity context) {
        this.context = context;
        audioManagerMusic = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);

        load();

        // audioManagerMusic.setStreamVolume(AudioManager.STREAM_MUSIC, PROGRESS_SEEKBAR_MUSIC, 1);
    }

    /**
     * Sauvegarde les paramètres dans les sharedPreferences
     *
     * @param volumeEffects Volume des effets à sauvegarder
     * @param volumeMusic   Volume de la musique à sauvegarder
     * @param switchDalton  Etat du switch daltonien à sauvegarder
     * @param switchDyslex  Etat du switch dyslexique à sauvegarder
     */
    public void save(int volumeEffects, int volumeMusic, boolean switchDalton, boolean switchDyslex) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(SEEKBAR_EFFECTS, volumeEffects);
        editor.putInt(SEEKBAR_MUSIC, volumeMusic);

        editor.putBoolean(SWITCH_DALTON, switchDalton);
        editor.putBoolean(SWITCH_DYSLEX, switchDyslex);

        editor.apply();

    }

    private void saveSwitchState(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH_DYSLEX, SWITCH_DYSLEX_STATE);

    }

    /**
     * Permet de charger les valeurs des sharedPreferences, et d'instancier / attribuer
     * les valeurs aux champs statiques de la classe
     */
    public void load() {
        Log.e("font", "Font selected :"+FONT_SELECTED);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        PROGRESS_SEEKBAR_EFFECTS = sharedPreferences.getInt(SEEKBAR_EFFECTS, (maxMusicVolume() / 2));
        PROGRESS_SEEKBAR_MUSIC = sharedPreferences.getInt(SEEKBAR_MUSIC, (maxMusicVolume() / 2));

        SWITCH_DALTON_STATE = sharedPreferences.getBoolean(SWITCH_DALTON, false);
        SWITCH_DYSLEX_STATE = sharedPreferences.getBoolean(SWITCH_DYSLEX, false);


        if (SWITCH_DYSLEX_STATE) {
            setDyslexFont();
            updatefont(context.findViewById(android.R.id.content).getRootView());
            FONT_SELECTED="dys";

        }
        else {
            setRegularFont();
            updatefont(context.findViewById(android.R.id.content).getRootView());
            FONT_SELECTED="reg";


        }

        SoundEffects.setVolume((float) PROGRESS_SEEKBAR_EFFECTS / 100f);
    }

    /**
     * Permet de modifier le volume de la musique
     *
     * @param newVolume Nouveau volume de la musique
     */
    public void setVolume(int newVolume) {
        audioManagerMusic.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 1);
    }

    /**
     * Permet de renvoyer le max du stream 'music' pour définir le max de la SeekBar de musique
     *
     * @return Valeur max du stream 'music"
     */
    public int maxMusicVolume() {
        return audioManagerMusic.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }


    /*
                   try {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                overrideFonts(context, child);
         }
        } else if (v instanceof TextView ) {
            ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "font.ttf"));
        }
    } catch (Exception e) {
 }
         }
     */

    public void updatefont(View v) {
        try {
            Log.e("font",v.toString());

            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;

                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    Log.e("font",child.toString());

                    updatefont(child);
                }
            } else if ( v instanceof TextView ) {
                Log.e("font", "UPDATE");
                View pass = v;
                ((TextView) pass).setTypeface(CURRENT_FONT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  ViewGroup group = (ViewGroup) root;
        int i;
        for (i = 0; i < group.getChildCount(); i++) {
            View child = group.getChildAt(i);
            Log.e("font", child.toString());

            if (child instanceof TextView) {
                ((TextView) child).setTypeface(CURRENT_FONT);
                Log.e("font", "Update "+child.toString()+"to "+CURRENT_FONT);

            }
            if(child instanceof Button){
                ((Button) child).setTypeface(CURRENT_FONT);
                Log.e("font", "Update "+child.toString()+"to "+CURRENT_FONT);

          }

        }*/

    }

    /**
     * Fonction permettant de passer à la police adaptée aux dyslexiques
     */
    public void setDyslexFont() {
       // CURRENT_FONT = Typeface.createFromAsset(this.context.getAssets(), "res/fonts/open_dyslexic_regular.otf");
    CURRENT_FONT = ResourcesCompat.getFont(context, R.font.open_dyslexic_regular);
    }

    /**
     * Fonction permettant de passer à la police standard
     */
    public void setRegularFont() {
        //CURRENT_FONT = Typeface.createFromAsset(this.context.getAssets(), "res/fonts/landasans_demo.ttf");
        CURRENT_FONT = ResourcesCompat.getFont(context, R.font.landasans_demo);

    }

    public void touchSwitchDislexState(){
        SWITCH_DYSLEX_STATE = !SWITCH_DYSLEX_STATE;
        saveSwitchState();
        load();


    }

}