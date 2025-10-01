package cartes;

public class Borne extends Carte {

	protected int km;
	public Borne(int km) {
		this.km=km;
	}
	@Override
	public String toString() {
		
		return km + "KM";
	}

	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj)) return false;
		if(!(obj instanceof Borne borne)) return false;
		return this.km == borne.km;
	}

}
