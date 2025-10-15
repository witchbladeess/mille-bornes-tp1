package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> cartes;
	
	public MainJoueur() {
		cartes = new ArrayList<>();
	}
	public void prendre(Carte c) {
		cartes.add(c);
	}
	public void jouer(Carte c) {
		assert cartes.contains(c): "La carte n'est pas dans la main du joueur";
		cartes.remove(c);
	}
	
	
	@Override 
	public String toString() {
		StringBuilder info = new StringBuilder();
		for(ListIterator<Carte> iter = cartes.listIterator(); iter.hasNext();) {
			info.append(iter.next().toString());
			if(iter.hasNext()) info.append(", ");
		}
		return info.toString();
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return cartes.iterator();
	}
	
}
