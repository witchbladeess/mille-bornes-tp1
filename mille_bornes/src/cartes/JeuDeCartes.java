package cartes;

import java.util.HashMap;
import java.util.Map;

public class JeuDeCartes {

    private final Map<Carte, Integer> configuration;

    public JeuDeCartes() {
        configuration = new HashMap<>();

        configuration.put(new Borne(25), 10);
        configuration.put(new Borne(50), 10);
        configuration.put(new Borne(75), 10);
        configuration.put(new Borne(100), 12);
        configuration.put(new Borne(200), 4);

        configuration.put(new Parade(Type.FEU), 14);        
        configuration.put(new FinLimite(), 6);
        configuration.put(new Parade(Type.ESSENCE), 6);
        configuration.put(new Parade(Type.CREVAISON), 6);
        configuration.put(new Parade(Type.ACCIDENT), 6);

        configuration.put(new Attaque(Type.FEU), 5);
        configuration.put(new DebutLimite(), 4);
        configuration.put(new Attaque(Type.ESSENCE), 3);
        configuration.put(new Attaque(Type.CREVAISON), 3);
        configuration.put(new Attaque(Type.ACCIDENT), 3);

        configuration.put(new Botte(Type.FEU), 1);
        configuration.put(new Botte(Type.ESSENCE), 1);
        configuration.put(new Botte(Type.CREVAISON), 1);
        configuration.put(new Botte(Type.ACCIDENT), 1);
    }
    public Carte[] donnerCartes() {

        int total = 0;
        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
            total += entry.getValue();
        }

        Carte[] allCartes = new Carte[total];
        int index = 0;

        // Création du tableau final
        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
            Carte carte = entry.getKey();
            int nb = entry.getValue();

            for (int i = 0; i < nb; i++) {
                allCartes[index++] = carte;
            }
        }

        return allCartes;
    }

    public String affichageJeuCartes() {
        StringBuilder sb = new StringBuilder("JEU : \n");

        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
            sb.append(entry.getValue())
              .append(" × ")
              .append(entry.getKey())
              .append("\n");
        }

        return sb.toString();
    }
    public boolean checkCount() {
        Carte[] all = donnerCartes();

        int total = 0;
        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
            total += entry.getValue();
        }

        if (total != all.length) return false;

        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {

            Carte carte = entry.getKey();
            int expected = entry.getValue();
            int actual = 0;

            for (Carte c : all) {
                if (c.equals(carte)) {
                    actual++;
                }
            }

            if (expected != actual) return false;
        }

        return true;
    }
}
