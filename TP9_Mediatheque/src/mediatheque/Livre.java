package mediatheque;

import java.util.Objects;

public class Livre extends Document {
	private String auteur;
	private String editeur;
	private Categorie categorie;
	private int nbJourRetour = 30;


	/**
	 * @param nom
	 * @param titre
	 * @param restriction
	 * @param auteur
	 * @param editeur
	 * @param categorie
	 */
	public Livre(String nom, String titre, boolean restriction, String auteur, String editeur, Categorie categorie) {
		super(nom, titre, restriction);
		super.setIdentifiant(this.getClass().getSimpleName()+Integer.toString(Mediatheque.nextId++));
		this.auteur = auteur;
		this.editeur = editeur;
		this.categorie = categorie;
		super.setNbJourRetour(nbJourRetour);
	}

	@Override
	public void voirDetail() {
		System.out.printf("Identifiant : %s\n", super.getIdentifiant());
		System.out.printf("Document : %s\n", getClass().getSimpleName());
		System.out.printf("Nom : %s\n", super.getNom());
		System.out.printf("Titre : %s\n", super.getTitre());
		System.out.printf("Auteur : %s\n", this.auteur);
		System.out.printf("Editeur : %s\n", this.editeur);
		System.out.printf("Categorie : %s\n", this.categorie);
		System.out.printf("Emprunt autorisé : %s\n", super.isRestriction()? "Non":"Oui");
		if (null != super.getDateRetour()) {
			System.out.printf("Emprunté jusqu'au : %s\n", super.getDateRetour());
		};
	}

	//GETTERS ET SETTERS

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public int getNbJourRetour() {
		return nbJourRetour;
	}
	
	public void setNbJourRetour(int nbJourRetour) {
		this.nbJourRetour = nbJourRetour;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livre other = (Livre) obj;
		return Objects.equals(auteur, other.auteur) && categorie == other.categorie
				&& Objects.equals(editeur, other.editeur) && nbJourRetour == other.nbJourRetour;
	}
	
	



}
