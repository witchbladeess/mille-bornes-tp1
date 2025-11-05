package testsFonctionnels;

import java.util.Set;

import cartes.Carte;
import jeu.Coup;
import jeu.Jeu;
import jeu.Joueur;

public class TestJeu {
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        Joueur jack  = new Joueur("Jack");
        Joueur bill  = new Joueur("Bill");
        Joueur luffy = new Joueur("Luffy");

        jeu.inscrire(jack, bill, luffy);

        jeu.distribuerCartes();

        jouerUneFois(jeu, jack);
        jouerUneFois(jeu, bill);
        jouerUneFois(jeu, luffy);
    }

    private static void jouerUneFois(Jeu jeu, Joueur joueur) {
        Carte piochee = joueur.prendreCarte(jeu.getSabot());
        System.out.println("Le joueur " + joueur + " a pioche " + piochee);

        System.out.println("Il a dans sa main : " + joueur.getMain());

        Set<Joueur> participants = jeu.getJoueurs();
        Coup coup = joueur.choisirCoup(participants);

        joueur.retirerDeLaMain(coup.getCarteJouee());

        if (coup.getJoueurCible() == null) {
            jeu.getSabot().ajouterCarte(coup.getCarteJouee());
        } else {
            coup.getJoueurCible().deposer(coup.getCarteJouee());
        }

        System.out.println(coup);
    }
}
