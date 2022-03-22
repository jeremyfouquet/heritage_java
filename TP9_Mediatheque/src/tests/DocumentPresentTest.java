package tests;

import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class DocumentPresentTest extends TestCase {
	private Mediatheque mediatheque;
	private Membre membre;

    public DocumentPresentTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(System.in));
		mediatheque.createDocuments();
		mediatheque.createMembres();
		membre = Mediatheque.membres.get(0);
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        membre = null;
    }
    
    public void testDocumentPresent1() throws Exception {
		System.out.printf("%s\n", "DocumentPresent1");
		System.out.printf("%s\n", "Cas normal");
        int attendu = Mediatheque.mediatheque.size();
        int resulat = mediatheque.documentPresent().size();
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
    
    public void testDocumentPresent2() throws Exception {
		System.out.printf("%s\n", "DocumentPresent2");
		System.out.printf("%s\n", "Cas normal");
		Document doc = Mediatheque.mediatheque.get(0);
		doc.setDateRetour(doc.retour());
        int attendu = Mediatheque.mediatheque.size() - 1;
        int resulat = mediatheque.documentPresent().size();
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
    
    public void testDocumentPresent3() throws Exception {
		System.out.printf("%s\n", "DocumentPresent3");
		System.out.printf("%s\n", "Cas normal");
		while(Mediatheque.mediatheque.size() > 1) {
			Mediatheque.mediatheque.remove(Mediatheque.mediatheque.size()-1);
		}
        int attendu = Mediatheque.mediatheque.size() - 1;
		Document doc = Mediatheque.mediatheque.get(0);
		mediatheque = new Mediatheque(new Scanner(doc.getIdentifiant()));
		membre.documentARenover(mediatheque);
        int resulat = mediatheque.documentPresent().size();
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
 
}
