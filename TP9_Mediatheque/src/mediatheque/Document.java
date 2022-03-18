package mediatheque;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Document {
	private String identifiant;
	private String nom;
	private String titre;
	private boolean restriction;
	private LocalDate dateRetour;
	private int nbJourRetour;

	/**
	 * @param nom
	 * @param titre
	 * @param restriction
	 */
	public Document(String nom, String titre, boolean restriction) {
		this.nom = nom;
		this.titre = titre;
		this.restriction = restriction;
	}

	public void voirDetail() {
		System.out.printf("Identifiant : %s\n", identifiant);
		System.out.printf("Document : %s\n", getClass().getSimpleName());
		System.out.printf("Nom : %s\n", nom);
		System.out.printf("Titre : %s\n",titre);
		System.out.printf("Emprunt autorisé : %s\n", restriction? "Non":"Oui");
		if (dateRetour != null) {
			System.out.printf("Déjà emprunté, retour prévu avant le : %s\n", dateRetour);
		};
	}
	
	public LocalDate retour() {
        LocalDate dateEmprunt = LocalDate.now();
        LocalDate retour = dateEmprunt.plusDays(nbJourRetour);
        return retour;
	}
	
	public boolean estEnRetard() {
        LocalDate now = LocalDate.now();
        boolean estEnRetard = now.isAfter(dateRetour);
        return estEnRetard;
	}
	
	// GETTERS ET SETTERS

	public int getNbJourRetour() {
		return nbJourRetour;
	}

	public void setNbJourRetour(int nbJourRetour) {
		this.nbJourRetour = nbJourRetour;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public boolean isRestriction() {
		return restriction;
	}

	public void setRestriction(boolean restriction) {
		this.restriction = restriction;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		return Objects.equals(dateRetour, other.dateRetour) && Objects.equals(identifiant, other.identifiant)
				&& nbJourRetour == other.nbJourRetour && Objects.equals(nom, other.nom)
				&& restriction == other.restriction && Objects.equals(titre, other.titre);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [identifiant=" + identifiant + ", nom=" + nom + ", titre=" + titre + ", restriction="
				+ restriction + ", dateRetour=" + dateRetour + "]";
	}

}
