package utils;

import java.util.ArrayList;
import java.util.Collections;
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
		int i = 0;
		T element = null;
		for(ListIterator<T> iterator = liste.listIterator(); iterator.hasNext() && !found;) {
			T courant = iterator.next();
			if(i == index) {
				element = courant;
				iterator.remove();
				found = true;
			}
		}
		return element;		
	 }
	
	 public static <T> List<T> melanger(List <T> liste){
		 List <T> melangee = new ArrayList<T>(); 
		 while(!liste.isEmpty()) {
			 T element = extraire(liste);
			 melangee.add(element);
		 }
		 return melangee;
	 }
	 
	 public static <T> boolean verifierMelange(List <T> l1, List<T> l2) {
		 if(l1.size() != l2.size()) return false;
		 for(T element : l1) {
			 int freq1 = Collections.frequency(l1, element);
			 int freq2 = Collections.frequency(l2, element);
			 if(freq1!=freq2) return false;
		 }
		 return true;
	 }
	 
	 public static <T> List <T> rassembler(List<T> liste){
		 List<T> result = new ArrayList<T>();
		 for(T element : liste) {
			 int lastindex = result.lastIndexOf(element);
			 if(lastindex == -1) {
				 result.add(element);
			 } else {
				 result.add(lastindex+1, element);
			 }
			 
		 }
		 return result;
	 }
	 public static <T> boolean verifierRassemblement(List<T> liste) {
		 if (liste.size() <= 1) return true;
		 ListIterator<T> iterator1 = liste.listIterator();
		 
		 
	 }
	 
}
