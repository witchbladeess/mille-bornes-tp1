package cartes;

public class Borne extends Carte implements Comparable<Borne> {

	protected int km;
	public Borne(int km) {
		this.km=km;
	}
	@Override
	public String toString() {
		
		return getKm() + "KM";
	}

	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj)) return false;
		return this.getKm() == ((Borne) obj).getKm();
	}
	public int getKm() {
		return km;
	}
    @Override
    public int compareTo(Borne autre) {
        return this.km - autre.km;
    }


}
