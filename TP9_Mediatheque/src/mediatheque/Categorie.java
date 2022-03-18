package mediatheque;

public enum Categorie {
	Bd("Bd"),
	Roman("Roman"),
	Manga("Manga"),
	Journal("Journal"),
	Nouvelle("Nouvelle");
	


	public String categorie = "";
	
	private Categorie(String categorie) {
		this.categorie = categorie;
	}
}
