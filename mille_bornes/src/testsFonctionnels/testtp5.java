package testsFonctionnels;

import jeu.Jeu;
import jeu.Joueur;
import strategies.StrategiePresse;

public class testtp5 {

    public static void main(String[] args) {

        Jeu jeu = new Jeu();

        Joueur j1 = new Joueur("Alice");
        Joueur j2 = new Joueur("Bob");
        Joueur j3 = new Joueur("Charlie");

        j1.setStrategie(new StrategiePresse());   


        jeu.inscrire(j1, j2, j3);

        jeu.distribuerCartes();

        String resultat = jeu.lancer();
        System.out.println(resultat);
    }
}
