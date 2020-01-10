package com.example.yatzy;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yatzy.model.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Joueur extends com.example.yatzy.model.Joueur {

    private List<Jeton> jetonsJoueurs;
    private Couleur couleurJetons;
    private int score;
    private int nbrLancers;
    private boolean peutJouer;
    private Combinaison combinaisonDefi;
    private Partie partie;
    private Plateau plateau;

    public Joueur(Couleur couleurJetons, Partie partie) {
        super();
        this.couleurJetons = couleurJetons;
        jetonsJoueurs = new ArrayList<>();
        this.partie = partie;
        plateau = partie.getPlateau();
        ajouterJetons();
    }


    public void ajouterJetons(){
        for (int i = 0; i < 12; i++){
            jetonsJoueurs.add(new Jeton(couleurJetons, plateau));
        }
    }

    public List<Jeton> getJetonsJoueurs() {
        return jetonsJoueurs;
    }
}
