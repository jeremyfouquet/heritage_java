import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Porte extends Meuble {
	private List<Piece> connecte = new ArrayList<Piece>(2);

	/**
	 * @param meuble
	 * @param meubles
	 */
	public Porte(TypeMeuble meuble, List<Meuble> meubles, List<Piece> connecte) {
		super(meuble, meubles);
		this.connecte = connecte;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porte other = (Porte) obj;
		return Objects.equals(connecte, other.connecte);
	}

	/**
	 * @return
	 * 
	 * Stock dans une nouvelle list toute les pieces connecté par cet Class Porte
	 * et retourne ensuite ce tableau
	 */
	protected List<Piece> stockPieces() {
		List<Piece> stock = new ArrayList<Piece>(2);
		for (Piece piece : this.getConnecte()) {
			stock.add(piece);
			System.out.printf("%s \n", piece.getType());
		}
		return stock;
	}

	public List<Piece> getConnecte() {
		return connecte;
	}

	public void setConnecte(List<Piece> connecte) {
		this.connecte = connecte;
	}

}
