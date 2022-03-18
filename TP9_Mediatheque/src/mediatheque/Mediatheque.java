package mediatheque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import exception.MonException;

public class Mediatheque {
	public static List<Document> mediatheque = new ArrayList<Document>(); // Liste de tous les documents de la médiateque
	public static List<Document> renovation = new ArrayList<Document>(); // Liste de tous les documents en renovation
	public static List<Membre> membres = new ArrayList<Membre>(); // Liste de tous les membres de la médiateque
	public static List<String> OuiNon = Arrays.asList(Choix.oui.choix, Choix.non.choix); // Liste de choix oui ou non reutilisable au besoin
	public static int nextId = 0; // Utilisé pour l'attribut Identifiant de Document et Membre. Indenté à chaque ajout de Membre ou de Document sa liste.
	public static Scanner sc;
	private Membre membreConnecte = null;

	public Mediatheque() {
	}
	
	/** 
	 * Propose de se connecter à la mediatheque avec l'un des profil listé
	 * 
	 * @exception 
	 * si membreConnecte ne possede pas le TypeMembre Personnel ou Abonne
	 * si le choix de l'action ne fait pas partie des methodes proposé
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
			membreConnecte.ajouterDocument(this);
		} else if (choix.equals(Choix.methode9.choix)) {
			membreConnecte.documentARenover(this);
		} else {
			System.out.printf("%s\n", "Le choix selectionné par membreConnecte ne corresponde à aucune methode");
		}
		if (!choix.equals(Choix.methode6.choix) && !choix.equals(Choix.methode7.choix)) {
			actions();
		}
	}
	
	/** 
	 * Interroge l'utilisateur en console au travers du Scanner
	 * DESCRIPTION SUPPLEMENTAIRE
	 * 
	 * @return  String correspondant à la reponse de l'utilisateur
	 * @param
	 * menu : liste de choix de response possible
	 * question : question à poser avant de demander à l'utilisateur de répondre
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
	
	public Object question(String question) {
		String reponse = "";
		int erreur = 0;
		System.out.printf("\n%s\n", question, "cliquez ENTREZ pour vous CONTINUEZ :");
		while (reponse.equals("") == true) {
			if(erreur > 0) {
				System.out.printf("\n%s\n", question, "cliquez ENTREZ pour vous CONTINUEZ :");
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
	 * Se deconnecter de la mediatheque
	 *
	 * @author	Jeremy Fouquet
	 * @throws MonException 
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
	 * 
	 * @exception 
	 * si indexOutOfBounds
	 * si la liste membre est vide
	 *
	 * @author	Jeremy Fouquet
	 */
	public void connection() {
		if (null == sc) {
			sc = new Scanner(System.in);
		}
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
	 * Trouve le Membre ayant l'attribut identifiant == id parmis membres
	 * 
	 * @return Membre dont son identifiant = id
	 * @param
	 * id :	l'identifiant du Membre recherché
	 *
	 * @exception
	 * si la liste membre est vide
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
		if (membres.size() <= 0) {
			throw new MonException("La liste membres est vide");
		}
		Membre membre = membres.get(membreIndex);
		return membre;
	}

	/** 
	 * Trouve le Document ayant l'attribut identifiant == id parmis mediateque
	 * 
	 * @return Membre dont son identifiant = id
	 * @param
	 * id :	l'identifiant du Document recherché
	 *
	 * @exception
	 * si la liste document est vide
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
		if (mediatheque.size() <= 0) {
			throw new MonException("La liste mediatheque est vide");
		}
		Document document = mediatheque.get(docIndex);
		return document;
	}


	
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
			throw new MonException("La liste emprunt est vide");
		}
		return index;
	}

	



}


