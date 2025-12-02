package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
    private static final int NBCARTES = 6;
    
    private Sabot sabot;
    private List<Joueur> joueurs;
    private Iterator<Joueur> iterateurJoueurs;
    
    public Jeu() {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] cartes = jeuDeCartes.donnerCartes();
        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, cartes);
        List<Carte> listeCartesMelangees = GestionCartes.melanger(listeCartes);       
        Carte[] cartesMelangees = listeCartesMelangees.toArray(new Carte[0]);
        this.sabot = new Sabot(cartesMelangees);
        this.joueurs = new ArrayList<>();
    }
    
    public Sabot getSabot() {
        return sabot;
    }
    
    public void inscrire(Joueur... nouveauxJoueurs) {
        for (Joueur joueur : nouveauxJoueurs) {
            joueurs.add(joueur);
        }
    }
    
    public void distribuerCartes() {
        for (int i = 0; i < NBCARTES; i++) {
            for (Joueur joueur : joueurs) {
                if (!sabot.estVide()) {
                    joueur.prendreCarte(sabot);
                }
            }
        }
    }
    
    public String jouerTour(Joueur joueur) {
        StringBuilder resultat = new StringBuilder();
        Carte cartePiochee = null;
        if (!sabot.estVide()) {
            cartePiochee = joueur.prendreCarte(sabot);
            resultat.append("\nLe joueur ").append(joueur).append(" a pioché ").append(cartePiochee).append("\n");
        }
        resultat.append("Il a dans sa main : ").append(joueur.afficherMain()).append("\n");
        Set<Joueur> participants = new HashSet<>(joueurs);
        Coup coup = joueur.choisirCoup(participants);
        if (coup == null) {
            resultat.append(joueur).append(" ne peut pas jouer (main vide).\n");
            return resultat.toString();
        }
        joueur.retirerDeLaMain(coup.getCarteJouee());
        if (coup.getJoueurCible() != null) {
            coup.getJoueurCible().deposer(coup.getCarteJouee());
            resultat.append(coup.getJoueurCourant()).append(" dépose la carte ")
                    .append(coup.getCarteJouee()).append(" dans la zone de jeu de ")
                    .append(coup.getJoueurCible()).append("\n");
        } else {
            sabot.ajouterCarte(coup.getCarteJouee());
            resultat.append(coup.getJoueurCourant()).append(" défausse la carte ")
                    .append(coup.getCarteJouee()).append("\n");
        }
        return resultat.toString();
    }
    
    public Joueur donnerJoueurSuivant() {
        if (joueurs.isEmpty()) {
            return null;
        }
        if (iterateurJoueurs == null || !iterateurJoueurs.hasNext()) {
            iterateurJoueurs = joueurs.iterator();
        }
        return iterateurJoueurs.next();
    }
    public Set<Joueur> classement() {
        return new TreeSet<>(new Comparator<Joueur>() {
            @Override
            public int compare(Joueur j1, Joueur j2) {
                int diff = j2.donnerKmParcours() - j1.donnerKmParcours();
                if (diff != 0) {
                    return diff; // tri décroissant
                }
                return j1.toString().compareTo(j2.toString());
            }
        }) {{
            addAll(joueurs);
        }};
    }

    
    public String lancer() {
        StringBuilder resultat = new StringBuilder();
        boolean jeuTermine = false;

        while (!jeuTermine && !sabot.estVide()) {
            Joueur joueurCourant = donnerJoueurSuivant();
            if (joueurCourant == null) {
                break;
            }

            resultat.append(jouerTour(joueurCourant));

            // Vérifier si un joueur a atteint 1000 km
            for (Joueur j : joueurs) {
                if (j.donnerKmParcours() >= 1000) {
                    jeuTermine = true;
                    resultat.append(j)
                            .append(" a atteint 1000 km et remporte la partie !\n\n");
                    resultat.append(afficherClassementFinal());
                    return resultat.toString();
                }
            }
        }

        if (sabot.estVide()) {
            resultat.append("\nLe sabot est vide, la partie est terminée.\n\n");
            resultat.append(afficherClassementFinal());
        }

        return resultat.toString();
    }
    private String afficherClassementFinal() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== CLASSEMENT FINAL =====\n");

        Set<Joueur> classement = classement();
        int rang = 1;

        for (Joueur j : classement) {
            sb.append(rang++)
              .append(". ")
              .append(j)
              .append(" : ")
              .append(j.donnerKmParcours())
              .append(" km\n");
        }

        Joueur gagnant = classement.iterator().next();
        sb.append("\nVainqueur : ")
          .append(gagnant)
          .append(" avec ")
          .append(gagnant.donnerKmParcours())
          .append(" km !\n");

        return sb.toString();
    }

    
    private String afficherScoresFinaux() {
        StringBuilder resultat = new StringBuilder();
        resultat.append("      SCORES FINAUX\n");
        for (Joueur j : joueurs) {
            resultat.append(j).append(" : ").append(j.donnerKmParcours()).append(" km\n");
        }
        Joueur gagnant = joueurs.get(0);
        for (Joueur j : joueurs) {
            if (j.donnerKmParcours() > gagnant.donnerKmParcours()) {
                gagnant = j;
            }
        }
        resultat.append("\nLe gagnant est ").append(gagnant)
                .append(" avec ").append(gagnant.donnerKmParcours()).append(" km !\n");
        
        return resultat.toString();
    }
}