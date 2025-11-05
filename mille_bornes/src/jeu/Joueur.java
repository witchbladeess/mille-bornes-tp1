package jeu;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
	public MainJoueur getMain() {
	    return main;
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
	public boolean estDepotAutorise(Carte carte) {
	    return zone.estDepotAutorise(carte);
	}
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> res = new HashSet<>();
		for(Carte c : main) {
			Coup versSabot = new Coup(this, c, null);
			if(versSabot.estValide()) res.add(versSabot);
			for (Joueur cible : participants) {
				Coup coup = new Coup(this, c, cible);
				if(coup.estValide()) res.add(coup);
			}
		}
		return res;		
	}
	public Set<Coup> coupsDefausse(){
		Set <Coup> defausse = new HashSet<>();
		for(Carte carte : main) {
			Coup coup = new Coup(this, carte, null);
			defausse.add(coup);
		}
		return defausse;
	}
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	public Coup choisirCoup(Set<Joueur> participants) {
        Set<Coup> possibles = coupsPossibles(participants);

        if (!possibles.isEmpty()) {
            return choisirAleatoirement(possibles);
        } else {
            Set<Coup> defausse = coupsDefausse();
            return choisirAleatoirement(defausse);
        }
    }
	private Coup choisirAleatoirement(Set<Coup> ensemble) {
        int taille = ensemble.size();
        if (taille == 0) return null;

        int index = new Random().nextInt(taille);
        int i = 0;
        for (Coup c : ensemble) {
            if (i == index) return c;
            i++;
        }
        return null; 
    }

	public String afficherEtatJoueur() {
	    StringBuilder etat = new StringBuilder();
	    etat.append("Joueur : ").append(nom).append("\n");

	    etat.append("Bottes : ");
	    if (zone.getBottes().isEmpty()) etat.append("aucune");
	    else etat.append(zone.getBottes());
	    etat.append("\n");

	    int limite = zone.donnerLimitationVitesse();
	    boolean limiteActive = (limite == 50);
	    etat.append("Limitation de vitesse : ").append(limiteActive).append("\n");

	    if (zone.getPileBataille().isEmpty())
	        etat.append("Sommet bataille : null\n");
	    else {
	        etat.append("Sommet bataille : ")
	            .append(zone.getPileBataille()
	                    .get(zone.getPileBataille().size() - 1))
	            .append("\n");
	    }

	    etat.append("Main : ").append(main.toString());

	    return etat.toString();
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
