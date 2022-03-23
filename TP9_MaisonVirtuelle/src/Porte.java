import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * @author jeremy fouquet
 *
 */
public class Porte extends Meuble {
	private List<Piece> connecte = new ArrayList<Piece>(2);

	/**
	 * @param meuble
	 * @param meubles
	 * @param connecte
	 * 
	 * CONSTRUCTEUR
	 */
	public Porte(TypeMeuble meuble, List<Meuble> meubles, List<Piece> connecte) {
		super(meuble, meubles);
		this.connecte = connecte;
	}

	/**
	 * @return List<Piece> Liste des Pieces stocké par la porte
	 * 
	 * Stock dans une nouvelle list toute les pieces connecté par cet Class Porte
	 * et retourne ensuite ce tableau
	 */
	protected List<Piece> stockPieces() {
		List<Piece> stock = new ArrayList<Piece>(2);
		for (Piece piece : this.connecte) {
			stock.add(piece);
		}
		return stock;
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

	// GETTERS ET SETTERS

	public List<Piece> getConnecte() {
		return connecte;
	}

	public void setConnecte(List<Piece> connecte) {
		this.connecte = connecte;
	}

}
