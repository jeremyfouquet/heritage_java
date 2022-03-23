import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author jeremy fouquet
 *
 */
public class Main {

	public static void main(String[] args) {
		visiteMaison();
	}

	/**
	 *  Tant que l'utilisateur ne choisi pas STOP via le SCANNER il peut soit :
	 *  Ouvrir une porte pour changer de piece 
	 *  Voir la liste des meubles present dans la piece
	 *  Voir les caracteristique de la piece
	 */
	public static void visiteMaison() {
		Scanner sc = new Scanner(System.in);
		Maison maison = new Maison(true);
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
					System.out.printf("Surface : %s m²\n", visite.getSurface());
					break;
				default:
					break;
			}
		}
		sc.close();
		
	}

	/**
	 * @param sc Scanner permettant d'interragir avec l'utilisateur
	 * @param menu liste des choix de réponse proposé
	 * @return String reponse de l'utilisateur
	 * 
	 * Propose à l'utilisateur de faire un choix parmis une liste de String
	 */
	public static String selectChoix(Scanner sc, List<String> menu) {
		String choix = "";
		System.out.printf("\nVous avez le choix parmis les %s actions suivantes :\n", menu.size());
		for (String c : menu) {
			System.out.printf("%s\n", c);
		}
		while (!menu.contains(choix)) {
			System.out.printf("\nRECOPIEZ votre choix ci dessous, puis click ENTREZ: \n");
			choix = sc.nextLine();
		}
		return choix;
	}

	/**
	 * @param sc Scanner permettant d'interragir avec l'utilisateur
	 * @param portes Liste des portes accessibles
	 * @return visite Piece actuelle
	 * 
	 * Propose à l'utilisateur choisir parmis une ou deux porte menant à une autre piece
	 */
	public static int selectPosition(Scanner sc, List<Porte> portes, Piece visite) {
		List<Integer> positionAccepte = new ArrayList<Integer>();
		int position = -1;
		while (!positionAccepte.contains(position)) {
			System.out.printf("\nPour OUVRIR une porte vers \n");
			for (Porte porte : portes) {
				for (Piece piece : porte.getConnecte()) {
					if(piece.getType() != visite.getType()) {
						positionAccepte.add(piece.getPosition());
						System.out.printf("%s => RECOPIEZ %d, puis click ENTREZ\n", piece.getType(), piece.getPosition());
					}
				}
			}
			try {
				position = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.printf("%s\n", "Seul les valeurs numeriques sont autorisés");
			}
		}
		return position;
	}



}
