import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import exception.MonException;
import mediatheque.*;

public class Main {
	public static Mediatheque mediatheque;

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws MonException {
		mediatheque = new Mediatheque();
		createMembres();
		createDocuments();
		mediatheque.connection();
	}
	
	/** 
	 * Ajout de 9 Document dans mediatheque
	 * 3 Livre, 3 CD et 3 Dvd avec des caracteristiques aléatoires
	 *  
	 * 
	 * @author	Jeremy Fouquet
	 */
	public static void createDocuments() {
		List<String> types = new ArrayList<String>();
		while(types.size() < 9) {
			types.add(Choix.Livre.choix);
			types.add(Choix.Cd.choix);
			types.add(Choix.Dvd.choix);
		}
		for (String typeDocument : types) {
			Document nouveauDocument = null;
			String nom = randomName(6);
			String titre = randomName(6);
			boolean restriction = typeDocument.equals(Choix.Cd.choix)? true : false;
			if(typeDocument == Choix.Livre.choix) {
				String auteur = randomName(6);
				String editeu = randomName(6);
				Categorie categorie = (Categorie) randomList(Arrays.asList(Categorie.Journal, Categorie.Bd, Categorie.Manga, Categorie.Nouvelle, Categorie.Roman));
				nouveauDocument = new Livre(nom, titre, restriction, auteur, editeu, categorie);
			} else if (typeDocument == "Cd") {
				String auteur = randomName(6);
				String compositeur = randomName(6);
				nouveauDocument = new Cd(nom, titre, restriction, auteur, compositeur);
			} else {
				String studio = randomName(6);
				nouveauDocument = new Dvd(nom, titre, restriction, studio);
			}
			Mediatheque.mediatheque.add(nouveauDocument);
		}
	}
	
	/** 
	 * Ajout de 2 Membre dans membres
	 * 1 TypeMembre.Personnel + 1 TypeMembre.Abonne avec des caracteristiques aléatoires
	 *  
	 * @author	Jeremy Fouquet
	 */
	public static void createMembres() {
		List<TypeMembre> types = Arrays.asList(TypeMembre.Personnel, TypeMembre.Abonne);
		for (TypeMembre typeMembre : types) {
			String id = typeMembre+Integer.toString(Mediatheque.nextId++);
			String nom = randomName(6);
			Membre nouveauMembre = new Membre(id, nom, typeMembre);
			Mediatheque.membres.add(nouveauMembre);
		}
	}
	
	/** 
	 * Generateur de String aleatoire 
	 *  
	 * @return	String generé
	 * @param
	 * longueur : longueur de la String souhaité
	 * 
	 * @author	https://www.programiz.com/java-programming/examples/generate-random-string
	 */
	public static String randomName(int longueur) {
		// create a string of all characters
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    // create random string builder
	    StringBuilder sb = new StringBuilder();
	    // create an object of Random class
	    Random random = new Random();
	    // specify length of random string
	    int length = longueur;
	    for(int i = 0; i < length; i++) {
	      // generate random index number
	      int index = random.nextInt(alphabet.length());
	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);
	      // append the character to string builder
	      sb.append(randomChar);
	    }
	    String name = sb.toString();
	    return name.toLowerCase();
	}
	
	/** 
	 * Methode qui selectionne un element parmi une liste d'element donnés
	 *  
	 * @return	l'Object séléctionné
	 * @param
	 * list : La liste d'un type d'objet particulié dont on souhaite en séléctionné un seul
	 * 
	 * @author	Jeremy Fouquet
	 */
	public static Object randomList(List<Object> list) {
		int r = (int) (Math.random() * list.size());
	    return list.get(r);
	}

}

