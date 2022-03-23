import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jeremy fouquet
 *
 */
public class Piece {
	private int taille;
	private double surface;
	private String couleur;
	private int position; // position dans la maison
	private String type; // nom de la piece
	public List<Meuble> meubles = new ArrayList<Meuble>(); // Liste des meubles present dans la piece
	/**
	 * @param typePiece
	 * @param pieces
	 * 
	 * CONSTRUCTEUR
	 */
	public Piece(TypePiece typePiece, List<Piece> pieces) {
		this.couleur = typePiece.getCouleur();
		this.position = pieces.size()+1;		
		this.type = typePiece.getNom();
		for (TypeMeuble typeMeuble : typePiece.getTypeMeubles()) {
			new Meuble(typeMeuble, meubles);
		}
		this.taille = this.getDimensions();
		this.surface = this.getTaille()*5;
		pieces.add(this);
		if(this.position > 1) {
			Piece pieceVoisine = pieces.get(this.position - 2);
			List<Piece> connecte = Arrays.asList(pieceVoisine, this);
			new Porte(TypeMeuble.porte, pieceVoisine.meubles, connecte);
			new Porte(TypeMeuble.porte, meubles, connecte);
		}
	}
	/**
	 * @return int Nombre de meuble possédé par la piece
	 * 
	 * afficher en console chaque meuble possédé par la pièce
	 */
	protected int afficherMeubles() {
		for (Meuble meuble : meubles) {
			System.out.printf("%s\n", meuble.getNom());
			System.out.printf("Couleur  : %s\n", meuble.getCouleur());
			System.out.printf("Forme : %s\n", meuble.getForme() == null? meuble.getForme() : "Aucune");
			System.out.printf("Taille  : %s\n", meuble.getTaille());
			System.out.printf("Position : %s\n", meuble.getPosition());
			System.out.printf("Representation : %s\n", meuble.getCharactere());
			System.out.printf("%s\n", "--------------------");

		}
		return meubles.size();
	}
	
	/**
	 * @return int Nombre de meuble possédé par la piece
	 * 
	 * afficher en console une grille sur 4 colonnes représentant la pièce
	 * où chacun de ces meubles est symbolisé par son charactère. Aucun espace pour circuler
	 * n'est représenté ce qui donne à la piece un aspect tres compact !!
	 */
	protected int afficherGrille() {
		int surfaceCarre = this.getDimensions();
		List<Character> grille = new ArrayList<Character>(surfaceCarre);
		for(int i = 0; i < surfaceCarre; i++) {grille.add(' ');};
		int index = 0;
		for (Meuble meuble : meubles) {
			int taille = meuble.getTaille();
			String forme = meuble.getForme();
			while(grille.get(index) != ' ') {
				index++;
			}
			if(forme == "carré" && taille == 4) {
				grille.set(index, meuble.getCharactere());
				grille.set(index+1, meuble.getCharactere());
				grille.set(index+4, meuble.getCharactere());
				grille.set(index+5, meuble.getCharactere());
				index+=2;
			} else if (forme == "rectangle" && taille == 2) {
				grille.set(index, meuble.getCharactere());
				grille.set(index+1, meuble.getCharactere());
				index+=2;
			} else {
				if(taille != 0) {
					grille.set(index, meuble.getCharactere());
					index++;
				}
			}
		}
		int modulo = 0;
		for (Character character : grille) {
			modulo++;
			System.out.printf("%c ", character);
			if(modulo%4 == 0) {
				System.out.println();
			}
			
		}
		return meubles.size();
	}	
	
	/**
	 * @return int Nombre total de meuble (+ 4 Si c'est un multiple de 4) (Sinon on ajout la difference pour en faire un multiple de 4)
	 * 
	 * Calcule la dimension total de la piece, qui doit etre assez grande pour accueillir tous les meubles
	 */
	protected int getDimensions() {
		int surfaceTotal = 0;
		for (Meuble meuble : meubles) {
			surfaceTotal += meuble.getTaille();
		}
		int dimensions = surfaceTotal%4 == 0? surfaceTotal + 4 : surfaceTotal+(4-surfaceTotal%4);
		return dimensions;
	}

	// GETTERS ET SETTERS
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public double getSurface() {
		return surface;
	}
	public void setSurface(double surface) {
		this.surface = surface;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Meuble> getMeuble() {
		return meubles;
	}
	public void setMeuble(List<Meuble> meuble) {
		this.meubles = meuble;
	}
	@Override
	public String toString() {
		return "Piece [taille=" + taille + ", surface=" + surface + ", couleur=" + couleur + ", position=" + position
				+ ", type=" + type + ", meubles=" + meubles + "]";
	}

}
