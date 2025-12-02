package strategies;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

import jeu.Coup;

public interface Strategie {

    default Set<Coup> trierCoups(Set<Coup> coups) {
        Random random = new Random();

        Comparator<Coup> comp = new Comparator<Coup>() {
            @Override
            public int compare(Coup c1, Coup c2) {

                if (c1.equals(c2)) {
                    return 0;
                }
                return random.nextBoolean() ? -1 : 1;
            }
        };

        TreeSet<Coup> sorted = new TreeSet<>(comp);
        sorted.addAll(coups);
        return sorted;
    }
    default Coup selectionnerCoup(Set<Coup> coups) {
        return trierCoups(coups).iterator().next();
    }

    default Coup selectionnerDefausse(Set<Coup> coups) {
        Set<Coup> sorted = trierCoups(coups);
        Coup dernier = null;
        for (Coup c : sorted) {
            dernier = c;
        }
        return dernier;
    }
}
