package tests;

import java.util.ArrayList;
import java.util.Scanner;

import exception.MonException;
import junit.framework.TestCase;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Membre;

public class GetIndexEmpruntByIdTest extends TestCase {
	private Mediatheque mediatheque;
	private Membre membre;
	private Document document;

    public GetIndexEmpruntByIdTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		mediatheque = new Mediatheque(new Scanner(""));
		mediatheque.createDocuments();
		mediatheque.createMembres();
		membre = Mediatheque.membres.get(0);
		document = Mediatheque.mediatheque.get(0);
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        membre = null;
        document = null;
        Mediatheque.mediatheque = new ArrayList<Document>();
        Mediatheque.membres = new ArrayList<Membre>();
    }
    
    public void testGetIndexEmpruntById1() throws Exception {
		System.out.printf("%s\n", "GetIndexEmpruntById1");
		System.out.printf("%s\n", "Cas normal");
		String identifiant = document.getIdentifiant();
		membre.getEmprunt().add(document);
		int attendu = 0;
		int resulat = membre.getIndexEmpruntById(identifiant);
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
    
    public void testGetIndexEmpruntById2() throws Exception {
		System.out.printf("%s\n", "GetIndexEmpruntById2");
		System.out.printf("%s\n", "Cas anormal");
		try {
			String identifiant = document.getIdentifiant();
			membre.getIndexEmpruntById(identifiant);
		} catch(MonException e) {
			System.out.printf("%s\n", e.getMessage());
		} finally {
			int attendu = 0;
			int resulat = membre.getEmprunt().size();
			System.out.printf("Resultat attentu : %s\n", attendu);
			System.out.printf("Resultat final : %s\n", resulat);
	        assertEquals(true, attendu == resulat);
		}
		System.out.println();
    }
    
    public void testGetIndexEmpruntById3() throws Exception {
		System.out.printf("%s\n", "GetIndexEmpruntById3");
		System.out.printf("%s\n", "Cas anormal");
		String identifiant = document.getIdentifiant();
		try {
			membre.getEmprunt().add(Mediatheque.mediatheque.get(1));
			membre.getIndexEmpruntById(identifiant);
		} catch(MonException e) {
			System.out.printf("%s\n", e.getMessage());
		} finally {
			int attendu = -1;
			int resulat = -1;
			int i = 0;
			for (Document doc : membre.getEmprunt()) {
				if(doc.getIdentifiant().equals(identifiant)) {
					resulat = i;
				}
				i++;
			}
			System.out.printf("Resultat attentu : %s\n", attendu);
			System.out.printf("Resultat final : %s\n", resulat);
	        assertEquals(true, attendu == resulat);
		}
		System.out.println();
    }

}
