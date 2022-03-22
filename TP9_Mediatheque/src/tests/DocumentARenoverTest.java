package tests;

import java.util.ArrayList;
import java.util.Scanner;

import exception.MonException;
import junit.framework.TestCase;
import mediatheque.Categorie;
import mediatheque.Document;
import mediatheque.Livre;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class DocumentARenoverTest extends TestCase {
	private Mediatheque mediatheque;
	private Document document;
	private Membre membre;

    public DocumentARenoverTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		document = new Livre("Dune", "Dune", false, "Herbert", "Pocket", Categorie.Roman);
		mediatheque = new Mediatheque(new Scanner(document.getIdentifiant()));
		Mediatheque.mediatheque = new ArrayList<Document>();
		Mediatheque.mediatheque.add(document);
		mediatheque.createMembres();
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        membre = null;
    }
    
    public void testDocumentARenover1() throws Exception {
		System.out.printf("%s\n", "DocumentARenover1");
		System.out.printf("%s\n", "Cas normal");
		membre = Mediatheque.membres.get(0);
        membre.documentARenover(mediatheque);
        Document attendu = document;
        Document resulat = Mediatheque.renovation.get(0);
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(true, attendu.equals(resulat));
		System.out.println();
    }
    
    public void testDocumentARenover2() throws Exception {
		System.out.printf("%s\n", "DocumentARenover2");
		System.out.printf("%s\n", "Cas anormal");
		try {
			membre = Mediatheque.membres.get(1);
	        membre.documentARenover(mediatheque);
	        membre.documentARenover(mediatheque);
	        Document attendu = Mediatheque.renovation.get(0);
	        Document resulat = document;
	        assertEquals(true, attendu.equals(resulat));
		} catch(MonException e) {
			System.out.printf("%s\n", e.getMessage());
		} finally {
			System.out.println();
		}
    }
 
}
