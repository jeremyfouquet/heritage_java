package tests;

import java.util.ArrayList;
import java.util.Scanner;

import exception.MonException;
import junit.framework.TestCase;
import mediatheque.Document;
import mediatheque.Mediatheque;

public class GetIndexMediathequeByIdTest extends TestCase {
	private Mediatheque mediatheque;

    public GetIndexMediathequeByIdTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(System.in));
		mediatheque.createDocuments();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testGetIndexMediathequeById1() throws Exception {
		System.out.printf("%s\n", "GetIndexMediathequeById1");
		System.out.printf("%s\n", "Cas normal");
		int attendu = 0;
		Document document = Mediatheque.mediatheque.get(attendu);
		int resulat = mediatheque.getIndexMediathequeById(document.getIdentifiant());
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
    
    public void testGetIndexMediathequeById2() throws Exception {
		System.out.printf("%s\n", "GetIndexMediathequeById2");
		System.out.printf("%s\n", "Cas anormal");
		int attendu = -1;
		int resultat = mediatheque.getIndexMediathequeById("Inconnu");
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resultat);
        assertEquals(attendu, resultat);
		System.out.println();
    }
    
    public void testGetIndexMediathequeById3() throws Exception {
		System.out.printf("%s\n", "GetIndexMediathequeById3");
		System.out.printf("%s\n", "Cas posant probleme");
		try {
			String identifiant = Mediatheque.mediatheque.get(0).getIdentifiant();
			Mediatheque.mediatheque = new ArrayList<Document>();
			int attendu = -1;
	        assertEquals(attendu, mediatheque.getIndexMediathequeById(identifiant));
		} catch(MonException e) {
			System.out.printf("%s\n", e.getMessage());
		} finally {
			System.out.println();
		}
    }

}
