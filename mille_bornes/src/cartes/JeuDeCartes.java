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

	        
	        new Configuration(new Parade(Type.FEU), 5), // Feu rouge
	        new Configuration(new DebutLimite(), 4), 
	        new Configuration(new Parade(Type.ESSENCE), 3), // panne d'essence
	        new Configuration(new Parade(Type.ESSENCE), 3), // panne d'essence 

	        new Configuration(new Parade(Type.CREVAISON), 3), // 3 crevaison 
	        new Configuration(new Parade(Type.ACCIDENT), 3), // 3 accident
	        new Configuration(new Parade(Type.FEU), 1), // 1 prioritaire
	        new Configuration(new Parade(Type.ESSENCE), 1), // 1 citerne
	        new Configuration(new Parade(Type.CREVAISON), 1), // 1 increvable
	        new Configuration(new Parade(Type.ACCIDENT), 1), // 1 as au volant

	    };

	
	
	public String afficherJeuDeCartes() {
		StringBuilder sb = new StringBuilder("JEU : \n");
		for(Configuration config: typesDeCartes) {
			sb.append(config.getNbExemplaires());
			sb.append(' ');
			sb.append(config.getCarte());
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public Carte[] donnerCartes() {
		int total = 0;
		for(Configuration config: typesDeCartes) {
			total+=config.getNbExemplaires();
		}
		Carte[] allCartes = new Carte[total];
		
		for(Configuration config: typesDeCartes) {
			int index = 0;
			for(int i =0; i< config.getNbExemplaires(); i++) {
				allCartes[index++] = config.getCarte();
			}
		}
		
		return allCartes;
		
	} 
}
