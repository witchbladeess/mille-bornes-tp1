package cartes;

public class JeuDeCartes {

	private final Configuration[] typesDeCartes = new Configuration[] {
	        new Configuration(new Borne(25), 10),
	        new Configuration(new Borne(50), 10),
	        new Configuration(new Borne(75), 10),
	        new Configuration(new Borne(100), 12),
	        new Configuration(new Borne(200), 4),

	        new Configuration(new Parade(Type.FEU), 14), // Feu Vert
	        new Configuration(new FinLimite(), 6),	        
	        new Configuration(new Parade(Type.ESSENCE), 6), // 6 bidon essence
	        
	        
	        new Configuration(new Parade(Type.CREVAISON), 6),  //6 roue de secours
	        new Configuration(new Parade(Type.ACCIDENT), 6),// 6 reparation

	        
	        new Configuration(new Attaque(Type.FEU), 5),          // Feu Rouge  
	        new Configuration(new DebutLimite(), 4),              // Limite 50
	        new Configuration(new Attaque(Type.ESSENCE), 3),      // Panne d'essence  
	        new Configuration(new Attaque(Type.CREVAISON), 3),    // Crevaison     
	        new Configuration(new Attaque(Type.ACCIDENT), 3),     
	        new Configuration(new Botte(Type.FEU), 1), // 1 prioritaire
	        new Configuration(new Botte(Type.ESSENCE), 1), // 1 citerne
	        new Configuration(new Botte(Type.CREVAISON), 1), // 1 increvable
	        new Configuration(new Botte(Type.ACCIDENT), 1), // 1 as au volant

	    };

	
	
		
	public Carte[] donnerCartes() {
		int total = 0;
		for(Configuration config: typesDeCartes) {
			total+=config.getNbExemplaires();
		}
		Carte[] allCartes = new Carte[total];
		int index = 0;
		for(Configuration config: typesDeCartes) {
			
			for(int i =0; i< config.getNbExemplaires(); i++) {
				allCartes[index++] = config.getCarte();
			}
		}
		
		return allCartes;
		
	}

	public String affichageJeuCartes() {
		StringBuilder sb = new StringBuilder("JEU : \n");
		for(Configuration config: typesDeCartes) {
			sb.append(config.getNbExemplaires());
			sb.append(' ');
			sb.append(config.getCarte());
			sb.append('\n');
		}
		return sb.toString();

	}

	public boolean checkCount() {
		Carte[] all = donnerCartes();
		int total = 0;
		for (Configuration cfg : typesDeCartes) {
		    int expected = cfg.getNbExemplaires();
		    total += expected;
		    int actual = 0;
		    for (Carte c : all) if (c.equals(cfg.getCarte())) actual++;
		        if (expected != actual) return false;
		    }
		    return all.length == total;
		}

	} 

