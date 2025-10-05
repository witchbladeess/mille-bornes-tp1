package testsFonctionnels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestUtilsGestionCartes {

    // méthode statique générique pour comparer les fréquences
    // (vérifie que chaque élément apparaît le même nombre de fois)
    public static <T> boolean memeFrequence(List<T> original, List<T> melangee) {
        if (original.size() != melangee.size()) return false;
        for (T element : original) {
            if (Collections.frequency(original, element) != Collections.frequency(melangee, element)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println("=== TEST AVEC LE JEU DE CARTES ===");

        // Création du jeu
        JeuDeCartes jeu = new JeuDeCartes();

        // On ne détruit pas la liste originale
        List<Carte> listeCarteNonMelangee = new LinkedList<>();
        for (Carte carte : jeu.donnerCartes()) {
            listeCarteNonMelangee.add(carte);
        }

        // Copie dans une ArrayList
        List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);

        // 1. affichage initial
        System.out.println("Cartes originales :");
        System.out.println(listeCarteNonMelangee);

        // 2. mélange
        listeCartes = GestionCartes.melanger(listeCartes);
        System.out.println("\nCartes mélangées :");
        System.out.println(listeCartes);

        // 3. vérification du mélange (avec méthode générique)
        System.out.println("\nListe mélangée sans erreur ? "
                + memeFrequence(listeCarteNonMelangee, listeCartes));

        // 4. rassemblement
        listeCartes = GestionCartes.rassemblerV2(listeCartes);
        System.out.println("\nCartes rassemblées :");
        System.out.println(listeCartes);

        // 5. vérification du rassemblement
        System.out.println("Liste rassemblée sans erreur ? "
                + GestionCartes.verifierRassemblement(listeCartes));

        // --------------------------------------------------------
        // TESTS SUPPLÉMENTAIRES AVEC DES LISTES SIMPLES
        // --------------------------------------------------------

        System.out.println("\n=== TESTS ===");

        List<Integer> l1 = new ArrayList<>(); // []
        List<Integer> l2 = Arrays.asList(1, 1, 2, 1, 3);
        List<Integer> l3 = Arrays.asList(1, 4, 3, 2);
        List<Integer> l4 = Arrays.asList(1, 1, 2, 3, 1);

        // vérifier le rassemblement sur chaque liste
        System.out.println("[] → " + GestionCartes.verifierRassemblement(l1));           // true
        System.out.println("[1,1,2,1,3] → " + GestionCartes.verifierRassemblement(l2));   // false
        System.out.println("[1,4,3,2] → " + GestionCartes.verifierRassemblement(l3));     // true
        System.out.println("[1,1,2,3,1] → " + GestionCartes.verifierRassemblement(l4));   // false

    }
}
