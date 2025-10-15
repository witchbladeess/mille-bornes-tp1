package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Probleme;

public class ZoneDeJeu {
	private List<Limite> pileLimites;
	private List<Probleme>pileBataille;
	private List<Borne>collectionBornes;
	
	public ZoneDeJeu() {
		pileLimites = new ArrayList<>();
		pileBataille = new ArrayList<>();
		collectionBornes = new ArrayList<>();
	}
	public int donnerLimitationVitesse() {
		if(pileLimites.isEmpty()) return 200;
		Limite sommet = pileLimites.get(pileLimites.size()-1);
		if(sommet instanceof FinLimite)
			return 200;
		else
			return 50;
	}
	public int donnerKmParcours() {
		int total = 0;
		for(Borne b : collectionBornes)
			total+= b.getKm();
		return total;
	}
	public void deposer(Carte c) {
		if(c instanceof Borne) {
			collectionBornes.add((Borne) c);
		} else if(c instanceof Limite) {
			pileLimites.add((Limite) c);
		}else if(c instanceof Probleme) {
			pileBataille.add((Probleme) c);
		}
	}
}
