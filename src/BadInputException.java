
public class BadInputException extends Error {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String message = "Bad Input!";

    public BadInputException() {

    }

    public BadInputException(String message) {
	this.message = message;
    }

    @Override
    public String getMessage() {
	return message;
    }
}
