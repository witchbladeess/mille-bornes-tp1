package jeu;

import java.util.Objects;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {
	private Joueur joueurCourant;
	private Carte carteJouee;
	private Joueur joueurCible;

	public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		this.carteJouee = carteJouee;
		this.joueurCible = joueurCible;
	}
	public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public Carte getCarteJouee() {
        return carteJouee;
    }

    public Joueur getJoueurCible() {
        return joueurCible;
    }
   
    public boolean estValide() {
    	if((carteJouee instanceof Attaque || carteJouee instanceof Limite)) {
    		return joueurCible != null && ! joueurCible.equals(joueurCourant);
    	}
    	else {
    		return joueurCible == null || joueurCible.equals(joueurCourant);
    	}
    }
    @Override
    public String toString() {
        if (joueurCible == null) {
            return joueurCourant + " defausse la carte " + carteJouee;
        } else {
            return joueurCourant + " depose la carte " + carteJouee
                   + " dans la zone de jeu de " + joueurCible;
        }
    }
    @Override 
    public boolean equals(Object o) {
    	if (this == o) return true;
        if (!(o instanceof Coup)) return false;
        Coup c = (Coup) o;
        return Objects.equals(joueurCourant, c.joueurCourant)
            && this.carteJouee == c.carteJouee
            && Objects.equals(joueurCible, c.joueurCible);
    }
    @Override
    public int hashCode() {
    	return joueurCible.hashCode(); 
    }

}
