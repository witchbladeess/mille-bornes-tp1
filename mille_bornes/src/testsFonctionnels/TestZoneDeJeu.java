package testsFonctionnels;

import cartes.Borne;
import cartes.DebutLimite;
import cartes.FinLimite;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {
    public static void main(String[] args) {
        ZoneDeJeu z = new ZoneDeJeu();

        System.out.println("Deposer carte 25 km");
        z.deposer(new Borne(25));

        System.out.println("Deposer carte 50 km");
        z.deposer(new Borne(50));

        System.out.println("Deposer carte 75 km");
        z.deposer(new Borne(75));

        System.out.println("Total des bornes : " + z.donnerKmParcours());

        System.out.println("Limite : " + z.donnerLimitationVitesse()); // pile vide → 200

        z.deposer(new DebutLimite());
        System.out.println("Limite : " + z.donnerLimitationVitesse()); // 50

        z.deposer(new FinLimite());
        System.out.println("Limite : " + z.donnerLimitationVitesse()); // 200
    }
}
