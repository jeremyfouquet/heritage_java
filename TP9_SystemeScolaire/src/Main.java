import java.util.List;
import java.util.Random;

public class Main {
	public static int nextIdEtud = 0;
	public static int nextIdProf = 0;

	public static void main(String[] args) {
		//TESTS PROFESSEUR
		testAjouterCours("ajouterCours");
		testModifierCours("modifierCours");
		testSupprimerCours("supprimerCours");
		testVoirEtudiant("voirEtudiant");
		testNoter("noter");
		
		//TESTS ETUDIANT
		testVoirCours("voirCours");
		testInscrire("inscrire");
		testDesinscrire("desinscrire");
		testVoirNotes("voirNotes");
		testMoyenne("moyenne");
	}
	
// TESTS PROFESSEUR

	public static void testAjouterCours(String testMethodName) {
		Professeur professeur = mockProfesseur();
		int nbCoursAjouté = 3;
		System.out.printf("TEST : %s \nProfesseur ajoute %d cours \n",testMethodName, nbCoursAjouté);
		for (int i = 0; i < nbCoursAjouté; i++) {
			professeur.ajouterCours(mockCours(i));
		}
		boolean conditionTestSuccess = professeur.getCours().size() == nbCoursAjouté;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testModifierCours(String testMethodName) {
		Professeur professeur = mockProfesseur();
		professeur.ajouterCours(mockCours(0));
		Cours coursAvant = mockCours(0);
		Cours coursApres = mockCours(4);
		System.out.printf("TEST : %s \nProfesseur modifie son cours %s en %s \n",testMethodName, coursAvant, coursApres);
		professeur.modifierCours(coursAvant, coursApres);
		boolean conditionTestSuccess = (
			professeur.getCours().size() == 1
			&&
			professeur.getCours().get(0).equals(coursApres)
		);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);	
		System.out.println();
	}
	
	public static void testSupprimerCours(String testMethodName) {
		Professeur professeur = mockProfesseur();
		professeur.ajouterCours(mockCours(0));
		System.out.printf("TEST : %s \nProfesseur supprime son cours unique cours \n", testMethodName);
		professeur.supprimerCours(mockCours(0));
		boolean conditionTestSuccess = professeur.getCours().size() == 0;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testVoirEtudiant(String testMethodName) {
		Cours cours1 = mockCours(0);
		Cours cours2 = mockCours(1);
		Professeur professeur = mockProfesseur();
		Etudiant e1 = mockEtudiant();
		Etudiant e2 = mockEtudiant();
		Etudiant e3 = mockEtudiant();
		Etudiant e4 = mockEtudiant();
		professeur.ajouterCours(cours1);
		professeur.ajouterCours(cours2);
		int nbCours1 = 3;
		int nbCours2 = 4;
		System.out.printf("TEST : %s \nProfesseur veut voir les %d étudiants du %s puis les %d étudiants du %s \n", testMethodName, nbCours1, cours1, nbCours2, cours2);
		for (int i = 0; i < nbCours1; i++) {
			e1.inscrire(cours1);
			e2.inscrire(cours1);
			e3.inscrire(cours1);
		}
		for (int i = 0; i < nbCours2; i++) {
			e1.inscrire(cours2);
			e2.inscrire(cours2);
			e3.inscrire(cours2);
			e4.inscrire(cours2);
		}
		boolean conditionTestSuccess = (
			professeur.voirEtudiants(cours1).size() == nbCours1
			&&
			professeur.voirEtudiants(cours2).size() == nbCours2
		);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testNoter(String testMethodName) {
		Cours cours = mockCours(0);
		double note = 15.5;
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		professeur.ajouterCours(cours);
		etudiant.inscrire(cours);
		professeur.noter(etudiant, cours, note);
		System.out.printf("TEST : %s \nProfesseur note %.1f \n",testMethodName, note);
		
		boolean conditionTestSuccess = etudiant.getNotes().get(0).getNotes().get(0).equals(note);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}

// TESTS ETUDIANT
	
	public static void testVoirCours(String testMethodName) {
		int nbCours = 3;
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		for (int i = 0; i < nbCours; i++) {
			professeur.ajouterCours(mockCours(i));
			etudiant.inscrire(mockCours(i));
		}
		System.out.printf("TEST : %s \nEtudiant veut voir la liste de ses %d cours inscrit \n", testMethodName, nbCours);
		
		boolean conditionTestSuccess = etudiant.voirCours().size() == nbCours;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testInscrire(String testMethodName) {
		Cours cours = mockCours(0);
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		professeur.ajouterCours(cours);
		etudiant.inscrire(cours);
		System.out.printf("TEST : %s \nEtudiant inscrit au %s \n", testMethodName, cours);
		boolean conditionTestSuccess = (
				etudiant.getCours().size() == 1
				&&
				etudiant.getCours().get(0).equals(cours)
		);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}

	public static void testDesinscrire(String testMethodName) {
		Cours cours = mockCours(0);
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		professeur.ajouterCours(cours);
		etudiant.inscrire(cours);
		etudiant.desinscrire(cours);
		System.out.printf("TEST : %s \nEtudiant desinscrit au %s \n", testMethodName, cours);
		boolean conditionTestSuccess = etudiant.getCours().size() == 0;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testVoirNotes(String testMethodName) {
		Cours cours = mockCours(0);
		double note = 15.5;
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		professeur.ajouterCours(cours);
		etudiant.inscrire(cours);
		professeur.noter(etudiant, cours, note);
		System.out.printf("TEST : %s \nEtudiant veut voir son unique note %.1f \n", testMethodName, note, cours);
		List<Notes> notes = etudiant.voirNotes();
		boolean conditionTestSuccess = (
				notes.get(0).getNotes().get(0).equals(note)
				&&
				notes.size() == 1
				&&
				notes.get(0).getNotes().size() == 1
		);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testMoyenne(String testMethodName) {
		double note = 10.0;
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		int nbCoursAjouté = 3;
		for (int i = 0; i < nbCoursAjouté; i++) {
			professeur.ajouterCours(mockCours(i));
			etudiant.inscrire(mockCours(i));
			professeur.noter(etudiant, mockCours(i), i == 0? note : i == 1? note + 5 : note - 5);
		}		
		System.out.printf("TEST : %s \nEtudiant veut voir sa moyenne qui doit être de %.1f \n", testMethodName, note);
		boolean conditionTestSuccess = etudiant.moyenne() == note;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
// MOCKS

	public static Etudiant mockEtudiant() {
		Etudiant e = new Etudiant(nextIdEtud++, mockName(), mockName());
		return e;
	}
	public static Professeur mockProfesseur() {
		Professeur p = new Professeur(nextIdProf++, mockName(), mockName());
		return p;
	}

	// SOURCE
	// https://www.programiz.com/java-programming/examples/generate-random-string
	public static String mockName() {
		// create a string of all characters
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    // create random string builder
	    StringBuilder sb = new StringBuilder();
	    // create an object of Random class
	    Random random = new Random();
	    // specify length of random string
	    int length = 4;
	    for(int i = 0; i < length; i++) {
	      // generate random index number
	      int index = random.nextInt(alphabet.length());
	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);
	      // append the character to string builder
	      sb.append(randomChar);
	    }
	    String name = sb.toString();
	    return name.toLowerCase();
	}
	public static Cours mockCours(int n) {
		Cours cours;
		return switch(n) {
			case 0 :
			cours = new Cours("Amphi 1", "21/03/2022 10:00", 120, "JAVA", 4);
			yield cours;
			case 1 :
			cours = new Cours("Amphi 2", "21/03/2022 12:00", 90, "PYTHON", 4);
			yield cours;
			case 2 :
			cours = new Cours("Amphi 3", "21/03/2022 14:00", 120, "C++", 2);
			yield cours;
			case 3 :
			cours = new Cours("Amphi 4", "21/03/2022 15:00", 90, "JS", 2);
			yield cours;
			default:
			cours = new Cours("Amphi 6", "21/03/2022 10:00", 120, "JAVA", 4);
			yield cours;
		};
	}
	

	
	

}
