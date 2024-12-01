package exceptions.cascades;

/**
 * Exceptions related to hibernate modification management on entities engaged in a relationship.
 */
public class CascadeException extends RuntimeException {

    public CascadeException(String message) { super(message); }

    public CascadeException() {}
}