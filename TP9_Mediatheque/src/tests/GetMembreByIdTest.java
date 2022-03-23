package tests;

import java.util.ArrayList;
import java.util.Scanner;

import exception.MonException;
import junit.framework.TestCase;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class GetMembreByIdTest extends TestCase {
	private Mediatheque mediatheque;

    public GetMembreByIdTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(System.in));
		mediatheque.createMembres();
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        Mediatheque.membres = new ArrayList<Membre>();
    }
    
    public void testGetMembreById1() throws Exception {
		System.out.printf("%s\n", "GetMembreById1");
		System.out.printf("%s\n", "Cas normal");
		Membre attendu = Mediatheque.membres.get(0);
        Membre resulat = mediatheque.getMembreById(attendu.getIdentifiant());
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(true, attendu.equals(resulat));
		System.out.println();
    }
    
    public void testGetMembreById2() throws Exception {
		System.out.printf("%s\n", "GetMembreById2");
		System.out.printf("%s\n", "Cas anormal");
		try {
	        assertEquals(true, mediatheque.getMembreById("Inconnu") == null);
		} catch (MonException e) {
		    System.out.printf("%s\n", e.getMessage());
		} finally {
			System.out.println();
		}
    }
    
    public void testGetMembreById3() throws Exception {
		System.out.printf("%s\n", "GetMembreById3");
		System.out.printf("%s\n", "Cas anormal");
		try {
			Membre attendu = Mediatheque.membres.get(0);
			Mediatheque.membres = new ArrayList<Membre>();
	        assertEquals(true, mediatheque.getMembreById(attendu.getIdentifiant()) == null);
		} catch (MonException e) {
		    System.out.printf("%s\n", e.getMessage());
		} finally {
			System.out.println();
		}
    }
 
}
