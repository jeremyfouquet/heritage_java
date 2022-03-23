package tests;

import java.util.ArrayList;
import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class RendreDocumentTest extends TestCase {
	private Mediatheque mediatheque;
	private Membre membre;
	private Document emprunt;

    public RendreDocumentTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(""));
		mediatheque.createDocuments();
		mediatheque.createMembres();
		membre = Mediatheque.membres.get(0);
		emprunt = Mediatheque.mediatheque.get(0);
		emprunt.setDateRetour(emprunt.retour());
		membre.getEmprunt().add(emprunt);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        membre = null;
        emprunt = null;
        Mediatheque.mediatheque = new ArrayList<Document>();
        Mediatheque.membres = new ArrayList<Membre>();
    }
    
    public void testRendreDocument1() throws Exception {
		System.out.printf("%s\n", "RendreDocument");
		System.out.printf("%s\n", "Cas normal");
		String identifiant = emprunt.getIdentifiant();
		mediatheque = new Mediatheque(new Scanner(identifiant));
		int tailleAvant = membre.getEmprunt().size();
	    membre.rendreDocument(mediatheque);
		int tailleApres = membre.getEmprunt().size();
		System.out.printf("Resultat attentu : %s\n", tailleAvant);
		System.out.printf("Resultat final : %s\n", tailleApres+1);
        assertEquals(tailleAvant, tailleApres+1);
		System.out.println();
    }
}
