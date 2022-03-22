import java.util.List;
import java.util.Random;

public class Main {
	public static int nextIdEtud = 0;
	public static int nextIdProf = 0;

	public static void main(String[] args) {
		//TESTS PROFESSEUR
		testAjouterCours("ajouterCours");
		reset();
		testModifierCours("modifierCours");
		reset();
		testSupprimerCours("supprimerCours");
		reset();
		testVoirEtudiant("voirEtudiant");
		reset();
		testNoter("noter");
		reset();
		
		//TESTS ETUDIANT
		testVoirCours("voirCours");
		reset();
		testInscrire("inscrire");
		reset();
		testDesinscrire("desinscrire");
		reset();
		testVoirNotes("voirNotes");
		reset();
		testMoyenne("moyenne");
		reset();
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
		System.out.printf("%s\n", "-----------------------------------");
	}
	
	public static void testModifierCours(String testMethodName) {
		Professeur professeur = mockProfesseur();
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		professeur.ajouterCours(mockCours(0));
		Cours coursAvant = mockCours(0);
		Cours coursApres = mockCours(4);
		System.out.printf("Professeur modifie son cours :\n%s \nEN\n%s\n",coursAvant, coursApres);
		professeur.modifierCours(coursAvant, coursApres);
		boolean conditionTestSuccess = professeur.getCours().get(0).equals(coursApres);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);	
		System.out.printf("\n%s\n", "CAS ANORMAL");
		reset();
		professeur = mockProfesseur();	
		int nbCours = professeur.getCours().size();
		System.out.printf("%s\n","Professeur ne peut pas modifier son cours car il n'en as aucun");
		professeur.modifierCours(coursAvant, coursApres);
		conditionTestSuccess = nbCours == 0;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("%s\n", "-----------------------------------");
		}
	
	public static void testSupprimerCours(String testMethodName) {
		Professeur professeur = mockProfesseur();
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		professeur.ajouterCours(mockCours(0));
		System.out.printf("%s\n", "Professeur supprime son cours");
		int nbCoursAvant = professeur.getCours().size();
		professeur.supprimerCours(mockCours(0));
		int nbCoursApres = professeur.getCours().size();
		boolean conditionTestSuccess = nbCoursAvant == ( nbCoursApres + 1);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);	
		System.out.printf("\n%s\n", "CAS ANORMAL");
		reset();
		professeur = mockProfesseur();
		int nbCours = professeur.getCours().size();
		System.out.printf("%s\n","Professeur ne peut pas supprimer son cours car il n'en as aucun");
		professeur.supprimerCours(mockCours(0));
		conditionTestSuccess = nbCours == 0;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("\n%s\n", "CAS ANORMAL");
		reset();
		professeur = mockProfesseur();
		professeur.ajouterCours(mockCours(0));
		nbCoursAvant = professeur.getCours().size();
		System.out.printf("%s\n","Professeur ne peut pas supprimer son cours car il n'existe pas dans sa liste de cours");
		professeur.supprimerCours(mockCours(1));
		nbCoursApres = professeur.getCours().size();
		conditionTestSuccess = nbCoursAvant == nbCoursApres;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("%s\n", "-----------------------------------");
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
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Professeur veut voir ses %d étudiants inscrits au cours\n", nbCours1);
		for (int i = 0; i < nbCours1; i++) {
			e1.inscrire(cours1);
			e2.inscrire(cours1);
			e3.inscrire(cours1);
		}
		boolean conditionTestSuccess = professeur.voirEtudiants(cours1).size() == nbCours1;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Professeur veut voir ses %d étudiants inscrits au cours\n", nbCours2);
		for (int i = 0; i < nbCours2; i++) {
			e1.inscrire(cours2);
			e2.inscrire(cours2);
			e3.inscrire(cours2);
			e4.inscrire(cours2);
		}
		conditionTestSuccess = professeur.voirEtudiants(cours2).size() == nbCours2;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("%s\n", "-----------------------------------");	
	}
	
	public static void testNoter(String testMethodName) {
		Cours cours = mockCours(0);
		double note = 15.5;
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		professeur.ajouterCours(cours);
		etudiant.inscrire(cours);
		professeur.noter(etudiant, cours, note);
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Professeur note %.1f \n",note);
		boolean conditionTestSuccess = etudiant.getNotes().get(0).getNotes().get(0).equals(note);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Professeur ajoute 2 autres notes %.1f et %.1f \n",note - 2, note + 2);
		professeur.noter(etudiant, cours, note+2);
		professeur.noter(etudiant, cours, note-2);
		conditionTestSuccess = etudiant.getNotes().get(0).getNotes().size() == 3;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("\n%s\n", "CAS ANORMAL");
		reset();
		etudiant = mockEtudiant();
		System.out.printf("Professeur ne peut pas noter l'Etudiant car celui ci n'est pas inscrit au cours \n");
		professeur.noter(etudiant, cours, note);
		int nbNotes = etudiant.getNotes().size();
		conditionTestSuccess = nbNotes == 0;
		System.out.printf("%b\n", conditionTestSuccess);	
		System.out.printf("%s\n", "-----------------------------------");	
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
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Etudiant veut voir la liste des %d cours où il est inscrit\n", nbCours);
		boolean conditionTestSuccess = etudiant.voirCours().size() == nbCours;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		reset();
		etudiant = mockEtudiant();
		System.out.printf("\n%s\n", "CAS ANORMAL");
		System.out.printf("Etudiant ne peut pas voir ses cours car il n'est inscrit à aucun cours\n");
		conditionTestSuccess = etudiant.voirCours().size() == 0;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("%s\n", "-----------------------------------");
	}
	
	public static void testInscrire(String testMethodName) {
		Cours cours = mockCours(0);
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		professeur.ajouterCours(cours);
		etudiant.inscrire(cours);
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Etudiant inscrit au cours :\n%s\n", cours);
		boolean conditionTestSuccess = etudiant.getCours().get(0).equals(cours);
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("\n%s\n", "CAS ANORMAL");
		System.out.printf("Etudiant ne peut se reinscrire au cours dont il est déjà inscrit\n");
		etudiant.inscrire(cours);
		conditionTestSuccess = etudiant.getCours().size() == 1;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("%s\n", "-----------------------------------");
	}

	public static void testDesinscrire(String testMethodName) {
		Cours cours = mockCours(0);
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		professeur.ajouterCours(cours);
		etudiant.inscrire(cours);
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Etudiant se desinscrit du cours :\n%s\n", cours);
		int nbCoursAvant = etudiant.getCours().size();
		etudiant.desinscrire(cours);
		int nbCoursApres = etudiant.getCours().size();
		boolean conditionTestSuccess = nbCoursAvant == nbCoursApres + 1;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("\n%s\n", "CAS ANORMAL");
		System.out.printf("Etudiant ne peut pas se desinscrit d'un cours dont il n'est pas ou plus inscrit\n");
		nbCoursAvant = etudiant.getCours().size();
		etudiant.desinscrire(cours);
		nbCoursApres = etudiant.getCours().size();
		conditionTestSuccess = nbCoursAvant == nbCoursApres;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("%s\n", "-----------------------------------");
	}
	
	public static void testVoirNotes(String testMethodName) {
		Cours cours = mockCours(0);
		Cours cours2 = mockCours(1);
		double note = 15.5;
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		professeur.ajouterCours(cours);
		professeur.ajouterCours(cours2);
		etudiant.inscrire(cours);
		etudiant.inscrire(cours2);
		professeur.noter(etudiant, cours, note);
		professeur.noter(etudiant, cours, note + 2);
		professeur.noter(etudiant, cours, note - 2);
		professeur.noter(etudiant, cours2, note + 3);
		professeur.noter(etudiant, cours2, note - 3);
		professeur.noter(etudiant, cours2, note);
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Etudiant veut voir ses notes :\n");
		List<Notes> notes = etudiant.voirNotes();
		boolean conditionTestSuccess = (
				notes.get(0).getNotes().size() == 3
				&&
				notes.get(1).getNotes().size() == 3
		);	
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("%s\n", "-----------------------------------");
	}
	
	public static void testMoyenne(String testMethodName) {
		double note = 10.0;
		int coeff1 = 4;
		int	coeff2 = 2;
		double moyenneAttendu = (double) ((note*coeff1)+((note+5)*coeff1)+((note-5)*coeff2))/((coeff1*2)+coeff2);
		Professeur professeur = mockProfesseur();
		Etudiant etudiant = mockEtudiant();
		int nbCoursAjouté = 3;
		for (int i = 0; i < nbCoursAjouté; i++) {
			professeur.ajouterCours(mockCours(i));
			etudiant.inscrire(mockCours(i));
			professeur.noter(etudiant, mockCours(i), i == 0? note : i == 1? note + 5 : note - 5);
		}		
		System.out.printf("TEST : %s \n",testMethodName);
		System.out.printf("\n%s\n", "CAS NORMAL");
		System.out.printf("Etudiant veut voir sa moyenne qui devrait être %.1f\n", moyenneAttendu);
		boolean conditionTestSuccess = etudiant.moyenne() == moyenneAttendu;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.printf("%s\n", "-----------------------------------");
	}
// RESET
	public static void reset() {
		while(Personne.listPersonne.size() > 0) {
			Personne.listPersonne.remove(Personne.listPersonne.size() - 1);
		}
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
