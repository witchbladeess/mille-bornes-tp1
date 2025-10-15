package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Probleme;
import cartes.Type;

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
	public boolean peutAvancer() {
		if(pileBataille.isEmpty()) return false;
		Probleme sommet = pileBataille.get(pileBataille.size()-1);
		return(sommet instanceof Parade) && sommet.getType() == Type.FEU;
	}
	private boolean estDepotFeuVertAutorise() {
		if(pileBataille.isEmpty()) return true;
		Probleme sommet = pileBataille.get(pileBataille.size()-1);
		if (sommet instanceof Attaque && sommet.getType() == Type.FEU)
	        return true;
	    if (sommet instanceof Parade && sommet.getType() != Type.FEU)
	        return true;
	    return false;
 	}
	private boolean estDepotBorneAutorise(Borne borne) {
	    if (!peutAvancer()) return false;
	    if (borne.getKm() > donnerLimitationVitesse()) return false;
	    int total = donnerKmParcours() + borne.getKm();
	    return total <= 1000;
	}
	private boolean estDepotLimiteAutorise(Limite limite) {
	    if (limite instanceof DebutLimite) {
	        // Cas a : début de limite -> autorisé si pile vide ou sommet = FinLimite
	        return pileLimites.isEmpty() ||(pileLimites.get(pileLimites.size() - 1) instanceof FinLimite);
	    } else if (limite instanceof FinLimite) {
	        // Cas b : fin de limite -> autorisé seulement si sommet = DébutLimite
	        return !pileLimites.isEmpty() &&(pileLimites.get(pileLimites.size() - 1) instanceof DebutLimite);
	    }
	    return false;
	}
	private boolean estBloque() {
	    if (pileBataille.isEmpty()) return false;
	    Probleme sommet = pileBataille.get(pileBataille.size() - 1);
	    return sommet instanceof Attaque;
	}

	private boolean estDepotBatailleAutorise(Probleme bataille) { 
	    if (bataille instanceof Attaque) {
	        return !estBloque();
	    }
	    
	    Parade p = (Parade) bataille;
	    if (p.getType() == Type.FEU) {
	        return estDepotFeuVertAutorise();
	    }
	    if (pileBataille.isEmpty()) return false;
	    Probleme sommet = pileBataille.get(pileBataille.size() - 1);
	    return (sommet instanceof Attaque) && (sommet.getType() == p.getType());
	}

	public boolean estDepotAutorise(Carte carte) {
	    if (carte instanceof Borne)   return estDepotBorneAutorise((Borne) carte);
	    if (carte instanceof Limite)  return estDepotLimiteAutorise((Limite) carte);
	    if (carte instanceof Probleme) return estDepotBatailleAutorise((Probleme) carte); 
	    return false;
	}
		
}
