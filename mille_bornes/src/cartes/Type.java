package cartes;

public enum Type {
	    ESSENCE  ("Panne d'essence", "Bidon d'essence", "Citerne"),
	    CREVAISON("Crevaison", "Roue de secours", "Increvable"),
	    ACCIDENT ("Accident",  "Réparation", "As du volant"),
    	FEU      ("Feu Rouge", "Feu Vert",  "Prioritaire");

	
	private final String attaque;
	private final String parade;
	private final String botte;

	Type(String attaqueLabel, String paradeLabel, String botteLabel) {
		this.attaque=attaqueLabel;
		this.parade=paradeLabel;
		this.botte=botteLabel;		
	}
	public String getAttaqueLabel() {
		return attaque;
	}
	public String getParadeLabel() {
		return parade ;
	}

	public String getBotteLabel() {
		return botte ;
	}

}
