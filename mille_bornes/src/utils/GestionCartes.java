package utils;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private static final Random random = new Random();
	
	// : utilise List directement
	public static <T> T extraire(List<T> liste) {
		if(liste.isEmpty()) throw new IllegalArgumentException("liste vide");
		int index = random.nextInt(liste.size());
		return liste.remove(index);
	}
	
	// version ListIterator
	 public static <T> T extraireAvecIterateur(List<T> liste) {
		if(liste.isEmpty()) throw new IllegalArgumentException("Liste vide");
		int index = random.nextInt(liste.size());
		boolean found = false;
		for(ListIterator<T> iterator = liste.listIterator(); iterator.hasNext() && !found;) {
			
		}
		return null;
		
	 }
	
}
