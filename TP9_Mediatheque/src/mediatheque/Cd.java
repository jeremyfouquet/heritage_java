package mediatheque;

import java.util.Objects;

public class Cd extends Document {
	private String auteur;
	private String compositeur;
	private int nbJourRetour = 7;


	/**
	 * @param nom
	 * @param titre
	 * @param restriction
	 * @param auteur
	 * @param compositeur
	 */
	public Cd(String nom, String titre, boolean restriction, String auteur, String compositeur) {
		super(nom, titre, restriction);
		super.setIdentifiant(this.getClass().getSimpleName()+Integer.toString(Mediatheque.nextId++));
		this.auteur = auteur;
		this.compositeur = compositeur;
		super.setNbJourRetour(nbJourRetour);
	}

	@Override
	public void voirDetail() {
		System.out.printf("Identifiant : %s\n", super.getIdentifiant());
		System.out.printf("Document : %s\n", getClass().getSimpleName());
		System.out.printf("Nom : %s\n", super.getNom());
		System.out.printf("Titre : %s\n", super.getTitre());
		System.out.printf("Auteur : %s\n", this.auteur);
		System.out.printf("Compositeur : %s\n", this.compositeur);
		System.out.printf("Emprunt autorisé : %s\n", super.isRestriction()? "Non":"Oui");
		if (null != super.getDateRetour()) {
			System.out.printf("Emprunté jusqu'au : %s\n", super.getDateRetour());
		};
	}
	
	// GETTERS ET SETTERS

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getCompositeur() {
		return compositeur;
	}

	public void setCompositeur(String compositeur) {
		this.compositeur = compositeur;
	}

	public void setNbJourRetour(int nbJourRetour) {
		this.nbJourRetour = nbJourRetour;
	}

	public int getNbJourRetour() {
		return nbJourRetour;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cd other = (Cd) obj;
		return Objects.equals(auteur, other.auteur) && Objects.equals(compositeur, other.compositeur)
				&& nbJourRetour == other.nbJourRetour;
	}



}
