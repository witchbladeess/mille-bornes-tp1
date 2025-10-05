package testsFonctionnels;

import java.util.Iterator;

//import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
//import cartes.Type;
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
		//version b: appeler piocher dans boucle-> exception
		sabot = new Sabot(jeu.donnerCartes());
		System.out.println("\nTEST ITERATEUR\n");
		//version piocher avant la boucle
		sabot.piocher();
		Iterator<Carte> iter = sabot.iterator();
		while(iter.hasNext()) {
			Carte c = iter.next();
			System.out.println("je pioche " + c);
			//sabot.piocher();
			//sabot.ajouterCarte(new Botte(Type.ACCIDENT));
			iter.remove();
		}
	}	
}
