package tests;

import java.util.NoSuchElementException;
import java.util.Scanner;

import junit.framework.TestCase;
import mediatheque.Mediatheque;

public class QuestiontionTest extends TestCase {
	private Mediatheque mediatheque;

    public QuestiontionTest(String testMethodName) {
        super(testMethodName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        mediatheque = new Mediatheque(new Scanner(System.in));
    }
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testQuestiontion1() throws Exception {
		System.out.printf("%s\n", "Questiontion1");
		System.out.printf("%s\n", "Cas normal");
        String attendu = "Test";
        mediatheque = new Mediatheque(new Scanner(attendu));
        String resulat = mediatheque.question("Ecrire la question de votre choix ici");
		System.out.printf("Resultat attentu : %s\n", attendu);
		System.out.printf("Resultat final : %s\n", resulat);
        assertEquals(attendu, resulat);
		System.out.println();
    }
    
    public void testSelection2() throws Exception {
		System.out.printf("%s\n", "Questiontion2");
		System.out.printf("%s\n", "Cas normal");
		try {
	        mediatheque = new Mediatheque(new Scanner(""));
	        mediatheque.question("Ecrire la question de votre choix ici");
		} catch (NoSuchElementException e) {
			System.out.printf("\n%s", "Continue à demander une reponse tant que celle ci est une String vide : NoSuchElementException");
		}
		System.out.println();
    }
 
}
