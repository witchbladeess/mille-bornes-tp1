package cartes;

public class Parade extends Bataille implements Comparable<Parade> {

	public Parade(Type type) {
		super(type);
	}
	@Override
	public String toString() {
		return getType().getParadeLabel();
	}
	public int compareTo(Parade parade2) {
		return this.getType().compareTo(parade2.getType());

	}
}
