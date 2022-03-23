import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Fouquet
 *
 */
public class Etudiant implements Personne {
	
	private String Nom;
	private String prenom;
	private String identifiant; // identifiant unique à chaque Personne et permattant de l'identifié
	private List<Cours> mesCours = new ArrayList<Cours>(); // liste de cours inscrit
	private List<Notes> notes = new ArrayList<Notes>(); // liste de notes associés au cours inscrit
	
	/**
	 * @param identifiant
	 * @param nom
	 * @param prenom
	 * 
	 * Constructeur
	 */
	public Etudiant(int identifiant, String nom, String prenom) {
		super();
		this.identifiant = "etudiant"+identifiant;
		this.Nom = nom;
		this.prenom = prenom;
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
		return "Etudiant [Nom=" + Nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", coursInscrit="
				+ mesCours + ", notes=" + notes + "]";
	}

	public List<Notes> getNotes() {
		return notes;
	}

	

	/**
	 * @param cours Le Cours à ajouté à mesCours
	 * 
	 * Ajoute le cours passé en paramettre à mesCours (si pas déjà inscrit)
	 * Puis créé une nouvelle instance de Note pour l'ajouté à notes (si pa encore existant)
	 */
	protected void inscrire(Cours cours) {	
		if (!deja(cours, mesCours)) {
			mesCours.add(cours);	
			if(!dejaNotes(cours)) {
				Notes n = new Notes(cours);
				this.notes.add(n);
			}
		}
	};
	
	/**
	 * @param cours Le Cours à supprimer de mesCours
	 * 
	 * Verifi si le Cours existe dans mesours avant de le supprimer de la liste
	 */
	protected void desinscrire(Cours cours) {
		if (deja(cours, mesCours)) {
			mesCours.remove(indexCours(cours, mesCours));
		}
	};

	

	/**
	 * @return List<Notes> Liste de toute les notes de l'etudiant
	 * 
	 * Affiche pour chacun des cours ouvert à recevoir une notes : son theme, son horraire et le tableau des notes associés s'il en a sinon precise "aucune note"
	 */
	protected List<Notes> voirNotes() {
		for (Notes n : notes) {
			System.out.printf("Pour le Cours %s de %s :\n", n.getCours().getTheme(), n.getCours().getHoraire());
			if(n.getNotes().size()>0) {
				int i = 1;
				for (double note: n.getNotes()) {
					System.out.printf("note n°%d : %.1f",i, note);
					if(n.getNotes().size() != i) {
						System.out.printf("%s"," ; ");
					} else {
						System.out.println();
					}
					i++;
				}
			} else {
				System.out.printf("%s\n","Vous n'avez encore aucune note");
			}
			System.out.println("---------");
		}
		return notes;
	};
	
	/**
	 * @return double La moyenne de l'etudiant
	 * 
	 * Calcule et ratourne la moyenne de l'etudiant
	 */
	protected double moyenne() {
		double total = 0;
		int multi = 0;
		for (Notes n : notes) {
			int coeff = n.getCours().getCoefficient();
			for (double m : n.getNotes()) {
				multi += coeff;
				total += (m * coeff);
			}
		}
		double moyenne = multi != 0 ? total / multi : null;
		return moyenne;
	}
	

	/**
	 * @param cours Le Cours recherche dans notes
	 * @return boolean true si il existe la possibilité de noté le cours passé en paramettre sinon false
	 * 
	 * Verifie si le cours passé en paramettre de la methode est déjà présent dans notes
	 */
	protected boolean dejaNotes(Cours cours) {
		boolean dejaNotes = false;
		for( Notes n : notes) { 
			Cours a = n.getCours();
			if (a.equals(cours)) {
				dejaNotes = true;
			}
		}		
		return dejaNotes;
	};
	

}
