import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author webdevelopment
 *
 */
public class Main {

	public static void main(String[] args) {
		// On visite la maison
		visiteMaison("visiteMaison");
		// TESTS
		testAfficherMeubles("afficherMeubles");
		testAfficherGrille("afficherGrille");
		testAfficherPieces("afficherPieces");
		testSurfaceTotal("surfaceTotal");
		testStockPieces("stockPieces");
	}
	
	public static void testAfficherMeubles(String testMethodName) {
		Maison maison = new Maison();
		String piece = maison.getPieces().get(0).getType();
		int nbMeubles = 8;
		System.out.printf("TEST : %s \n%s affiche la liste de ses %d meubles : \n",testMethodName, piece, nbMeubles);
		int liste = maison.getPieces().get(0).afficherMeubles();
		boolean  conditionTestSuccess = nbMeubles == liste;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testAfficherGrille(String testMethodName) {
		Maison maison = new Maison();
		String piece = maison.getPieces().get(0).getType();
		int nbMeubles = 8;
		System.out.printf("TEST : %s \naffiche la grille représentant %s \n",testMethodName, piece);
		int liste = maison.getPieces().get(0).afficherGrille();
		boolean  conditionTestSuccess = nbMeubles == liste;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testAfficherPieces(String testMethodName) {
		Maison maison = new Maison();
		int nbPieces = 5;
		System.out.printf("TEST : %s \n%s affiche la liste de ses %d pieces : \n",testMethodName, maison.getClass().getName(), nbPieces);
		int liste = maison.afficherPieces();
		boolean  conditionTestSuccess = nbPieces == liste;
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testSurfaceTotal(String testMethodName) {
		Maison maison = new Maison();
		double surfaceTotal = 0;
		for (Piece piece : maison.getPieces()) {
			surfaceTotal += piece.getDimensions()*5;
		}
		System.out.printf("TEST : %s \n%s affiche sa surface total : %.1f\n",testMethodName, maison.getClass().getName(), surfaceTotal);
		boolean conditionTestSuccess = surfaceTotal == maison.surfaceTotal();
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	public static void testStockPieces(String testMethodName) {
		Maison maison = new Maison();
		List<Meuble> meubles = maison.getPieces().get(0).getMeuble();
		Porte porte = null;
		while (porte == null) {
			for (Meuble meuble : meubles) {
				if(meuble.getNom().equals(TypeMeuble.porte.getNom())) {
					porte = (Porte) meuble;
				}
			}
		}
		int nbPiece = porte.getConnecte().size();
		System.out.printf("TEST : %s\n%s : %s stock les %d pieces qu'elle connecte \n",testMethodName, porte.getNom(), porte, nbPiece);
		boolean conditionTestSuccess = porte.getConnecte().size() == porte.stockPieces().size();
		System.out.printf("SUCCESS : %b \n", conditionTestSuccess);
		System.out.println();
	}
	
	
	/**
	 * @param methodName
	 * 
	 *  Une personne peut donc visiter la maison, en se déplaçant dans les
	 *	différentes pièces en choisissant à l'aide d'un menu la porte à ouvrir,
	 *  afficher la liste des objets présents dans la pièce, ou les
	 *	caractéristiques de celles-ci.
	 */
	public static void visiteMaison(String methodName) {
		Scanner sc = new Scanner(System.in);
		System.out.printf("METHODE : %s\n", methodName);
		Maison maison = new Maison();
		int precedentePosition = -1;
		int position = 1;
		String choix = "";
		List<String> menu = Arrays.asList("Ouvrir une porte", "Liste des meubles", "Voir les caracteristiques", "STOP");
		while(!choix.contains("STOP")) {
			choix = null;
			Piece visite = null;
			List<Porte> portes = new ArrayList<Porte>();
			for (Piece piece : maison.getPieces()) {
				if(piece.getPosition() == position) {
					visite = piece;
				}
			}
			if(precedentePosition != position) {System.out.printf("VOUS ENTREZ DANS %s\n", visite.getType().toUpperCase());};
			for (Meuble meuble : visite.getMeuble()) {
				if(meuble.getNom().equals(TypeMeuble.porte.getNom())) {
					Porte porte = (Porte) meuble;
					if(!portes.contains(porte)) {
						portes.add(porte);
					}
				}
			}
			choix = selectChoix(sc, menu);
			switch (choix) {
				case "Ouvrir une porte":
					precedentePosition = position;
					position = selectPosition(sc, portes, visite);
					break;
				case "Liste des meubles":
					precedentePosition = position;
					visite.afficherMeubles();
					break;
				case "Voir les caracteristiques":
					precedentePosition = position;
					System.out.printf("Nom : %s\n", visite.getType());
					System.out.printf("Couleur : %s\n", visite.getCouleur());
					System.out.printf("Taille : %s\n", visite.getTaille());
					System.out.printf("Surface : %s\n", visite.getSurface());
					break;
				default:
					break;
			}
		}
		sc.close();
		
	}

	// CHOIX MENU
	public static String selectChoix(Scanner sc, List<String> menu) {
		String choix = "";
		System.out.printf("Vous avez le choix parmis les %s actions suivantes : \n", menu.size());
		while (!menu.contains(choix)) {
			for (String c : menu) {
				System.out.printf("%s\n", c);
			}
			System.out.printf("RECOPIEZ votre choix ci dessous, puis click ENTREZ: \n");
			choix = sc.nextLine();
		}
		return choix;
	}
	// CHOIX PORTE
	public static int selectPosition(Scanner sc, List<Porte> portes, Piece visite) {
		List<Integer> positionAccepte = new ArrayList<Integer>();
		System.out.printf("Pour OUVRIR une porte vers \n");
		int position = -1;
		while (!positionAccepte.contains(position)) {
			for (Porte porte : portes) {
				for (Piece piece : porte.getConnecte()) {
					if(piece.getType() != visite.getType()) {
						positionAccepte.add(piece.getPosition());
						System.out.printf("%s => TAPEZ %d \n", piece.getType(), piece.getPosition());
					}
				}
			}
			position = sc.nextInt();
			sc.nextLine();
		}
		return position;
	}



}
