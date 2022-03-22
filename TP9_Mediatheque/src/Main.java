import java.util.Scanner;

import exception.MonException;
import mediatheque.*;

public class Main {
	public static Mediatheque mediatheque;

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws MonException {
		mediatheque = new Mediatheque(new Scanner(System.in));
		mediatheque.createDocuments();
		mediatheque.createMembres();
		mediatheque.connection();
	}

}

