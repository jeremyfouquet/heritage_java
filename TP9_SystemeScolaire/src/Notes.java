import java.util.ArrayList;
import java.util.List;

public class Notes {
	private Cours cours;
	private List<Double> notes = new ArrayList<Double>();;

	/**
	 * @param cours
	 * 
	 * Constructeur
	 */
	public Notes(Cours cours) {
		this.setCours(cours);
	}

//	GETTERS AND SETTERS

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public List<Double> getNotes() {
		return notes;
	}
}
