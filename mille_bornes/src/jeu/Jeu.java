package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> joueurs;
	
	public Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		Carte[] cartes = jeuDeCartes.donnerCartes();
		
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		Collections.shuffle(listeCartes);
		Carte[] cartesMelangees = listeCartes.toArray(new Carte[0]);
		sabot = new Sabot(cartesMelangees);
		joueurs = new LinkedHashSet<>();
	}
	public Sabot getSabot() {
		return sabot;
	}
	public Set<Joueur> getJoueurs() {
        return joueurs;
    }

    public void inscrire(Joueur... nouveauxJoueurs) {
        Collections.addAll(joueurs, nouveauxJoueurs);
    }
    public void distribuerCartes() {
    	final int NBCARTES = 6;
    	for(int i = 0; i < NBCARTES; i++) {
    		for(Joueur joueur : joueurs) {
    			Carte carte = sabot.piocher();
    			if(carte!= null) {
    				joueur.donner(carte);
    			}
    		}
    	}
    }
    public String jouerTour(Joueur joueur) {
        StringBuilder log = new StringBuilder();
        Carte piochee = joueur.prendreCarte(sabot);
        if (piochee != null)
            log.append(joueur).append(" pioche ").append(piochee).append("\n");
        else
            log.append(joueur).append(" ne pioche pas (sabot vide)\n");
        Coup coup = joueur.choisirCoup(joueurs);
        log.append(coup).append("\n");

        joueur.retirerDeLaMain(coup.getCarteJouee());

        if (coup.getJoueurCible() == null) {
            sabot.ajouterCarte(coup.getCarteJouee());
        } else {
            coup.getJoueurCible().deposer(coup.getCarteJouee());
        }

        return log.toString();
    }

}

