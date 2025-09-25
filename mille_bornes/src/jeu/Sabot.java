package jeu;
import cartes.Carte;

public class Sabot {
	private Carte[] cartes;
	private int nbCartes;
	
	public Sabot(Carte[] cartes) {
		this.cartes= new Carte[cartes.length];
		for(int i = 0; i < cartes.length;i++) {
			this.cartes[i] = cartes[i];
		}
		this.nbCartes = cartes.length;
	}
	
	public int getNbCartes() {
		return nbCartes;
	}
	public Carte[] getCartes() {
		return cartes;
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	public void aujouterCarte(Carte c) {
		if(nbCartes==cartes.length) {
			throw new IllegalStateException("Le sabot est plein!");
		}
		cartes[nbCartes] = c;
		nbCartes++;
		
	}
}
