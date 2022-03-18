import java.util.List;
/**
 * @author jeremy fouquet
 *
 */
public class Meuble {
	private String nom;
	private String couleur;
	private String forme;
	private int taille;
	private int position;
	private char charactere;
	/**
	 * @param nom
	 * @param couleur
	 * @param forme
	 * @param taille
	 * @param position
	 * @param charactere
	 *
	 * CONSTRUCTEUR
	 * 
	 */
	public Meuble(TypeMeuble meuble, List<Meuble> meubles) {
		this.nom = meuble.getNom();
		this.couleur = meuble.getCouleur();
		this.forme = meuble.getForme();
		this.taille = meuble.getTaille();
		this.position = meubles.size()+1;
		this.charactere = meuble.getCharactere();
		meubles.add(this);
	}
	
	// GETTERS ET SETTERS
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getForme() {
		return forme;
	}
	public void setForme(String forme) {
		this.forme = forme;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public char getCharactere() {
		return charactere;
	}
	public void setCharactere(char charactere) {
		this.charactere = charactere;
	}

	@Override
	public String toString() {
		return "Meuble [nom=" + nom + ", couleur=" + couleur + ", forme=" + forme + ", taille=" + taille + ", position="
				+ position + ", charactere=" + charactere + "]";
	}
}
