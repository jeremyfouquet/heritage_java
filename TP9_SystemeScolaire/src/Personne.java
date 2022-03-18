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
	 * Ecrit en console le nom, le prenom ainsi que l'id du professeur
	 */
	public default void presenter() {
		System.out.printf("Presentation du professeur id : %s \n",  this.getIdentifiant());
		System.out.printf("Nom : %s \n", this.getNom());
		System.out.printf("Prenom : %s \n", this.getPrenom());
	}

	/**
	 * Ecrit en console le detail de chaque cours présent dans la liste de mesCours de Personne (Etudiant/Professeur)
	 */
	public default List<Cours> voirCours() {
		String typeCours = this.getClass().getName() == "Etudiant"? "inscrit" : "propose";
		System.out.printf("Voici la liste des cours %s : \n", typeCours);
		for (Cours a : this.getCours()) {
			System.out.printf("Cours : %s, lieu : %s, horaire : %s, duree : %d min, coeff: %d, type : %s \n", a.getTheme(), a.getLieu(), a.getHoraire(), a.getDuree(), a.getCoefficient(), a.getType());
		}
		return this.getCours();
	};

	/**
	 * @param cours
	 * @return
	 * 
	 * Retourne l'index du cours passé en paramettre
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
	 * @param cours
	 * @return
	 * 
	 * Verifie si le cours passé en 1er paramettre est déjà présent dans mesCours passé en 2eme paramettre
	 * Si oui alors retourne TRUE sinon FALSE
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

	/**
	 * @return
	 */
	public String getIdentifiant();

	/**
	 * @param identifiant
	 */
	public void setIdentifiant(String identifiant);

	/**
	 * @return
	 */
	public String getNom();

	/**
	 * @param nom
	 */
	public void setNom(String nom);

	/**
	 * @return
	 */
	public String getPrenom();

	/**
	 * @param prenom
	 */
	public void setPrenom(String prenom);

	/**
	 * @return
	 */
	public List<Cours> getCours();
	
}
