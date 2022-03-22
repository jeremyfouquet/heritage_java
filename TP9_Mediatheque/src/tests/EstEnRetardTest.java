package tests;

import java.time.LocalDate;

import junit.framework.TestCase;
import mediatheque.Categorie;
import mediatheque.Document;
import mediatheque.Livre;

public class EstEnRetardTest extends TestCase {
	private Document document;

    public EstEnRetardTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		document = new Livre("Dune", "Dune", false, "Herbert", "Pocket", Categorie.Roman);
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        document = null;
    }
    
    public void testEstEnRetard1() throws Exception {
		System.out.printf("%s\n", "EstEnRetard1");
		System.out.printf("%s\n", "Cas normal");
        LocalDate dateRetour = document.retour();
        document.setDateRetour(dateRetour);
        boolean attendu = false;
        boolean resulat = document.estEnRetard();
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
    
    public void testEstEnRetard2() throws Exception {
		System.out.printf("%s\n", "EstEnRetard2");
		System.out.printf("%s\n", "Cas normal");
        LocalDate dateEmprunt = LocalDate.now();
        LocalDate dateRetour = dateEmprunt.plusDays(-1);
        document.setDateRetour(dateRetour);
        boolean attendu = true;
        boolean resulat = document.estEnRetard();
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
    
    public void testEstEnRetard3() throws Exception {
		System.out.printf("%s\n", "EstEnRetard3");
		System.out.printf("%s\n", "Cas posant probleme");
		try {
	        boolean attendu = false;
	        assertEquals(attendu, document.estEnRetard());
		} catch (NullPointerException e) {
			System.out.printf("%s\n", "Une exception de type NullPointerException aurait du etre levee");
		}
		System.out.println();
    }
}
