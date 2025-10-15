package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zone;
	private MainJoueur main;
	
	public Joueur(String nom) {
		this.nom=nom;
		this.zone = new ZoneDeJeu();
		this.main = new MainJoueur();
	}
	public void donner(Carte c) {
		main.prendre(c);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide()) return null;
		Carte c = sabot.piocher();
		main.prendre(c);
		return c;
	}
	public int DonnerKmParcours() {
		return zone.donnerKmParcours();
	}
	public void deposer(Carte c) {
		zone.deposer(c);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Joueur joueuer) {
			return nom!= null && nom.equals(joueuer.nom);
		}
		return false;
	}
	@Override
	public String toString() {
		return nom;
	}
}
