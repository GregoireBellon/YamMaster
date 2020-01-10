package com.example.yatzy;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yatzy.model.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Partie extends com.example.yatzy.model.Partie{

    private Joueur joueurActuel;
    private Joueur joueur1;
    private Joueur joueur2;
    private List<Jeton> jetonsPoses;
    private Plateau plateau;
    private EcranJeu jeu;

    public Partie(EcranJeu jeu) {
        this.jeu = jeu;
        plateau = jeu.getPlateau();
        joueur1 = new com.example.yatzy.Joueur(Couleur.BLANC, this);
        joueur2 = new com.example.yatzy.Joueur(Couleur.NOIR, this);
        joueurActuel = joueur1;
        jetonsPoses = new ArrayList<>();
    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void placerJeton(Case caseCible){
        Jeton jeton = joueurActuel.getJetonsJoueurs().get(0);
        ImageView view = jeton.getView();

        int x = caseCible.getCoordX() + (plateau.getDimensionCases() / 8);
        int y = caseCible.getCoordY() + (plateau.getDimensionCases() / 8);

        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(plateau.getDimensionJetons(),plateau.getDimensionJetons());
        plateau.getJeu().addContentView(view, param);
        plateau.getJetonsPoses().add(jeton);
        joueurActuel.getJetonsJoueurs().remove(jeton);
        plateau.getJeu().poserJeton(jeton, x ,y);
        if (joueurActuel == joueur1){
            joueurActuel = joueur2;
        }else{
            joueurActuel = joueur1;
        }
    }
}
