package mediatheque;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Document {
	private String identifiant;
	private String nom;
	private String titre;
	private boolean restriction; // si true emprunt non autorisé sinon emprunt auorisé
	private LocalDate dateRetour; // date de retour à null si non emprunté et affecté lors de l'emprunt du document (date du jour de l'emprunt + nbJourRetour) 
	private int nbJourRetour; // nombre de jours dont le document peut être emprunté

	/**
	 * @param nom
	 * @param titre
	 * @param restriction
	 * 
	 * Constructeur
	 */
	public Document(String nom, String titre, boolean restriction) {
		this.nom = nom;
		this.titre = titre;
		this.restriction = restriction;
	}

	/** 
	 * Affiche le detail de Document
	 * Identifiant, Type de Classe, Nom, Titre, Restriction(OUI/NON), Date de retour (Si emprunt uniquement)
	 * 
	 * @author	Jeremy Fouquet
	 */
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

	/** 
	 * Calcule la date de retour : date du jour + nbJourRetour
	 * 
	 * @return LocalDate date de retour maximum autorisé
	 * @author	Jeremy Fouquet
	 */
	public LocalDate retour() {
        LocalDate dateEmprunt = LocalDate.now();
        LocalDate retour = dateEmprunt.plusDays(nbJourRetour);
        return retour;
	}

	/** 
	 * Verifie si la dateRetour est depassé et donc si le document est en retard de rendu
	 * 
	 * @return boolean si en retard renvoi true sinon false
	 * @author	Jeremy Fouquet
	 */
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
