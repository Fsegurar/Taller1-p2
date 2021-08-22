package co.edu.unbosque.model.persistence;

public class IdentifierExistsException extends Exception {
	
	public IdentifierExistsException() {
		super("Id repetido, generado Id especial");
	}

}
