import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Fouquet
 *
 */
public class Professeur implements Personne {
	
	private String Nom;
	private String prenom;
	private String identifiant; // identifiant unique à chaque Personne et permattant de l'identifié
	private List<Cours> mesCours = new ArrayList<Cours>(); // Liste de cours donné
	
	
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
	 * @param cours Cours à ajouter
	 * 
	 * Ajout un cours à mesCours (Si le cours n'existe pas déjà dans la liste)
	 */
	protected void ajouterCours(Cours cours) {	
		if (!deja(cours, mesCours)) {
			mesCours.add(cours);
		}
	};

	/**
	 * @param cours Cours avant modification
	 * @param coursModified Cours apres modification
	 * 
	 * Verifie que cours est present dans mesCours, puis remplace celui ci par coursModified
	 */
	protected void modifierCours(Cours cours, Cours coursModified) {
		if (deja(cours, mesCours)) {
			mesCours.set(indexCours(cours, mesCours), coursModified);
		}
	};
	
	/**
	 * @param cours Cours à modifier
	 * 
	 * Verifie que cours est present dans mesCours, puis si c'est le ca le supprime de la liste
	 */
	protected void supprimerCours(Cours cours) {
		if (deja(cours, mesCours)) {
			mesCours.remove(indexCours(cours, mesCours));
		}
	};
	
	/**
	 * @param cours Cours dont on recherche les Etudiants qui y sont inscrit
	 * @return List<Etudiant> La liste de tous les etudiants inscrit au cours passé en paramettre sinon une liste vide
	 * 
	 * Verifie dans la liste de tout les etudiants tous ceux inscrit au cours passé en paramettre et affiche leur information s'il y en a
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
			System.out.printf("Voici la liste des etudiants inscrits au cours %s de %s : \n", cours.getTheme(), cours.getHoraire());
			for (Etudiant etudiant : etudiantsInscrit) {
				etudiant.presenter();
				System.out.println("--------");
			}
		}
		return etudiantsInscrit;

	};
	
	/**
	 * @param etudiant à noter
	 * @param cours à noter
	 * @param note à ajouter
	 * 
	 * Ajoute une note au cours d'un etudiant (si celui ci y est inscrit uniquement)
	 */
	protected void noter(Etudiant etudiant, Cours cours, double note) {
		for (Notes n : etudiant.getNotes()) {
			if(n.getCours().equals(cours)) {
				n.getNotes().add(note);
			}
		}
	};

}
