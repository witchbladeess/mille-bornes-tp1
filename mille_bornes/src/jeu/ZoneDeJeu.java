package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Probleme;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimites;
	private List<Probleme> pileBataille;
	private List<Borne> collectionBornes;
	private Set<Botte> bottes;
	
	public ZoneDeJeu() {
		pileLimites = new ArrayList<>();
		pileBataille = new ArrayList<>();
		collectionBornes = new ArrayList<>();
		bottes = new HashSet<>();
	}
	
	public boolean estPrioritaire() {
		Botte bottePrioritaire = new Botte(Type.FEU);
		return bottes.contains(bottePrioritaire);
	}
	
	public int donnerLimitationVitesse() {
		if(estPrioritaire()) return 200;
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
		if(c instanceof Botte) {
			bottes.add((Botte) c);
		} else if(c instanceof Borne) {
			collectionBornes.add((Borne) c);
		} else if(c instanceof Limite) {
			pileLimites.add((Limite) c);
		} else if(c instanceof Probleme) {
			pileBataille.add((Probleme) c);
		}
	}
	
	public boolean peutAvancer() {
		if(pileBataille.isEmpty() && estPrioritaire()) {
			return true;
		}
		
		if(pileBataille.isEmpty()) {
			return false;
		}
		
		Probleme sommet = pileBataille.get(pileBataille.size()-1);
		
		if(sommet instanceof Parade && sommet.getType() == Type.FEU) {
			return true;
		}
		
		if(!estPrioritaire()) {
			return false;
		}
		
		if(sommet instanceof Parade) {
			return true;
		}
		
		if(sommet instanceof Attaque && sommet.getType() == Type.FEU) {
			return true;
		}
		
		if(sommet instanceof Attaque) {
			Botte botteCorrespondante = new Botte(sommet.getType());
			return bottes.contains(botteCorrespondante);
		}
		
		return false;
	}
	
	private boolean estDepotFeuVertAutorise() {
		if(estPrioritaire()) return false;		
		if(pileBataille.isEmpty()) return true;
		Probleme sommet = pileBataille.get(pileBataille.size()-1);
		if (sommet instanceof Attaque && sommet.getType() == Type.FEU) {
	        return true;
		}

	    if (sommet instanceof Parade && sommet.getType() != Type.FEU) {
	        return true;
		}

		if (sommet instanceof Attaque) {
			Botte botteCorrespondante = new Botte(sommet.getType());
			if(bottes.contains(botteCorrespondante)) {
				return true;
			}
		}
		
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
	        return pileLimites.isEmpty() || (pileLimites.get(pileLimites.size() - 1) instanceof FinLimite);
	    } else if (limite instanceof FinLimite) {
	        return !pileLimites.isEmpty() && (pileLimites.get(pileLimites.size() - 1) instanceof DebutLimite);
	    }
	    return false;
	}
	
	private boolean estSommetFeuVert() {
	    if (pileBataille.isEmpty()) return false;
	    Probleme s = pileBataille.get(pileBataille.size() - 1);
	    return (s instanceof Parade) && s.getType() == Type.FEU;
	}

	private boolean estDepotBatailleAutorise(Probleme bataille) { 
	    if (bataille instanceof Botte) {
	        return true;
	    }
	    
	    Botte botteAssociee = new Botte(bataille.getType());
	    if(bottes.contains(botteAssociee)) {
	        return false;
	    }
	    
	    if (bataille instanceof Attaque) {
	        return estSommetFeuVert() || estPrioritaire();
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
	    if (carte instanceof Botte)    return true;
	    if (carte instanceof Borne)    return estDepotBorneAutorise((Borne) carte);
	    if (carte instanceof Limite)   return estDepotLimiteAutorise((Limite) carte);
	    if (carte instanceof Probleme) return estDepotBatailleAutorise((Probleme) carte); 
	    return false;
	}
	
	public String afficherBottes() {
		if (bottes.isEmpty()) {
			return "Aucune botte";
		}
		StringBuilder sb = new StringBuilder();
		boolean premier = true;
		for (Botte botte : bottes) {
			if (!premier) {
				sb.append(", ");
			}
			sb.append(botte);
			premier = false;
		}
		return sb.toString();
	}
	
	public Carte getSommetBataille() {
	    if (pileBataille.isEmpty()) {
	        return null; 
	    }
	    return pileBataille.get(pileBataille.size() - 1);
	}

	public Set<Botte> donnerBottes() {
		return bottes;
	}
		
}