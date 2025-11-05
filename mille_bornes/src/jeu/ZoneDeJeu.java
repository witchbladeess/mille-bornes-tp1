package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Cartes;
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
	//tp4
	private Set<Botte> bottes;
	
	public ZoneDeJeu() {
		pileLimites = new ArrayList<>();
		pileBataille = new ArrayList<>();
		collectionBornes = new ArrayList<>();
		//tp4
		bottes = new HashSet<>();
	}
	public int donnerLimitationVitesse() {
		//tp 4 botte "vehicule" pas de limite
		if(estPrioritaire()) return 200;
		
		if(pileLimites.isEmpty()) return 200;
		Limite sommet = pileLimites.get(pileLimites.size()-1);
		if(sommet instanceof FinLimite)
			return 200;
		else
			return 50;
	}
	//tp4
	public boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}
	public int donnerKmParcours() {
		int total = 0;
		for(Borne b : collectionBornes)
			total+= b.getKm();
		return total;
	}
	public void deposer(Carte c) {
		if(c instanceof Botte) {
			bottes.add((Botte)c);
		} else if(c instanceof Limite) {
			pileLimites.add((Limite) c);
		}else if(c instanceof Probleme) {
			pileBataille.add((Probleme) c);
			//tp4
		}else if(c instanceof Borne) {
			collectionBornes.add((Borne)c);
		}
	}
	public boolean peutAvancer() {
	    if (pileBataille.isEmpty()) return estPrioritaire();
	    Probleme sommet = pileBataille.get(pileBataille.size() - 1);
	    if (sommet instanceof Parade && sommet.getType() == Type.FEU) return true;
	    if (sommet instanceof Parade) return estPrioritaire();
	    if (sommet instanceof Attaque && sommet.getType() == Type.FEU) return estPrioritaire();
	    if (sommet instanceof Attaque) return estPrioritaire() && aLaBotte(sommet.getType());
	    return false;
	}
	private boolean aLaBotte(Type t) {
	    for (Botte b : bottes) if (b.getType() == t) return true;
	    return false;
	}

	public Set<Botte> getBottes() { return bottes; }
	public List<Probleme> getPileBataille() { return pileBataille; }

	private boolean estDepotFeuVertAutorise() {
		if(estPrioritaire()) return false;
		if(pileBataille.isEmpty()) return true;
		Probleme sommet = pileBataille.get(pileBataille.size()-1);
		if (sommet instanceof Attaque && sommet.getType() == Type.FEU)
	        return true;
	    if (sommet instanceof Parade && sommet.getType() != Type.FEU)
	        return true;
	    if(sommet instanceof Attaque) return aLaBotte(sommet.getType());
	    return false;
 	}
	private boolean estDepotBorneAutorise(Borne borne) {
	    if (!peutAvancer()) return false;
	    if (borne.getKm() > donnerLimitationVitesse()) return false;
	    int total = donnerKmParcours() + borne.getKm();
	    return total <= 1000;
	}
	private boolean estDepotLimiteAutorise(Limite limite) {
		if(estPrioritaire()) return false;
	    if (limite instanceof DebutLimite) {
	        // Cas a : début de limite -> autorisé si pile vide ou sommet = FinLimite
	        return pileLimites.isEmpty() ||(pileLimites.get(pileLimites.size() - 1) instanceof FinLimite);
	    } else if (limite instanceof FinLimite) {
	        // Cas b : fin de limite -> autorisé seulement si sommet = DébutLimite
	        return !pileLimites.isEmpty() &&(pileLimites.get(pileLimites.size() - 1) instanceof DebutLimite);
	    }
	    return false;
	}
		private boolean estSommetFeuVert() {
	    if (pileBataille.isEmpty()) return false;
	    Probleme s = pileBataille.get(pileBataille.size() - 1);
	    return (s instanceof Parade) && s.getType() == Type.FEU;
	}


	private boolean estDepotBatailleAutorise(Probleme bataille) { 
		if(aLaBotte(bataille.getType())) return false;
	    if (bataille instanceof Attaque) {
	        return estSommetFeuVert() || estPrioritaire() ;
	    }
	    if(bataille instanceof Parade) {
	    Parade p = (Parade) bataille;
	    if (p.getType() == Type.FEU) {
	        return estDepotFeuVertAutorise();
	    }
	    if (pileBataille.isEmpty()) return false;
	    Probleme sommet = pileBataille.get(pileBataille.size() - 1);
	    return (sommet instanceof Attaque) && (sommet.getType() == p.getType());
	    }
	    return false;
	}

	public boolean estDepotAutorise(Carte carte) {
		if(carte instanceof Botte) return true;
	    if (carte instanceof Borne)   return estDepotBorneAutorise((Borne) carte);
	    if (carte instanceof Limite)  return estDepotLimiteAutorise((Limite) carte);
	    if (carte instanceof Probleme) return estDepotBatailleAutorise((Probleme) carte); 
	    return false;
	}
		
}
