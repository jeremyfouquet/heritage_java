package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class RendreRetardTest extends TestCase {
	private Mediatheque mediatheque;
	private Membre membre;
	private Document emprunt;
    LocalDate dateRetard = LocalDate.now().plusDays(-1);

    public RendreRetardTest(String testMethodName) {
        super(testMethodName);
		
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner("Oui"));
		mediatheque.createDocuments();
		mediatheque.createMembres();
		membre = Mediatheque.membres.get(0);
		emprunt = Mediatheque.mediatheque.get(0);
		emprunt.setDateRetour(dateRetard);
		membre.getEmprunt().add(emprunt);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        membre.setEmprunt(new ArrayList<Document>());
    }
    
    public void testRendreRetard1() throws Exception {
		System.out.printf("%s\n", "RendreRetard1");
		System.out.printf("%s\n", "Cas normal");
		int tailleAvant = membre.getEmprunt().size();
		membre.rendreRetard(mediatheque, emprunt);
		int tailleApres = membre.getEmprunt().size();
		System.out.printf("Resultat attentu : %s\n", tailleAvant);
		System.out.printf("Resultat final : %s\n", tailleApres+1);
        assertEquals(tailleAvant, tailleApres+1);
		System.out.println();
    }
    
    public void testRendreDocument2() throws Exception {
		System.out.printf("%s\n", "RendreRetard2");
		System.out.printf("%s\n", "Cas posant probleme");
		try {
			int tailleAvant = membre.getEmprunt().size();
			Document docErreur = Mediatheque.mediatheque.get(1);
			membre.rendreRetard(mediatheque, docErreur);
			int tailleApres = membre.getEmprunt().size();
			System.out.printf("Resultat attentu : %s\n", tailleAvant);
			System.out.printf("Resultat final : %s\n", tailleApres);
	        assertEquals(tailleAvant, tailleApres);
		} catch (NullPointerException ise) {
		    fail("Une exception de type NullPointerException aurait du etre levee");
		} catch (AssertionFailedError ise) {
		    fail("Une exception de type AssertionFailedError aurait du etre levee");
		}
		System.out.println();
    }
}
