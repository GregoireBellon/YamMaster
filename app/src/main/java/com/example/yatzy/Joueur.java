package com.example.yatzy;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.yatzy.model.Couleur;
import java.util.ArrayList;
import java.util.List;

public class Joueur extends com.example.yatzy.model.Joueur {

    private List<Jeton> jetonsJoueurs;
    private Couleur couleurJetons;
    private int nbrLancers;
    private boolean peutJouer;
    private boolean peutPoser;
    private Combinaison combinaisonDefi;
    private Partie partie;
    private Plateau plateau;
    private int score;

    public Joueur(Couleur couleurJetons, Partie partie) {
        super();
        peutPoser = true;
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

    public void placerJeton(Case caseCible){
        if (getJetonsJoueurs().size() > 0){
            Case[][] cases = plateau.getDispositionCases();

            Jeton jeton = getJetonsJoueurs().get(0);
            ImageView view = jeton.getView();

            int x = caseCible.getCoordX() + (plateau.getDimensionCases() / 8);
            int y = caseCible.getCoordY() + (plateau.getDimensionCases() / 8);

            cases[caseCible.getIndexX()][caseCible.getIndexY()].setJetonPose(jeton);

            ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(plateau.getDimensionJetons(),plateau.getDimensionJetons());
            plateau.getJeu().addContentView(view, param);
            plateau.getJetonsPoses().add(jeton);
            getJetonsJoueurs().remove(jeton);
            plateau.getJeu().poserJeton(jeton, x ,y);
            plateau.setDispositionCases(cases);
            partie.passerTour();
            caseCible.setJetonPose(jeton);
        }
    }

 /*   public int compterScore() {
        if(plateau.checkTroisJetonsAlignes())

        return 0;
    }*/

    public List<Jeton> getJetonsJoueurs() {
        return jetonsJoueurs;
    }

    public boolean isPeutJouer() {
        return peutJouer;
    }

    @Override
    public void setPeutJouer(boolean peutJouer) {
        this.peutJouer = peutJouer;
    }

    public boolean isPeutPoser() {
        return peutPoser;
    }

    public void setPeutPoser(boolean peutPoser) {
        this.peutPoser = peutPoser;
    }

    public int getNbrLancers() {
        return nbrLancers;
    }

    public void setNbrLancers(int nbrLancers) {
        this.nbrLancers = nbrLancers;
    }

    public Combinaison getCombinaisonDefi() {
        return combinaisonDefi;
    }

    public void setCombinaisonDefi(Combinaison combinaisonDefi) {
        this.combinaisonDefi = combinaisonDefi;
    }
}
