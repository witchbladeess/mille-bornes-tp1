package cartes;

public abstract class Probleme extends Carte {

	protected final Type type;
	protected Probleme(Type type) {
		this.type = type;
	}
	public Type getType() {
		return type;
	}
	public Probleme() {
		this.type = null;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
