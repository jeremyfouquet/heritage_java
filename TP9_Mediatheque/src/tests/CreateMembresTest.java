package tests;

import java.util.ArrayList;
import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class CreateMembresTest extends TestCase {
	private Mediatheque mediatheque;

    public CreateMembresTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(System.in));
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        Mediatheque.membres = new ArrayList<Membre>();
    }
    
    public void testCreateMembres() throws Exception {
		System.out.printf("%s\n", "CreateMembres");
		System.out.printf("%s\n", "Cas normal");
        int avantCreation = Mediatheque.membres.size();
		mediatheque.createMembres();
        int apresCreation = Mediatheque.membres.size();
		System.out.printf("Avant appel de la methode Mediatheque.membres.size() : %s\n", avantCreation);
		System.out.printf("Apres appel de la methode Mediatheque.membres.size() : %s\n", apresCreation);
        assertEquals(true, avantCreation < apresCreation);
		System.out.println();
    }
 
}
