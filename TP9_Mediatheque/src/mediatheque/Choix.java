package mediatheque;

public enum Choix {

	RETOUR("RETOUR"),
	methode1("consulterDocumentDisponnible"),
	methode2("voirDetail"),
	methode3("consulterDocumentEmprunte"),
	methode4("rendreDocument"),
	methode5("emprunterDocument"),
	methode6("deconnection"),
	methode7("changerMembre"),
	methode8("ajouterDocument"),
	methode9("documentARenover"),
	oui("Oui"),
	non("Non"),
	Livre("Livre"),
	Cd("Cd"),
	Dvd("Dvd");

	public String choix = "";
	
	private Choix(String choix) {
		this.choix = choix;
	}

}
