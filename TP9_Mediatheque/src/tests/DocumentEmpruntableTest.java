package tests;

import java.util.ArrayList;
import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Document;
import mediatheque.Mediatheque;

public class DocumentEmpruntableTest extends TestCase {
	private Mediatheque mediatheque;

    public DocumentEmpruntableTest(String testMethodName) {
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
    
    public void testDocumentEmpruntable1() throws Exception {
		System.out.printf("%s\n", "DocumentEmpruntable1");
		System.out.printf("%s\n", "Cas normal");
        int attendu = Mediatheque.mediatheque.size() - 3 ;
        int resulat = mediatheque.documentEmpruntable().size();
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
 
}
