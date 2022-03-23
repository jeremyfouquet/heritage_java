package tests;

import java.util.ArrayList;
import java.util.Scanner;

import exception.MonException;
import junit.framework.TestCase;
import mediatheque.Document;
import mediatheque.Mediatheque;

public class GetDocumentByIdTest extends TestCase {
	private Mediatheque mediatheque;

    public GetDocumentByIdTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(System.in));
		mediatheque.createDocuments();
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        Mediatheque.mediatheque = new ArrayList<Document>();
    }
    
    public void testGetDocumentById1() throws Exception {
		System.out.printf("%s\n", "GetDocumentById1");
		System.out.printf("%s\n", "Cas normal");
		Document attendu = Mediatheque.mediatheque.get(0);
        Document resulat = mediatheque.getDocumentById(attendu.getIdentifiant());
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(true, attendu.equals(resulat));
		System.out.println();
    }
    
    public void testGetDocumentById2() throws Exception {
		System.out.printf("%s\n", "GetDocumentById2");
		System.out.printf("%s\n", "Cas posant probleme");
		try {
	        assertEquals(true, mediatheque.getDocumentById("Inconnu") == null);
		} catch (MonException e) {
		    System.out.printf("%s\n", e.getMessage());
		} finally {
			System.out.println();
		}
    }
    
    public void testGetDocumentById3() throws Exception {
		System.out.printf("%s\n", "GetDocumentById3");
		System.out.printf("%s\n", "Cas anormal");
		try {
			Document attendu = Mediatheque.mediatheque.get(0);
			Mediatheque.mediatheque = new ArrayList<Document>();
	        assertEquals(true, mediatheque.getDocumentById(attendu.getIdentifiant()) == null);
		} catch (MonException e) {
		    System.out.printf("%s\n", e.getMessage());
		} finally {
			System.out.println();
		}
    }
 
}
