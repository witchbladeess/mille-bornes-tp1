package testsFonctionnels;

import jeu.*;

public class TestJeu {
    
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        
        Joueur jack = new Joueur("Jack");
        Joueur bill = new Joueur("Bill");
        Joueur luffy = new Joueur("Luffy");
       
        jeu.inscrire(jack, bill, luffy);        
        jeu.distribuerCartes();
        
        System.out.println("ÉTAT INITIAL DES JOUEURS");
        
        System.out.println(jack.afficherEtatJoueur());
        System.out.println("\n\n" + bill.afficherEtatJoueur());
        System.out.println("\n\n" + luffy.afficherEtatJoueur());
        
        System.out.println("DÉBUT DE LA PARTIE");
        
        String resultat = jeu.lancer();
        System.out.println(resultat);
    }
}