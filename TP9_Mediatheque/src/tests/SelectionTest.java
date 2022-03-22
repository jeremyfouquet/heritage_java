package tests;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Mediatheque;

public class SelectionTest extends TestCase {
	private Mediatheque mediatheque;
	private List<String> menu;

    public SelectionTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        mediatheque = new Mediatheque(new Scanner(System.in));
		menu = Mediatheque.OuiNon;
    }
    protected void tearDown() throws Exception {
        super.tearDown();
        menu = null;
    }
    
    public void testSelection1() throws Exception {
		System.out.printf("%s\n", "Selection1");
		System.out.printf("%s\n", "Cas normal");
        String attendu = menu.get(0);
        mediatheque = new Mediatheque(new Scanner(attendu));
        String resulat = mediatheque.selection(menu, "Ecrire la question de votre choix ici");
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
    
    public void testSelection2() throws Exception {
		System.out.printf("%s\n", "Selection2");
		System.out.printf("%s\n", "Cas normal");
		try {
	        mediatheque = new Mediatheque(new Scanner("Autre"));
	        mediatheque.selection(menu, "Ecrire la question de votre choix ici");
		} catch (NoSuchElementException e) {
			System.out.printf("\n%s", "Continue à demander une reponse tant que celle ci n'est pas correcte : NoSuchElementException");
		}
		System.out.println();
    }
 
}
