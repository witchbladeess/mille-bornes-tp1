package testsFonctionnels;

import java.util.Iterator;

import cartes.Carte;
import cartes.JeuDeCartes;
import jeu.Sabot;

public class TestSabot {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] toutes = jeu.donnerCartes();
		Sabot sabot = new Sabot(toutes);
		
		System.out.println("TEST PIOCHER\n");
		while(!sabot.estVide()) {
			Carte carte = sabot.piocher();
			System.out.println("je pioche " + carte);
		}
		
		sabot = new Sabot(jeu.donnerCartes());
		System.out.println("\nTEST ITERATEUR\n");
		Iterator<Carte> iter = sabot.iterator();
		while(iter.hasNext()) {
			Carte c = iter.next();
			System.out.println("je pioche " + c);
			iter.remove();
		}
	}	
}
