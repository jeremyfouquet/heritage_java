import java.util.ArrayList;
import java.util.List;

/**
 * @author jeremy fouquet
 *
 */
public class Maison {
	public List<Piece> pieces = new ArrayList<Piece>();
	/**
	 * 
	 * CONSTRUCTEUR
	 */
	public Maison() {
		new Piece(TypePiece.salon, pieces);
		new Piece(TypePiece.cuisine, pieces);
		new Piece(TypePiece.salleDeBain, pieces);
		new Piece(TypePiece.chambre, pieces);
		new Piece(TypePiece.toilettes, pieces);
	}
	
	/**
	 * @return
	 * 
	 * afficher en console la liste des pieces présente dans la maison
	 * pour chaque piece : son nom, sa dimensions et son visuel (recuperé dupuis la methode afficheGrille)
	 * Puis retourn le nombre de piece
	 */
	protected int afficherPieces() {
		for (Piece piece : pieces) {
			System.out.printf("Nom : %s \n", piece.getType());
			System.out.printf("Dimensions : %d \n", piece.getDimensions());
			System.out.printf("Visuel : \n");
			piece.afficherGrille();
			System.out.printf("%s \n", "------");
		}
		return pieces.size();
	}
	
	/**
	 * @return
	 * 
	 * retourne la surface total de la maison
	 */
	protected double surfaceTotal() {
		double surfaceTotal = 0;
		for (Piece piece : pieces) {
			surfaceTotal += piece.getSurface();
		}
		return surfaceTotal;
	}

	// GETTERS ET SETTERS
	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

}
