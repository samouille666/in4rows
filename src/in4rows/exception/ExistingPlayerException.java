package in4rows.exception;

public class ExistingPlayerException extends Exception {

	private static final long serialVersionUID = -3108577632280095373L;

	public ExistingPlayerException(String message) {
		super(message);
	}

}
