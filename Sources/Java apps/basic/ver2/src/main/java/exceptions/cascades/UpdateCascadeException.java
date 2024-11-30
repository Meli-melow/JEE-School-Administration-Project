package exceptions.cascades;

/**
 * Exception dedicated to update failure between entities engaged in a relationship.
 */
public class UpdateCascadeException extends CascadeException { public UpdateCascadeException(String message) { super(message); } }