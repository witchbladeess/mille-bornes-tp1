package cartes;

public class Configuration {
	private int nbExemplaires;
	private Carte carte;
	
	public Configuration(Carte carte, int nbExemplaires) {
		this.nbExemplaires = nbExemplaires;
		this.carte=carte;
		// TODO Auto-generated constructor stub
	}
	public Carte getCarte() {
		return carte;
	}
	public int getNbExemplaires() {
		return nbExemplaires;
	}

}
