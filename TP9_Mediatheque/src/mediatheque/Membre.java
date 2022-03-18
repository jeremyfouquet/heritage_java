package mediatheque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import exception.MonException;

public class Membre {
	private String identifiant;
	private String nom;
	private TypeMembre typeMembre;
	private final int maxEmprunt = 3;
	private List<Document> emprunt = new ArrayList<Document>(maxEmprunt);

	/**
	 * @param identifiant
	 * @param nom
	 * @param typeMembre
	 */
	public Membre(String identifiant, String nom, TypeMembre typeMembre) {
		this.identifiant = identifiant;
		this.nom = nom;
		this.typeMembre = typeMembre;
	}
	
	public void consulterDocumentDisponible() {
		System.out.printf("\n%s\n", "Voici la liste des medias :");
		System.out.println();
		for (Document media : Mediatheque.mediatheque) {
			media.voirDetail();
			System.out.println();
		}
	}
	
	public void voirDetail() {		
		System.out.println();
		System.out.printf("Identifiant : %s\n", identifiant);
		System.out.printf("Nom : %s\n", nom);
		System.out.printf("Type de membre : %s\n", typeMembre);
	}
	
	public void consulterDocumentEmprunte() {
		if (emprunt.size() > 0) {
			System.out.printf("\n%s\n", "Voici la liste des medias empruntés :");
			for (Document media : emprunt) {
				media.voirDetail();
				System.out.println();
			}
		} else {
			System.out.printf("\n%s\n", "Vous n'avez encore aucun media emprunté !");
		}
	}

	public void rendreDocument(Mediatheque mediatheque) {
		String choix = "";
		String retour = Choix.RETOUR.choix;
		Document emprunt = null;
		while(this.emprunt.size() > 0 && !choix.equals(retour)) {
			List<String> liste = new ArrayList<>();
			for (Document doc : this.emprunt) {
				liste.add(doc.getIdentifiant());
			}
			liste.add(retour);
			choix = mediatheque.selection(liste, "Quel document souhaitez vous RENDRE ?");
			if (!choix.equals(retour)) {
				try {
					emprunt = mediatheque.getDocumentById(choix);
				} catch (IndexOutOfBoundsException e) {
					System.out.printf("\n%s %s\n", "OUPS ERREUR : ", "Cette identifiant de document n'existe pas dans la mediatheque");
				} catch (MonException e) {
					System.out.printf("\n%s %s\n", "OUPS ERREUR : ", e.getMessage());
				} finally {
					if(null != emprunt) {
						int index = -1;								
						try {
							index = getIndexDocumentById(emprunt.getIdentifiant());					
						} catch (IndexOutOfBoundsException e) {
							System.out.printf("\n%s %s\n", "OUPS ERREUR : ", "Cette identifiant de document n'existe pas dans la emprunt");
						} catch (MonException e) {
							System.out.printf("\n%s %s\n", "OUPS ERREUR : ", e.getMessage());
						} finally {
							emprunt.setDateRetour(null);
							this.emprunt.remove(index);
						}
					}
				}
			}
		}
	}
	
	public void rendreRetard(Mediatheque mediatheque, Document emprunt) {
		String choix = "";
		int nbRetard = 0;
		for (Document doc : this.emprunt) {
			nbRetard += doc.estEnRetard()? 1 : 0;
		}
		System.out.printf("\n%s\n","Vous avez "+nbRetard+" document(s) en retard merci de le(s) rendre pour continuer d'utiliser nos services !");
		choix = mediatheque.selection(Mediatheque.OuiNon, "Souhaitez vous rendre le document "+emprunt.getIdentifiant()+" ?");
		if (!choix.equals(Choix.non.choix)) {
			int index = -1;								
			try {
				index = getIndexDocumentById(emprunt.getIdentifiant());					
			} catch (IndexOutOfBoundsException e) {
				System.out.printf("\n%s %s\n", "OUPS ERREUR : ", "Cette identifiant de document n'existe pas dans la emprunt");
			} catch (MonException e) {
				System.out.printf("\n%s %s\n", "OUPS ERREUR : ", e.getMessage());
			} finally {
				emprunt.setDateRetour(null);
				this.emprunt.remove(index);
			}
		}	
	}
	
	public void emprunterDocument(Mediatheque mediatheque) {
		String choix = "";
		String retour = Choix.RETOUR.choix;
		while(this.emprunt.size() < maxEmprunt && !choix.equals(retour)) {
			List<Document> documentEmprutable = mediatheque.documentEmpruntable();
			List<String> liste = new ArrayList<>();
			for (Document doc : documentEmprutable) {
				liste.add(doc.getIdentifiant());
			}
			liste.add(retour);
			choix = mediatheque.selection(liste, "Quel document souhaitez vous EMPRUNTER ?");
			if (!choix.equals(retour)) {
				Document emprunt = null;
				try {
					emprunt = mediatheque.getDocumentById(choix);
				} catch (IndexOutOfBoundsException e) {
					System.out.printf("\n%s %s\n", "OUPS ERREUR : ", "Cette identifiant de document n'existe pas dans la mediatheque");
				} catch (MonException e) {
					System.out.printf("\n%s %s\n", "OUPS ERREUR : ", e.getMessage());
				} finally {
					if(null != emprunt) {
						emprunt.setDateRetour(emprunt.retour());
						this.emprunt.add(emprunt);
					}
				}
			}
		}
		if (this.emprunt.size() >= maxEmprunt) {
			String choixOuiNon = mediatheque.selection(Mediatheque.OuiNon,  "Vous avez "+maxEmprunt+" documents empruntés. Souhaitez vous rendre un document ?");
			if (choixOuiNon.equals(Choix.oui.choix)) {
				rendreDocument(mediatheque);
			}
		}
			
	}
	
	public Document retard() {
		Document retard = null;
		for (Document doc : emprunt) {
			if (doc.estEnRetard()) {
				retard = doc;
			}
		}
		return retard;
	}
	public int getIndexDocumentById(String id) throws MonException {
		int index = -1;
		int i = 0;
		for (Document doc : this.emprunt) {
			if(doc.getIdentifiant().equals(id)) {
				index = i;
			};
			i++;
		}
		if (this.emprunt.size() <= 0) {
			throw new MonException("La liste emprunt est vide");
		}
		return index;
	}
	
	public void ajouterDocument(Mediatheque mediatheque) {
		if(this.typeMembre.equals(TypeMembre.Personnel)) {
			Document ajout = null;
			String choix = "";
			List<String> listMedia = Arrays.asList(Choix.Livre.choix, Choix.Dvd.choix, Choix.Cd.choix, Choix.RETOUR.choix);
			choix = mediatheque.selection(listMedia, "Quel media souhaitez vous ajouter ?");
			while(!choix.equals(Choix.RETOUR.choix) && null == ajout) {
				String nom = (String) mediatheque.question("Quel est le Nom du "+choix);
				String titre = (String) mediatheque.question("Quel est le Titre du "+choix);
				boolean restriction = (boolean) mediatheque.selection(Mediatheque.OuiNon, "Le "+choix+" est il restrictif ?").equals(Choix.oui.choix)? true : false;
				if(choix.equals(Choix.Livre.choix)) {
					String auteur = (String) mediatheque.question("Quel est l' Auteur du "+choix);
					String editeur = (String) mediatheque.question("Quel est l' Editeur du "+choix);
					List<String> categories = Arrays.asList(Categorie.Bd.categorie, Categorie.Journal.categorie, Categorie.Manga.categorie, Categorie.Nouvelle.categorie, Categorie.Roman.categorie);
					String categorieName = mediatheque.selection(categories, "Quel est la categorie du "+choix);
					Categorie categorie = null;
					if (categorieName.equals(Categorie.Bd.categorie)) {
						categorie = Categorie.Bd;
					} else if (categorieName.equals(Categorie.Roman.categorie)) {
						categorie = Categorie.Roman;
					} else if (categorieName.equals(Categorie.Journal.categorie)) {
						categorie = Categorie.Journal;
					} else if (categorieName.equals(Categorie.Nouvelle.categorie)) {
						categorie = Categorie.Nouvelle;
					} else if (categorieName.equals(Categorie.Manga.categorie)) {
						categorie = Categorie.Manga;
					} else {
						System.out.printf("\n%s\n", "Le choix de la categorie du "+choix+" n'existe pas");
					}
					ajout = new Livre(nom, titre, restriction, auteur,editeur, categorie);
				} else if (choix.equals(Choix.Cd.choix)) {
					String auteur = (String) mediatheque.question("Quel est l' Auteur du "+choix);
					String compositeur = (String) mediatheque.question("Quel est le Compositeur du "+choix);
					ajout = new Cd(nom, titre, restriction, auteur, compositeur);

				} else if (choix.equals(Choix.Dvd.choix)) {
					String studio = (String) mediatheque.question("Quel est le studio du "+choix);
					ajout = new Dvd(nom, titre, restriction, studio);
				} else {
					System.out.printf("\n%s\n", "Le choix du type de media n'existe pas");
				}
				System.out.printf("\n%s\n", "Vous allez ajouter le "+choix+" :");
				ajout.voirDetail();
				choix = mediatheque.selection(Mediatheque.OuiNon, "Êtes-Vous sûre de vouloir ajouter ce document ?");
				if (choix.equals(Choix.oui.choix)) {
					Mediatheque.mediatheque.add(ajout);
				}
			}
		} else {
			System.out.printf("\n%s\n", "Vous n'etes pas authorisé à faire ça !");
		}
	}
	
	public void documentARenover(Mediatheque mediatheque) {
		if(this.typeMembre.equals(TypeMembre.Personnel)) {
			String choix = "";
			List<Document> documents = mediatheque.documentEmpruntable();
			while(documents.size() > 0 && !choix.equals(Choix.RETOUR.choix)) {
				List<String> liste = new ArrayList<>();
				for (Document document : mediatheque.documentEmpruntable()) {
					liste.add(document.getIdentifiant());
				}
				liste.add(Choix.RETOUR.choix);
				choix = mediatheque.selection(liste, "Quel document est à RENOVER ?");
				if (!choix.equals(Choix.RETOUR.choix)) {
					Document renove = null;
					try {
						renove = mediatheque.getDocumentById(choix);
					} catch (IndexOutOfBoundsException e) {
						System.out.printf("\n%s %s\n", "OUPS ERREUR : ", "Cette identifiant de document n'existe pas dans la mediatheque");
					} catch (MonException e) {
						System.out.printf("\n%s %s\n", "OUPS ERREUR : ", e.getMessage());
					} finally {
						if(null != renove) {
							int index = -1;								
							try {
								index = mediatheque.getIndexMediathequeById(renove.getIdentifiant());			
							} catch (IndexOutOfBoundsException e) {
								System.out.printf("\n%s %s\n", "OUPS ERREUR : ", "Cette identifiant de document n'existe pas dans la mediatheque");
							} catch (MonException e) {
								System.out.printf("\n%s %s\n", "OUPS ERREUR : ", e.getMessage());
							} finally {
								Mediatheque.renovation.add(renove);
								Mediatheque.mediatheque.remove(index);
							}
						}
					}
				}
			}
		} else {
			System.out.printf("\n%s\n", "Vous n'etes pas authorisé à faire ça !");
		}
	}

	// GETTERS ET SETTERS

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public TypeMembre getTypeMembre() {
		return typeMembre;
	}

	public void setTypeMembre(TypeMembre typeMembre) {
		this.typeMembre = typeMembre;
	}

	public List<Document> getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(List<Document> emprunt) {
		this.emprunt = emprunt;
	}

	@Override
	public String toString() {
		return "Membre [identifiant=" + identifiant + ", nom=" + nom + ", typeMembre=" + typeMembre + ", emprunt="
				+ emprunt + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Membre other = (Membre) obj;
		return Objects.equals(emprunt, other.emprunt) && Objects.equals(identifiant, other.identifiant)
				&& maxEmprunt == other.maxEmprunt && Objects.equals(nom, other.nom) && typeMembre == other.typeMembre;
	}

}
