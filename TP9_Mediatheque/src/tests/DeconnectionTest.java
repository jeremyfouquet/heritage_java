package tests;

import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class DeconnectionTest extends TestCase {
	private Mediatheque mediatheque;
	private Membre membre;

    public DeconnectionTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner("Non"));
		mediatheque.createMembres();
		membre = Mediatheque.membres.get(0);
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        membre = null;
    }
    
    public void testDeconnection() throws Exception {
		System.out.printf("%s\n", "Deconnection");
		System.out.printf("%s\n", "Cas normal");
		mediatheque.setMembreConnecte(membre);
		mediatheque.deconnection();
        Membre attendu = null;
        Membre resulat = mediatheque.getMembreConnecte();
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(true, resulat == attendu);
		System.out.println();
    }
 
}
