package mediatheque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import exception.MonException;

public class Mediatheque {
	public static List<Document> mediatheque = new ArrayList<Document>(); // Liste de tous les documents de la médiateque
	public static List<Document> renovation = new ArrayList<Document>(); // Liste de tous les documents en renovation
	public static List<Membre> membres = new ArrayList<Membre>(); // Liste de tous les membres de la médiateque
	public static List<String> OuiNon = Arrays.asList(Choix.oui.choix, Choix.non.choix); // Liste de choix oui ou non reutilisable au besoin
	public static int nextId = 0; // Utilisé pour l'attribut Identifiant de Document et Membre. Indenté à chaque ajout de Membre ou de Document sa liste.
	private final Scanner sc;
	private Membre membreConnecte = null; // Membre de la session

	/** 
	 * Constructeur
	 *
	 * @author	Jeremy Fouquet
	 */
	public Mediatheque(Scanner sc) {
		this.sc = sc;
	}
	
	/** 
	 * Propose au membre connecte un choix d'action parmis une liste
	 * Si membreConnecte.retard() != null appel automatiquement la methode rendreRetard tant qu'un document sera en retard
	 * 
	 * @exception MonException TypeMembre non autorisé
	 * 
	 * @author	Jeremy Fouquet
	 */
	public void actions() {
		Document retard = membreConnecte.retard();
		while (null != retard) {
			membreConnecte.rendreRetard(this, retard);
			retard = membreConnecte.retard();
		}
		List<String> menu = new ArrayList<String>();
		menu.add(Choix.methode1.choix);
		menu.add(Choix.methode2.choix);
		menu.add(Choix.methode3.choix);
		menu.add(Choix.methode4.choix);
		menu.add(Choix.methode5.choix);
		menu.add(Choix.methode6.choix);
		menu.add(Choix.methode7.choix);
		switch (membreConnecte.getTypeMembre()) {
			case Personnel:
				menu.add(Choix.methode8.choix);
				menu.add(Choix.methode9.choix);
				break;
			case Abonne:
				break;
			default:
				System.out.printf("%s\n", "TypeMembre de membreConnecte est inconnue");
		}
		String choix = selection(menu, "Que souhaitez vous faire ?");
		if (choix.equals(Choix.methode1.choix)) {
			membreConnecte.consulterDocumentDisponible();
		} else if(choix.equals(Choix.methode2.choix)) {
			membreConnecte.voirDetail();
		} else if (choix.equals(Choix.methode3.choix)) {
			membreConnecte.consulterDocumentEmprunte();
		} else if (choix.equals(Choix.methode4.choix)) {
			membreConnecte.rendreDocument(this);
		} else if (choix.equals(Choix.methode5.choix)) {
			membreConnecte.emprunterDocument(this);
		} else if (choix.equals(Choix.methode6.choix)) {
			deconnection();
		} else if (choix.equals(Choix.methode7.choix)) {
			connection();		
		} else if (choix.equals(Choix.methode8.choix)) {
			 try {
					membreConnecte.ajouterDocument(this);
			 } catch (MonException e) {
			  		System.out.printf("\n%s\n",e.getMessage());
			 }
		} else if (choix.equals(Choix.methode9.choix)) {
			try {
				membreConnecte.documentARenover(this);
			 } catch (MonException e) {
			  		System.out.printf("\n%s\n",e.getMessage());
			 }
		} else {
			System.out.printf("%s\n", "Le choix selectionné par membreConnecte ne corresponde à aucune methode");
		}
		if (!choix.equals(Choix.methode6.choix) && !choix.equals(Choix.methode7.choix)) {
			actions();
		}
	}
	
	/** 
	 * Interroge l'utilisateur en console au travers du Scanner
	 * 
	 * @return  String correspondant à la reponse de l'utilisateur
	 * @param menu liste de choix de response possible
	 * @param question question à poser avant de demander à l'utilisateur de répondre
	 * 
	 * @author	Jeremy Fouquet
	 */
	public String selection(List<String> menu, String question) {
		String reponse = "";
		int index = 0;
		System.out.printf("\n%s\n%s\n", question, "COPIEZ/COLLEZ l'un des choix puis cliquez ENTREZ pour vous CONTINUEZ :");
		for (String choix : menu) {
			System.out.printf("Choix %d : %s\n", index, choix);
			index++;
		}
		int erreur = 0;
		while (!menu.contains(reponse)) {
			if(erreur > 0) {
				System.out.printf("\n%s\n%s\n", question, "COPIEZ/COLLEZ l'un des choix puis cliquez ENTREZ pour vous CONTINUEZ :");
				index = 0;
				for (String choix : menu) {
					System.out.printf("Choix %d : %s\n", index, choix);
					index++;
				}
			};
			reponse = sc.nextLine();
			erreur++;
		}
		return reponse;
	}

	/** 
	 * Interroge l'utilisateur en console au travers du Scanner
	 * 
	 * @return  String correspondant à la reponse de l'utilisateur
	 * @param question question à poser avant de demander à l'utilisateur de répondre
	 * 
	 * @author	Jeremy Fouquet
	 */
	public String question(String question) {
		String reponse = "";
		int erreur = 0;
		System.out.printf("\n%s\n", question, "cliquez ENTREZ pour CONTINUEZ :");
		while (reponse.equals("") == true) {
			if(erreur > 0) {
				System.out.printf("\n%s\n", question, "cliquez ENTREZ pour CONTINUEZ :");
			};
			reponse = sc.nextLine();
			erreur++;
		}
		return reponse;
	}
	
	/** 
	 * Filtre uniquement les documents pouvant être emprunté
	 * 
	 * @return  List<Document> Liste de documents empruntable
	 * 
	 * @author	Jeremy Fouquet
	 */
	public List<Document> documentEmpruntable() {
		List<Document> documents = new ArrayList<>();
		for (Document document : mediatheque) {
			if (null == document.getDateRetour() && !document.isRestriction()) {
				documents.add(document);
			}
		}
		return documents;
	}
	
	/** 
	 * Filtre uniquement les documents present dans la mediatheque
	 * 
	 * @return  List<Document> Liste de documents present
	 * 
	 * @author	Jeremy Fouquet
	 */
	public List<Document> documentPresent() {
		List<Document> documents = new ArrayList<>();
		for (Document document : mediatheque) {
			if (null == document.getDateRetour()) {
				documents.add(document);
			}
		}
		return documents;
	}
	
	/** 
	 * Se deconnecter de la mediatheque
	 *
	 * @author	Jeremy Fouquet
	 */
	public void deconnection() {
		if(null != membreConnecte) {			
			System.out.printf("\n%s %s\n", "AU REVOIR", membreConnecte.getNom());
			membreConnecte = null;
			connection();
		} else {
			System.out.printf("\n%s\n", "AU REVOIR");
			sc.close();
		}
	}
	
	/** 
	 * Propose de se connecter à la mediatheque avec l'un des profil listé
	 * Si le Scanner == null y affecte une nouvelle instance; Si membreConnect != null alors on le supprime sinon on souhaite la bienvenue
	 * 
	 * @exception IndexOutOfBoundsException L'identifiant du membre n'existe pas dans la liste des membres
	 * @exception MonException La liste membre est vide
	 *
	 * @author	Jeremy Fouquet
	 */
	public void connection() {
		if(null != membreConnecte) {
			membreConnecte = null;
		} else {
			System.out.printf("\n%s\n", "BONJOUR !");
			String choixOuiNon = selection(OuiNon, "Souhaitez vous vous CONNECTER à la MEDIATHEQUE ?");
			if (choixOuiNon.equals(Choix.non.choix)) {					
				deconnection();
				return;
			}
		}
		List<String> menu = new ArrayList<String>(membres.size());
		for (Membre membre : membres) {
			menu.add(membre.getIdentifiant());
		}
		String choix = selection(menu, "Quel est votre identifiant ?");
		Membre membre = null;
	    try {
			membre = getMembreById(choix);
	    } catch (IndexOutOfBoundsException e) {
	  		System.out.printf("\n%s %s\n\n", "OUPS ERREUR : ", "Cette identifiant n'est pas correct veuillez réessayer");
	  		connection();
	    } catch (MonException e) {
	  		System.out.printf("\n%s %s\n\n", "OUPS ERREUR : ", e.getMessage());
	  		connection();
	    } finally {
	    	if(null != membre) {
	    		membreConnecte = membre;
				System.out.printf("\n%s %s !\n", "BIENVENUE", membre.getNom());
				actions();
	    	}
	    }
	}

	/** 
	 * Trouve le Membre avec l'attribut identifiant == id parmis la liste membres
	 * 
	 * @return Membre dont l'attribut identifiant == id
	 * @param id Identifiant du Membre recherché
	 *
	 * @throws MonException La liste membres est vide
	 * 
	 * @author	Jeremy Fouquet
	 */
	public Membre getMembreById(String id) throws MonException {
		int index = 0;
		int membreIndex = -1;
		for (Membre membre : membres) {
			if(membre.getIdentifiant().equals(id)) {
				membreIndex = index;
			}
			index++;			
		}
		Membre membre = null;
		if (membres.size() <= 0) {
			throw new MonException("La liste membres est vide");
		} else if (membreIndex == -1) {
			throw new MonException("Le Membre n'existe pas dans la liste membres");
		} else {
			membre = membres.get(membreIndex);
		}
		return membre;
	}

	/** 
	 * Trouve le Document avec l'attribut identifiant == id parmis la liste mediateque
	 * 
	 * @return Document dont son attribut identifiant == id
	 * @param id Identifiant du Document recherché
	 *
	 * @throws MonException La liste mediatheque est vide
	 * 
	 * @author	Jeremy Fouquet
	 */
	public Document getDocumentById(String id) throws MonException {
		int index = 0;
		int docIndex = -1;
		for (Document doc : mediatheque) {
			if(doc.getIdentifiant().equals(id)) {
				docIndex = index;
			}
			index++;			
		}
		Document document = null;
		if (mediatheque.size() <= 0) {
			throw new MonException("La liste mediatheque est vide");
		} else if (docIndex == -1) {
			throw new MonException("Le Document n'existe pas dans la liste mediatheque");
		} else {
			document = mediatheque.get(docIndex);
		}
		return document;
	}


	/** 
	 * Trouve l'index du Document avec l'attribut identifiant == id dans la liste mediatheque
	 * 
	 * @return index du Document dont son attribut identifiant == id
	 * @param id Identifiant du Document recherché
	 *
	 * @throws MonException La liste mediatheque est vide
	 * 
	 * @author	Jeremy Fouquet
	 */
	public int getIndexMediathequeById(String id) throws MonException {
		int index = -1;
		int i = 0;
		for (Document doc : mediatheque) {
			if(doc.getIdentifiant().equals(id)) {
				index = i;
			};
			i++;
		}
		if (mediatheque.size() <= 0) {
			throw new MonException("La liste mediatheque est vide");
		}
		return index;
	}
	
	
	/** 
	 * Ajout de 9 Documents dans mediatheque
	 * 3 Livre, 3 CD et 3 Dvd avec des caracteristiques aléatoires
	 * 
	 * @see #randomName(int)
	 * @see #randomList(List)
	 *
	 * @author	Jeremy Fouquet
	 */
	public void createDocuments() {
		mediatheque = new ArrayList<Document>();
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
			mediatheque.add(nouveauDocument);
		}
	}
	
	/** 
	 * Ajout de 2 Membres dans membres
	 * 1 TypeMembre.Personnel + 1 TypeMembre.Abonne avec des caracteristiques aléatoires
	 * 
	 * @see #randomName(int)
	 *
	 * @author	Jeremy Fouquet
	 */
	public void createMembres() {
		membres = new ArrayList<Membre>();
		List<TypeMembre> types = Arrays.asList(TypeMembre.Personnel, TypeMembre.Abonne);
		for (TypeMembre typeMembre : types) {
			String id = typeMembre+Integer.toString(Mediatheque.nextId++);
			String nom = randomName(6);
			Membre nouveauMembre = new Membre(id, nom, typeMembre);
			membres.add(nouveauMembre);
		}
	}
	
	/** 
	 * Generateur de String aleatoire 
	 *  
	 * @return	String la String generé
	 * @param longueur longueur de la String souhaité
	 * 
	 * @author	SOURCE : https://www.programiz.com/java-programming/examples/generate-random-string
	 */
	public String randomName(int longueur) {
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
	 * Selection aleatoire d'un element parmi une liste d'element donnés
	 *  
	 * @return	Object l'Object séléctionné
	 * @param list La liste d'un type d'objet particulié dont on souhaite en séléctionné un seul
	 * 
	 * @author	SOURCE : https://www.programiz.com/java-programming/examples/generate-random-string
	 */
	public Object randomList(List<Object> list) {
		int r = (int) (Math.random() * list.size());
	    return list.get(r);
	}
	

	// GETTERS ET SETTERS

	public Membre getMembreConnecte() {
		return membreConnecte;
	}

	public void setMembreConnecte(Membre membreConnecte) {
		this.membreConnecte = membreConnecte;
	}


}


