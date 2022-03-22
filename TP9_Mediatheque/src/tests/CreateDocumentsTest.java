package tests;

import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Mediatheque;

public class CreateDocumentsTest extends TestCase {
	private Mediatheque mediatheque;

    public CreateDocumentsTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(System.in));
    }
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCreateDocuments() throws Exception {
		System.out.printf("%s\n", "CreateDocuments");
		System.out.printf("%s\n", "Cas normal");
        int avantCreation = Mediatheque.mediatheque.size();
		mediatheque.createDocuments();
        int apresCreation = Mediatheque.mediatheque.size();
		System.out.printf("Avant appel de la methode Mediatheque.mediatheque.size() : %s\n", avantCreation);
		System.out.printf("Apres appel de la methode Mediatheque.mediatheque.size() : %s\n", apresCreation);
        assertEquals(true, avantCreation < apresCreation);
		System.out.println();
    }
 
}
