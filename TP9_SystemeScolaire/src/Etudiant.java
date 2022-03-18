import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Fouquet
 *
 */
public class Etudiant implements Personne {
	
	private String Nom;
	private String prenom;
	private String identifiant;
	private List<Cours> mesCours = new ArrayList<Cours>();
	private List<Notes> notes = new ArrayList<Notes>();
	
	/**
	 * @param identifiant
	 * @param nom
	 * @param prenom
	 * 
	 * Constructeur
	 */
	public Etudiant(int identifiant, String nom, String prenom) {
		super();
		this.setIdentifiant("etudiant"+identifiant);
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
		return "Etudiant [Nom=" + Nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", coursInscrit="
				+ mesCours + ", notes=" + notes + "]";
	}

	/**
	 * @return
	 * 
	 * Getter Notes
	 */
	public List<Notes> getNotes() {
		return notes;
	}

	/**
	 * @param cours
	 * 
	 * SI il n'est pa déjà inscrit au cours alors on ajoute le cours passé en paramettre de la fonction
	 * à la liste des cours inscrit
	 * ET on ajoute également une nouvelle instance de Notes lié à ce cours
	 * si ce n'est pas déjà fait
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
	 * @param cours
	 * 
	 * SI il est inscrit au cours passé en paramettre on le desinscrit c'est à dire on le supprimme
	 * de la liste coursInscrit
	 */
	protected void desinscrire(Cours cours) {
		if (deja(cours, mesCours)) {
			mesCours.remove(indexCours(cours, mesCours));
		}
	};

	
	/**
	 * Ecrit en console le nom de chaque cours présent dans le relevé de notes ainsi que les notes associés
	 * s'il y en a !
	 */
	protected List<Notes> voirNotes() {
		System.out.printf("Voici la liste des notes : \n");
		for (Notes n : notes) {
			System.out.printf("Pour le Cours %s de %s, notes : %s \n", n.getCours().getTheme(), n.getCours().getHoraire(), n.getNotes().size()>0 ? n.getNotes() : "aucune note");
		}
		return notes;
	};
	
	/**
	 * @return
	 * 
	 * Retourne la moyenne des notes de l'etudiant sinon retourn null si aucun note
	 */
	protected double moyenne() {
		double total = 0;
		int multi = 0;
		for (Notes n : notes) {
			for (double m : n.getNotes()) {
				multi ++;
				total += m;
			}
		}
		double moyenne = multi != 0 ? total / multi : null;
		return moyenne;
	}
	
	/**
	 * @param cours
	 * @return
	 * 
	 * Verifie si le cours passé en paramettre de la methode est déjà présent dans notes
	 * Si oui alors retourne TRUE sinon FALSE
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
