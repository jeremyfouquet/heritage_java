package tests;

import java.time.LocalDate;

import junit.framework.TestCase;
import mediatheque.Categorie;
import mediatheque.Document;
import mediatheque.Livre;

public class RetourTest extends TestCase {
	private Document document;

    public RetourTest(String testMethodName) {
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
    
    public void testRetour() throws Exception {
		System.out.printf("%s\n", "Retour");
		System.out.printf("%s\n", "Cas normal");
        LocalDate dateEmprunt = LocalDate.now();
        LocalDate attendu = dateEmprunt.plusDays(30);
		System.out.printf("Resultat attentu : %s\n", attendu);
        LocalDate resulat = document.retour();
		System.out.printf("Resultat final : %s\n", attendu);
        assertEquals(true, attendu.equals(resulat));
		System.out.println();
    }
}
