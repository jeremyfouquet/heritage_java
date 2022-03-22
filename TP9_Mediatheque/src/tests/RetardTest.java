package tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class RetardTest extends TestCase {
	private Mediatheque mediatheque;
	private Membre membre;
	private List<Document> emprunt;
	private Document document;

    public RetardTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(""));
		mediatheque.createDocuments();
		mediatheque.createMembres();
		membre = Mediatheque.membres.get(0);
		emprunt = membre.getEmprunt();
		document = Mediatheque.mediatheque.get(0);
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        membre.setEmprunt(new ArrayList<Document>());
    }
    
    public void testRetard1() throws Exception {
		System.out.printf("%s\n", "Retard1");
		System.out.printf("%s\n", "Cas normal");
	    LocalDate dateRetard = LocalDate.now().plusDays(-1);
		document.setDateRetour(dateRetard);
		emprunt.add(document);
		System.out.printf("Resultat attentu : %s\n", document);
		Document resulat = membre.retard();
		System.out.printf("Resultat final : %s\n", document);
        assertEquals(true, document.equals(resulat));
		System.out.println();
    }
    
    public void testRetard2() throws Exception {
		System.out.printf("%s\n", "Retard2");
		System.out.printf("%s\n", "Cas normal");
		LocalDate dateRetour = document.retour();
		document.setDateRetour(dateRetour);
		emprunt.add(document);
		System.out.printf("Resultat attentu : %s\n", "null");
		Document resulat = membre.retard();
		System.out.printf("Resultat attentu : %s\n", resulat);
        assertEquals(null, resulat);
		System.out.println();
    }
}
