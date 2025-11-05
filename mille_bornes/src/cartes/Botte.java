package cartes;

public class Botte extends Probleme {

		public Botte(Type type) {
		super(type);
		}

		@Override
		public String toString() {
			return getType().getBotteLabel();
		}
		//tp4
		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			if(!(obj instanceof Botte)) return false;
			Botte autre = (Botte) obj;
			return this.type == autre.type;
		}
		@Override 
		public int hashCode() {
			return type.hashCode();
		}
}
