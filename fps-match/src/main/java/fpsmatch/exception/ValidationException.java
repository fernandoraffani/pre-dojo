package fpsmatch.exception;

public class ValidationException extends RuntimeException {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = -1004782726031689275L;
	
	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Exception exception) {
		super(exception);
	}

}
