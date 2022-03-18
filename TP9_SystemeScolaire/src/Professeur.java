import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Fouquet
 *
 */
public class Professeur implements Personne {
	
	private String Nom;
	private String prenom;
	private String identifiant;
	private List<Cours> mesCours = new ArrayList<Cours>();
	
	
	/**
	 * @param identifiant
	 * @param nom
	 * @param prenom
	 * 
	 * Constructeur
	 */
	public Professeur(int identifiant, String nom, String prenom) {
		super();
		this.setIdentifiant("professeur"+identifiant);
		this.setNom(nom);
		this.setPrenom(prenom);
		listPersonne.add(this);
	}

	@Override
	public String getIdentifiant() {
		return identifiant;
	}

	@Override
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	@Override
	public String getNom() {
		return Nom;
	}

	@Override
	public void setNom(String nom) {
		Nom = nom;
	}

	@Override
	public String getPrenom() {
		return prenom;
	}

	@Override
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public List<Cours> getCours() {
		return mesCours;
	}

	@Override
	public String toString() {
		return "Professeur [Nom=" + Nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", coursPropose="
				+ mesCours + "]";
	}
	
	
	/**
	 * @param cours
	 * 
	 * Si le cours passé en paramettre n'est pas encore dans mesCours alors il est ajouté à la liste
	 */
	protected void ajouterCours(Cours cours) {	
		if (!deja(cours, mesCours)) {
			mesCours.add(cours);
		}
	};

	/**
	 * @param cours
	 * @param coursModified
	 * 
	 * Si le cours passé en 1er paramettre est bien présent dans mesCours alors il est
	 * remplacé par le cours passé en 2eme paramettre
	 */
	protected void modifierCours(Cours cours, Cours coursModified) {
		if (deja(cours, mesCours)) {
			mesCours.set(indexCours(cours, mesCours), coursModified);
		}
	};
	
	/**
	 * @param cours
	 * 
	 * Si le cours passé en paramettre est bien présent dans mesCours alors il est supprimé
 	 * de la liste
	 */
	protected void supprimerCours(Cours cours) {
		if (deja(cours, mesCours)) {
			mesCours.remove(indexCours(cours, mesCours));
		}
	};
	
	/**
	 * @param cours
	 * 
	 * On créé une nouvelle liste vide
	 * Puis pour chaque etudiant inscrit au cours passé en paramettre on l'ajoute à cette liste
	 * Si la liste contient au moins 1 etudiant on affiche chacun d'eu grace à la methode presenter
	 */
	protected List<Etudiant> voirEtudiants(Cours cours) {
		List<Etudiant> etudiantsInscrit = new ArrayList<Etudiant>();
		for (Personne personne : listPersonne) {
			boolean inscrit = false;
			for (Cours c : personne.getCours()) {
				if (c.equals(cours) && personne.getClass().getName() == "Etudiant") {
					inscrit = true;
				}
			}
			if(inscrit) {
				etudiantsInscrit.add((Etudiant)personne);
			}
		}
		if(etudiantsInscrit.size() > 0) {
			System.out.printf("Voici la liste des etudiant inscrit au cours %s de %s : \n", cours.getTheme(), cours.getHoraire());
			for (Etudiant etudiant : etudiantsInscrit) {
				System.out.println(etudiant.toString());
				etudiant.presenter();
			}
		}
		return etudiantsInscrit;

	};
	
	/**
	 * @param etudiant
	 * @param cours
	 * @param note
	 * 
	 * 
	 * Si l'étudiant passé en 1er paramettre est inscrite au cours passé en 2eme paramettre
	 * on lui ajoute à ce cours la note passé en 3eme paramettre
	 */
	protected void noter(Etudiant etudiant, Cours cours, double note) {
		for (Notes n : etudiant.getNotes()) {
			if(n.getCours().equals(cours)) {
				n.getNotes().add(note);
			}
		}
	};

}
