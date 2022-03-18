package mediatheque;

import java.util.Objects;

public class Dvd extends Document {
	private String studio;
	private int nbJourRetour = 7;

	/**
	 * @param nom
	 * @param titre
	 * @param restriction
	 * @param studio
	 */
	public Dvd(String nom, String titre, boolean restriction, String studio) {
		super(nom, titre, restriction);
		super.setIdentifiant(this.getClass().getSimpleName()+Integer.toString(Mediatheque.nextId++));
		this.studio = studio;
		super.setNbJourRetour(nbJourRetour);
	}

	@Override
	public void voirDetail() {
		System.out.printf("Identifiant : %s\n", super.getIdentifiant());
		System.out.printf("Document : %s\n", getClass().getSimpleName());
		System.out.printf("Nom : %s\n", super.getNom());
		System.out.printf("Titre : %s\n", super.getTitre());
		System.out.printf("Studio : %s\n", this.studio);
		System.out.printf("Emprunt autorisé : %s\n", super.isRestriction()? "Non":"Oui");
		if (null != super.getDateRetour()) {
			System.out.printf("Emprunté jusqu'au : %s\n", super.getDateRetour());
		};
	}
	
	// GETTERS ET SETTERS

	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
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
		Dvd other = (Dvd) obj;
		return nbJourRetour == other.nbJourRetour && Objects.equals(studio, other.studio);
	}



}
