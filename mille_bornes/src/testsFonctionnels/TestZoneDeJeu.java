package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {

    public static void main(String[] args) {
        partie2();
        System.out.println();
        partie3();
        //System.out.println();
       // tp4Partie1();
    }

    // ---------------- TP3 — PARTIE 2 ----------------
    private static void partie2() {
        ZoneDeJeu z = new ZoneDeJeu();

        System.out.println("Deposer carte 25 km");
        z.deposer(new Borne(25));

        System.out.println("Deposer carte 50 km");
        z.deposer(new Borne(50));

        System.out.println("Deposer carte 75 km");
        z.deposer(new Borne(75));

        System.out.println("Total des bornes : " + z.donnerKmParcours());

        System.out.println("Limite : " + z.donnerLimitationVitesse()); // 200
        z.deposer(new DebutLimite());
        System.out.println("Limite : " + z.donnerLimitationVitesse()); // 50
        z.deposer(new FinLimite());
        System.out.println("Limite : " + z.donnerLimitationVitesse()); // 200
    }

    /*
    RESULTAT ATTENDU — TP3 PARTIE 2
        Deposer carte 25 km
        Deposer carte 50 km
        Deposer carte 75 km
        Total des bornes : 150
        Limite : 200
        Limite : 50
        Limite : 200
    */

    // ---------------- TP3 — PARTIE 3 ----------------
    private static void partie3() {
        boolean depotOK;
        ZoneDeJeu z = new ZoneDeJeu();

        System.out.println("Deposer carte Feu rouge");
        depotOK = z.estDepotAutorise(Cartes.FEU_ROUGE);
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(Cartes.FEU_ROUGE);
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte attaque - accident");
        depotOK = z.estDepotAutorise(new Attaque(Type.ACCIDENT));
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(new Attaque(Type.ACCIDENT));
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte Feu vert");
        depotOK = z.estDepotAutorise(Cartes.FEU_VERT);
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(Cartes.FEU_VERT);
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte attaque - essence");
        depotOK = z.estDepotAutorise(new Attaque(Type.ESSENCE));
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(new Attaque(Type.ESSENCE));
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte parade - roue de secours");
        depotOK = z.estDepotAutorise(new Parade(Type.CREVAISON));
        if (depotOK) z.deposer(new Parade(Type.CREVAISON));
        System.out.println("depot ok ? " + depotOK);
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte parade - essence");
        depotOK = z.estDepotAutorise(new Parade(Type.ESSENCE));
        if (depotOK) z.deposer(new Parade(Type.ESSENCE));
        System.out.println("depot ok ? " + depotOK);
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte Feu vert");
        depotOK = z.estDepotAutorise(Cartes.FEU_VERT);
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(Cartes.FEU_VERT);
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte borne - 100");
        depotOK = z.estDepotAutorise(new Borne(100));
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(new Borne(100));
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte limite - 50");
        depotOK = z.estDepotAutorise(new DebutLimite());
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(new DebutLimite());
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte borne - 100");
        depotOK = z.estDepotAutorise(new Borne(100));
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(new Borne(100));
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte borne - 25");
        depotOK = z.estDepotAutorise(new Borne(25));
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(new Borne(25));
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte fin limite - 50");
        depotOK = z.estDepotAutorise(new FinLimite());
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(new FinLimite());
        System.out.println("peut avancer ? " + z.peutAvancer());

        System.out.println("Deposer carte borne - 100");
        depotOK = z.estDepotAutorise(new Borne(100));
        System.out.println("depot ok ? " + depotOK);
        if (depotOK) z.deposer(new Borne(100));
        System.out.println("peut avancer ? " + z.peutAvancer());
    }

    /*
    RESULTAT ATTENDU — TP3 PARTIE 3
        Deposer carte Feu rouge
        depot ok ? false
        peut avancer ? false
        Deposer carte attaque - accident
        depot ok ? false
        peut avancer ? false
        Deposer carte Feu vert
        depot ok ? true
        peut avancer ? true
        Deposer carte attaque - essence
        depot ok ? true
        peut avancer ? false
        Deposer carte parade - roue de secours
        depot ok ? false
        peut avancer ? false
        Deposer carte parade - essence
        depot ok ? true
        peut avancer ? false
        Deposer carte Feu vert
        depot ok ? true
        peut avancer ? true
        Deposer carte borne - 100
        depot ok ? true
        peut avancer ? true
        Deposer carte limite - 50
        depot ok ? true
        peut avancer ? true
        Deposer carte borne - 100
        depot ok ? false
        peut avancer ? true
        Deposer carte borne - 25
        depot ok ? true
        peut avancer ? true
        Deposer carte fin limite - 50
        depot ok ? true
        peut avancer ? true
        Deposer carte borne - 100
        depot ok ? true
        peut avancer ? true
    */

    // ---------------- TP4 — PARTIE 1 ----------------
//    private static void tp4Partie1() {
//        boolean depotOK;
//        ZoneDeJeu z = new ZoneDeJeu();
//
//        System.out.println("Deposer carte Feu rouge");
//        depotOK = z.estDepotAutorise(Cartes.FEU_ROUGE);
//        System.out.println("depot ok ? " + depotOK);
//        if (depotOK) z.deposer(Cartes.FEU_ROUGE);
//        System.out.println("peut avancer ? " + z.peutAvancer());
//
//        System.out.println("Deposer carte Vehicule prioritaire");
//        depotOK = z.estDepotAutorise(Cartes.PRIORITAIRE);
//        System.out.println("depot ok ? " + depotOK);
//        if (depotOK) z.deposer(Cartes.PRIORITAIRE);
//        System.out.println("peut avancer ? " + z.peutAvancer());
//
//        System.out.println("Deposer carte attaque - accident");
//        depotOK = z.estDepotAutorise(new Attaque(Type.ACCIDENT));
//        System.out.println("depot ok ? " + depotOK);
//        if (depotOK) z.deposer(new Attaque(Type.ACCIDENT));
//        System.out.println("peut avancer ? " + z.peutAvancer());
//
//        System.out.println("Deposer botte - roue de secours");
//        depotOK = z.estDepotAutorise(new Botte(Type.CREVAISON));
//        if (depotOK) z.deposer(new Botte(Type.CREVAISON));
//        System.out.println("depot ok ? " + depotOK);
//        System.out.println("peut avancer ? " + z.peutAvancer());
//
//        System.out.println("Deposer carte as du volant");
//        depotOK = z.estDepotAutorise(new Botte(Type.ACCIDENT));
//        if (depotOK) z.deposer(new Botte(Type.ACCIDENT));
//        System.out.println("depot ok ? " + depotOK);
//        System.out.println("peut avancer ? " + z.peutAvancer());
//
//        System.out.println("Deposer carte Feu vert");
//        depotOK = z.estDepotAutorise(Cartes.FEU_VERT);
//        System.out.println("depot ok ? " + depotOK);
//        if (depotOK) z.deposer(Cartes.FEU_VERT);
//        System.out.println("peut avancer ? " + z.peutAvancer());
//
//        System.out.println("Deposer carte accident");
//        depotOK = z.estDepotAutorise(new Attaque(Type.ACCIDENT));
//        System.out.println("depot ok ? " + depotOK);
//        if (depotOK) z.deposer(new Attaque(Type.ACCIDENT));
//        System.out.println("peut avancer ? " + z.peutAvancer());
//
//        System.out.println("Deposer carte limite - 50");
//        depotOK = z.estDepotAutorise(new DebutLimite());
//        System.out.println("depot ok ? " + depotOK);
//        if (depotOK) z.deposer(new DebutLimite());
//        System.out.println("peut avancer ? " + z.peutAvancer());
//    }

    /*
    RESULTAT ATTENDU — TP4 PARTIE 1
        Deposer carte Feu rouge
        depot ok ? false
        peut avancer ? false
        Deposer carte Vehicule prioritaire
        depot ok ? true
        peut avancer ? true
        Deposer carte attaque - accident
        depot ok ? true
        peut avancer ? false
        Deposer botte - roue de secours
        depot ok ? true
        peut avancer ? false
        Deposer carte as du volant
        depot ok ? true
        peut avancer ? true
        Deposer carte Feu vert
        depot ok ? false
        peut avancer ? true
        Deposer carte accident
        depot ok ? false
        peut avancer ? true
        Deposer carte limite - 50
        depot ok ? false
        peut avancer ? true
    */
}
