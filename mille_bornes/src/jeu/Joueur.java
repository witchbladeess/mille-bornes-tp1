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
import cartes.Parade;
import cartes.Type;
import strategies.Strategie;

public class Joueur implements Comparable<Joueur>
{
	private String nom;
	private ZoneDeJeu zone;
	private MainJoueur main;
	private Strategie strategie;

	
	public Joueur(String nom) {
		this.nom = nom;
		this.zone = new ZoneDeJeu();
		this.main = new MainJoueur();
		this.strategie = new Strategie() { };

	}
	
	public void donner(Carte c) {
		main.prendre(c);
	}
	public void setStrategie(Strategie strategie) {
	    this.strategie = strategie;
	}

	
	public Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide()) return null;
		Carte c = sabot.piocher();
		main.prendre(c);
		return c;
	}
	
	public int donnerKmParcours() {
		return zone.donnerKmParcours();
	}
	
	public void deposer(Carte c) {
		zone.deposer(c);
	}
	
	public boolean estDepotAutorise(Carte carte) {
	    return zone.estDepotAutorise(carte);
	}
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> coups = new HashSet<>();
		for (Carte carte : main) {
			for (Joueur cible : participants) {
				Coup coup = new Coup(this, carte, cible);
				if (coup.estValide()) {
					coups.add(coup);
				}
			}
		}
		return coups;
	}
	
	public Set<Coup> coupsDefausse() {
		Set<Coup> coups = new HashSet<>();
		for (Carte carte : main) {
			Coup coup = new Coup(this, carte, null);
			coups.add(coup);
		}
		return coups;
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coups = coupsPossibles(participants);
		
		if (coups.isEmpty()) {
		    coups = coupsDefausse();
		    return strategie.selectionnerDefausse(coups);
		}
		
		if (!zone.peutAvancer()) {
			Coup coupFeuVert = trouverCoupFeuVert(coups);
			if (coupFeuVert != null) return coupFeuVert;
		}
		
		Coup coupBorne = trouverCoupType(coups, Borne.class, true);
		if (coupBorne != null) return coupBorne;
		
		Coup coupAttaque = trouverCoupAttaque(coups);
		if (coupAttaque != null) return coupAttaque;
		
		Coup coupFeuVert = trouverCoupFeuVert(coups);
		if (coupFeuVert != null) return coupFeuVert;
		
		Coup coupParade = trouverCoupParadeAutre(coups);
		if (coupParade != null) return coupParade;
		
		Coup coupFinLimite = trouverCoupType(coups, FinLimite.class, true);
		if (coupFinLimite != null) return coupFinLimite;
		
		Coup coupBotte = trouverCoupType(coups, Botte.class, true);
		if (coupBotte != null) return coupBotte;
		
		return strategie.selectionnerCoup(coups);

	}
	
	private Coup trouverCoupType(Set<Coup> coups, Class<?> classe, boolean surSoiMeme) {
		List<Coup> coupsCorrespondants = new ArrayList<>();
		
		for (Coup coup : coups) {
			if (classe.isInstance(coup.getCarteJouee())) {
				if (surSoiMeme && coup.getJoueurCible() != null && coup.getJoueurCible().equals(this)) {
					coupsCorrespondants.add(coup);
				} else if (!surSoiMeme && coup.getJoueurCible() != null && !coup.getJoueurCible().equals(this)) {
					coupsCorrespondants.add(coup);
				}
			}
		}
		
		if (!coupsCorrespondants.isEmpty() && classe == Borne.class) {
			Coup meilleurCoup = coupsCorrespondants.get(0);
			int maxKm = ((Borne) meilleurCoup.getCarteJouee()).getKm();
			
			for (Coup coup : coupsCorrespondants) {
				int km = ((Borne) coup.getCarteJouee()).getKm();
				if (km > maxKm) {
					maxKm = km;
					meilleurCoup = coup;
				}
			}
			return meilleurCoup;
		}
		
		return coupsCorrespondants.isEmpty() ? null : coupsCorrespondants.get(0);
	}
	
	private Coup trouverCoupAttaque(Set<Coup> coups) {
		for (Coup coup : coups) {
			if ((coup.getCarteJouee() instanceof Attaque || coup.getCarteJouee() instanceof DebutLimite)
				&& coup.getJoueurCible() != null 
				&& !coup.getJoueurCible().equals(this)) {
				return coup;
			}
		}
		return null;
	}
	
	private Coup trouverCoupFeuVert(Set<Coup> coups) {
		for (Coup coup : coups) {
			if (coup.getCarteJouee() instanceof Parade) {
				Parade parade = (Parade) coup.getCarteJouee();
				if (parade.getType() == Type.FEU 
					&& coup.getJoueurCible() != null 
					&& coup.getJoueurCible().equals(this)) {
					return coup;
				}
			}
		}
		return null;
	}
	
	private Coup trouverCoupParadeAutre(Set<Coup> coups) {
		for (Coup coup : coups) {
			if (coup.getCarteJouee() instanceof Parade) {
				Parade parade = (Parade) coup.getCarteJouee();
				if (parade.getType() != Type.FEU 
					&& coup.getJoueurCible() != null 
					&& coup.getJoueurCible().equals(this)) {
					return coup;
				}
			}
		}
		return null;
	}
	
	

	
	public String afficherEtatJoueur() {
		StringBuilder sb = new StringBuilder();
		sb.append(nom).append(" :\n");
		sb.append("  Bottes : ").append(zone.afficherBottes()).append("\n");
		sb.append("  Limitation de vitesse : ").append(zone.donnerLimitationVitesse() == 50).append("\n");
		sb.append("  Sommet pile bataille : ").append(zone.getSommetBataille()).append("\n");
		sb.append("  Main : ").append(main).append("\n");
		sb.append("  Km parcourus : ").append(donnerKmParcours()).append(" km");
		return sb.toString();
	}
	
	public String afficherMain() {
		return "[" + main.toString() + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Joueur joueur) {
			return nom != null && nom.equals(joueur.nom);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return nom != null ? nom.hashCode() : 0;
	}
	
	@Override
	public String toString() {
		return nom;
	}

	@Override
	public int compareTo(Joueur o) {
	    int diffKm = this.donnerKmParcours() - o.donnerKmParcours();
	    if (diffKm != 0) return diffKm;
	    return this.nom.compareTo(o.nom);
	}

	public Carte donnerSommetPile() {
		return zone.getSommetBataille();
	}

	public Set<Botte> donnerBottes() {
		return zone.donnerBottes();
	}

	public String getNom() {
		return nom;
	}
}