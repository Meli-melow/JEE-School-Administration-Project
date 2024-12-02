package exceptions.crud;

/**
 * Exception dedicated to hibernate CRUD operations performed by DAOs.
 */
public class DAOException extends RuntimeException {

    public DAOException(String message) { super(message); }

    public DAOException() {}
}
