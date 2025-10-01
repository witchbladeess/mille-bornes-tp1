package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Parade;
import cartes.Type;

public class TestMethodeEquals {
	public static void main(String[] args) {
		//a. Deux bornes 25 km
		Borne borne1 = new Borne(25);
		Borne borne2 = new Borne(25);
		System.out.println("Deux cartes sont identiques? " + borne1.equals(borne2));
		
		//b. Deux feux rouges (attaque type feu)
		Attaque fR1 = new Attaque(Type.FEU);
		Attaque fR2 = new Attaque(Type.FEU);
		System.out.println("Deux cartes de feux rouge sont identiques? " + fR1.equals(fR2));
		
		//c. Un feu rouge et un feu vert (parade de type feu)
		Parade feuVert = new Parade(Type.FEU);
		System.out.println("La carte feu rouge et la carte feu vert sont identiques? " + fR1.equals(feuVert));

	}
}
