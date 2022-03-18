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
	private int position;
	private String type;
	public List<Meuble> meubles = new ArrayList<Meuble>();
	/**
	 * @param taille
	 * @param surface
	 * @param couleur
	 * @param position
	 * @param typePiece
	 * 
	 * CONSTRUCTEUR
	 */
	public Piece(TypePiece typePiece, List<Piece> pieces) {
		this.setCouleur(typePiece.getCouleur());
		this.setPosition(pieces.size()+1);		
		this.setType(typePiece.getNom());
		for (TypeMeuble typeMeuble : typePiece.getTypeMeubles()) {
			new Meuble(typeMeuble, meubles);
		}
		this.setTaille(this.getDimensions());
		this.setSurface(this.getTaille()*5);
		pieces.add(this);
		if(this.getPosition() > 1) {
			Piece pieceVoisine = pieces.get(this.getPosition()-2);
			List<Piece> connecte = Arrays.asList(pieceVoisine, this);
			new Porte(TypeMeuble.porte, pieceVoisine.meubles, connecte);
			new Porte(TypeMeuble.porte, meubles, connecte);
		}
	}
	/**
	 * @return
	 * 
	 * afficher en console chaque meuble possédé par la pièce et retourne la taille total de meuble
	 */
	protected int afficherMeubles() {
		for (Meuble meuble : meubles) {
			System.out.printf("%s \n", meuble);
		}
		return meubles.size();
	}
	
	/**
	 * @return
	 * 
	 * afficher en console une grille par 4 colonnes représentant la pièce
	 * et chacun de ces meubles est symbolisé par son charactère. 
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
	 * @return
	 * 
	 * Additionne la taille total de tout les meubles de la piece et ajoute 4 si c'est déjà un multiple de 4 ou bien
	 * la différence pour arrivé à un multiple de 4 avant de retourné ce resultat. Correspond à l'espace suffisant pour stocker
	 * tout les meubles dans un espace carré
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
