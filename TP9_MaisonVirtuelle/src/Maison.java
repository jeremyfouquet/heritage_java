import java.util.ArrayList;
import java.util.List;

/**
 * @author jeremy fouquet
 *
 */
public class Maison {
	public List<Piece> pieces = new ArrayList<Piece>();
	/**
	 * @param generateur si true on ajoute des pieces grace au generateur aleatoire
	 * 
	 * CONSTRUCTEUR
	 */
	public Maison(boolean generateur) {
		new Piece(TypePiece.salon, pieces);
		new Piece(TypePiece.cuisine, pieces);
		new Piece(TypePiece.salleDeBain, pieces);
		new Piece(TypePiece.chambre, pieces);
		new Piece(TypePiece.toilettes, pieces);
		if(generateur) {
			generateurMaisonAleatoire();
		}
	}
	
	/**
	 * @return int nombre de pieces total de la maison
	 * 
	 * Afficher en console la liste des pieces présente dans la maison
	 * Pour chaque piece : son nom, sa dimensions et son visuel
	 * @see Piece#afficherGrille()
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
	 * @return double surface de la maison en m2
	 * 
	 * calcule la surface total de la maison en fonction des pieces quelle contient
	 */
	protected double surfaceTotal() {
		double surfaceTotal = 0;
		for (Piece piece : pieces) {
			surfaceTotal += piece.getSurface();
		}
		return surfaceTotal;
	}
	
	/** 
	 * Genere aleatoirement de 0 à 10 pieces parmis la liste de typePiece connu depuis l'enum
	 */
	protected void generateurMaisonAleatoire() {
		int nbPieces = (int) (Math.random() * 10);
		List<TypePiece> liste= new ArrayList<TypePiece>();
		for (TypePiece piece : TypePiece.values()) {
			liste.add(piece);
		}
		while(nbPieces > 0) {
			int r = (int) (Math.random() * liste.size());
			new Piece(liste.get(r), pieces);
			nbPieces--;
		}
	}

	// GETTERS ET SETTERS
	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

}
