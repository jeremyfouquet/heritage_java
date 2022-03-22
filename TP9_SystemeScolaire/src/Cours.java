import java.util.Objects;

/**
 * @author Jeremy Fouquet
 *
 */
public class Cours {    
	private String Lieu;
	private String horaire;
	private int duree;
	private String theme;
	private int coefficient;
	private TypeCours type;
	
	/**
	 * @param lieu
	 * @param horaire
	 * @param duree
	 * @param theme
	 * @param coefficient
	 * 
	 * Constructeur
	 */
	public Cours(String lieu, String horaire, int duree, String theme, int coefficient) {
		this.setLieu(lieu);
		this.setHoraire(horaire);
		this.setDuree(duree);
		this.setTheme(theme);
		this.setCoefficient(coefficient);
		this.setType(TypeCours.CoursMagistral);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cours other = (Cours) obj;
		return Objects.equals(Lieu, other.Lieu) && coefficient == other.coefficient && duree == other.duree
				&& Objects.equals(horaire, other.horaire) && Objects.equals(theme, other.theme) && type == other.type;
	}
	@Override
	public String toString() {
		return "Cours [Lieu=" + Lieu + ", horaire=" + horaire + ", duree=" + duree + ", theme=" + theme
				+ ", coefficient=" + coefficient + ", type=" + type + "]";
	}
	

//	GETTERS AND SETTERS

	public String getLieu() {
		return Lieu;
	}

	public void setLieu(String lieu) {
		Lieu = lieu;
	}

	public String getHoraire() {
		return horaire;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public TypeCours getType() {
		return type;
	}

	public void setType(TypeCours type) {
		this.type = type;
	}

}
