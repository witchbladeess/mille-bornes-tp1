package cartes;

public enum Type {
	FEU		("Feu rouge",	 "Feu vert", 	"Prioritaire"),
	ESSENCE	("Panne d'essence", "Bidon d'essence", "Citerne"),
	CREVAISON("Crevaison", "Increvable", "Roue de secours"),
	ACCIDENT("Accident", "As du volant", "Réparation");
	
	private final String attaqueLabel;
	private final String paradeLabel;
	private final String botteLabel;

	Type(String attaqueLabel, String paradeLabel, String botteLabel) {
		this.attaqueLabel=attaqueLabel;
		this paradeLabel=paradeLabel;
		this botteLabel=botteLabel;		
	}
	public String getAttaqueLabel() {
		return attaqueLabel;
	}
	public String getParadeLabel() {
		return paradeLabel ;
	}

	public String getBotteLabel() {
		return botteLabel ;
	}

}
