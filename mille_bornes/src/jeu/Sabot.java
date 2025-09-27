package jeu;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable <Carte>{
	private Carte[] cartes;
	private int nbCartes =0 ;
	private int nbOperations = 0; //compteur global de modif
	
	public Sabot(Carte[] cartes) {
		this.cartes= new Carte[cartes.length];
		for(int i = 0; i < cartes.length;i++) {
			this.cartes[i] = cartes[i];
		}
		this.nbCartes = cartes.length;
	}
	
	public int getNbCartes() {
		return nbCartes;
	}
		
	public boolean estVide() {
		return nbCartes == 0;
	}
	public void ajouterCarte(Carte carte) {
		if(nbCartes==cartes.length) {
			throw new IllegalStateException("Le sabot est plein!");
		}
		cartes[nbCartes] = carte;
		nbCartes++;
		nbOperations++;
		
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	private class Iterateur implements Iterator<Carte>{

		private int indiceIterateur = 0; //prochain élément
		private boolean nextEffectuer = false; // next() a été appelé?
		private int nbOperationsreference = nbOperations; // snapshot du compteur
		@Override
		public boolean hasNext() {
			return indiceIterateur<nbCartes;
		}

		@Override
		public Carte next() {
			verificationConcurrence();
			if(hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectuer=true;
				return carte;
			}else {
				throw new NoSuchElementException();
			}
			
		}

		private void verificationConcurrence() {
			if(nbOperations!=nbOperationsreference) {
				throw new ConcurrentModificationException();
			}
			
		}
		@Override 
		public void remove() {
			verificationConcurrence();
			if(nbCartes<1 || !nextEffectuer) {
				throw new IllegalStateException();
			}
			int pos = indiceIterateur-1;
			for(int i = pos; i < nbCartes-1; i++ ) {
				cartes[i] = cartes[i+1];
			}
			nbCartes--;
			cartes[nbCartes] = null;
			nextEffectuer=false;
			indiceIterateur--;
			nbOperationsreference++;
			nbOperations++;
			
		}
		
	}
	public Carte piocher() {
		Iterator<Carte> iter = iterator();
		if(!iter.hasNext()) {
			throw new NoSuchElementException("Le sabot est vide");
		}
		Carte carte = iter.next(); //prend la 1ere carte
		iter.remove(); // supprime du sabot
		return carte;

	}
	
	
}
