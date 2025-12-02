package strategies;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import cartes.Carte;
import jeu.Coup;
import jeu.Joueur;

public interface Presse extends Strategie, Priorite {

    @Override
    default Set<Coup> trierCoups(Set<Coup> coups) {

        TreeSet<Coup> ensembleTrie = new TreeSet<>(new Comparator<Coup>() {

            @Override
            public int compare(Coup c1, Coup c2) {

                int cmp = c1.compareTo(c2);
                if (cmp != 0) {
                    return cmp;
                }

                Joueur joueur = c1.getJoueurCourant();
                Carte carte1 = c1.getCarteJouee();
                Carte carte2 = c2.getCarteJouee();

                return comparerCartes(joueur, carte1, carte2);
            }
        });

        ensembleTrie.addAll(coups);
        return ensembleTrie;
    }


    private int comparerCartes(Joueur joueur, Carte carte1, Carte carte2) {

        Integer comparaison = null;

        comparaison = donnerPrioriteLimites(carte1, carte2);
        if (comparaison != null) return comparaison;

        comparaison = donnerPrioriteBornes(carte1, carte2);
        if (comparaison != null) return comparaison;

        Carte carteSommet = joueur.donnerSommetPile();
        if (carteSommet instanceof cartes.Attaque attaque) {

            cartes.Type typeProbleme = attaque.getType();

            if (joueur.donnerBottes().contains(new cartes.Botte(typeProbleme))) {
                typeProbleme = cartes.Type.FEU;
            }

            comparaison = donnerPrioriteBottes(typeProbleme, carte1, carte2);
            if (comparaison != null) return comparaison;
        }

        comparaison = donnerPrioriteParades(carte1, carte2);
        if (comparaison != null) return comparaison;

        return RANDOM.nextBoolean() ? 1 : -1;
    }
}
