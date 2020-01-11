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
    private List<De> listeDes;

    private De de1, de2 , de3, de4, de5;

    public Partie(EcranJeu jeu) {
        this.jeu = jeu;
        plateau = jeu.getPlateau();
        joueur1 = new com.example.yatzy.Joueur(Couleur.BLANC, this);
        joueur2 = new com.example.yatzy.Joueur(Couleur.NOIR, this);
        joueurActuel = joueur1;
        jetonsPoses = new ArrayList<>();
        listeDes = new ArrayList<>();

    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void passerTour(){
        if(joueurActuel == joueur1) {
            joueurActuel = joueur2;
            jeu.getTexteNomJoueur().setText(R.string.nomJoueur2);
        }
        else if(joueurActuel == joueur2) {
            joueurActuel = joueur1;
            jeu.getTexteNomJoueur().setText(R.string.nomJoueur1);
        }

    }

    public void lancersDes(){

            de1 = DataHolder.getHolder().getDe1();
            de2 = DataHolder.getHolder().getDe2();
            de3 = DataHolder.getHolder().getDe3();
            de4 = DataHolder.getHolder().getDe4();
            de5 = DataHolder.getHolder().getDe5();

        if (listeDes.size() == 0){
            listeDes.add(de1);
            listeDes.add(de2);
            listeDes.add(de3);
            listeDes.add(de4);
            listeDes.add(de5);
        }

        if (joueurActuel.getNbrLancers() < 3){
            for (De de: listeDes) {
                de.rouler();
            }
            joueurActuel.setNbrLancers(joueurActuel.getNbrLancers() + 1);

            DataHolder.getHolder().setDe1(de1);
            DataHolder.getHolder().setDe2(de2);
            DataHolder.getHolder().setDe3(de3);
            DataHolder.getHolder().setDe4(de4);
            DataHolder.getHolder().setDe5(de5);
            DataHolder.getHolder().setPartie(Partie.this);
        }

    }

    public List<De> getListeDes() {
        return listeDes;
    }
}
