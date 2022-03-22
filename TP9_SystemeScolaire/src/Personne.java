import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Fouquet
 *
 *	Le Choix d'une interface est une bonne utilisation ici car elle permet d'utilisé des méthodes par défauts
 *  et d'avoir la liste de tous les etudiants accessible par les professeurs grace au mot cles static
 *  Cependant ce n'est pas selon moi le meileur choix possible.
 *  En effet il n'est pas possible dans une interface d'avoir des variables alors qu'ici
 *  les nom, prenom et identifiant et mesCours sont des variables communes dans Etudiant et Professeur.
 *  Et les getters et setters sont ici uniquement des prototypes de part cette limite, alors qu'ils sont
 *  identique dans les 2 classes fille et pouraient très facilement être initialisé dans la classe mère.
 *  Il aurrait donc mieux vallu utilisé une classe abstraite cela aurait permis de déclarer les variables
 *  nom, prenom et identifiant et mesCours dans la class mere Personne.
 */
public interface Personne {

	public static List<Personne> listPersonne = new ArrayList<Personne>();

	/**
	 * Affiche les attributs Identifiant, Nom et Prenom
	 */
	public default void presenter() {
		System.out.printf("Identifiant : %s \n",  this.getIdentifiant());
		System.out.printf("Nom : %s \n", this.getNom());
		System.out.printf("Prenom : %s \n", this.getPrenom());
	}

	/**
	 * @return List<Cours> Liste des cours suivi (si Etudiant) ou Liste des cours donné (si Professeur)
	 * 
	 * Affiche le detail de chacun des cours présent dans la variable mesCours
	 */
	public default List<Cours> voirCours() {
		String typeCours = this.getClass().getName() == "Etudiant"? "inscrit" : "propose";
		for (Cours a : this.getCours()) {
			System.out.printf("Cours %s : %s\n", typeCours, a.getTheme());
			System.out.printf("Lieu : %s\n", a.getLieu());
			System.out.printf("Horaire : %s\n", a.getHoraire());
			System.out.printf("Duree : %d min\n", a.getDuree());
			System.out.printf("coeff: %d\n", a.getCoefficient());
			System.out.printf("type : %s\n", a.getType());
			System.out.println("---------");
		}
		return this.getCours();
	};

	/**
	 * @param cours Le Cours recherché
	 * @param mesCours La liste dans laquel on recherche le Cours
	 * @return int L'index du Cours recherché ou -1 si aucun cours trouvé
	 * 
	 * Cherche un Cours dans une liste de Cours et retourne son Index dans cette liste Sinon retourne -1
	 */
	public default int indexCours(Cours cours, List<Cours> mesCours) {
		int index = -1;
		for(int i = 0; index == -1 && i < mesCours.size(); i++) { 
			Cours a = mesCours.get(i);
			if (a.equals(cours)) {
				index = i;
			}
		}		
		return index;
	};


	/**
	 * @param cours Le Cours recherché
	 * @param mesCours La liste dans laquel on recherche le Cours
	 * @return boolean true si Cours existe déjà sinon false
	 * 
	 * Verifie si un Cours existe déjà dans une liste de Cours
	 */
	public default boolean deja(Cours cours, List<Cours> mesCours) {
		boolean deja = false;
		for( Cours a : mesCours) { 
			if (a.equals(cours)) {
				deja = true;
			}
		}		
		return deja;
	};

//	GETTERS AND SETTERS

	public String getIdentifiant();

	public void setIdentifiant(String identifiant);

	public String getNom();

	public void setNom(String nom);

	public String getPrenom();

	public void setPrenom(String prenom);

	public List<Cours> getCours();
	
}
