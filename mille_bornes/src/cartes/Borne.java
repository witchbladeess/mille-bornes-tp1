package cartes;

public class Borne extends Carte {

	protected int km;
	public Borne(int km) {
		this.km=km;
	}
	public Borne() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		
		return km + "KM";
	}

	

}
