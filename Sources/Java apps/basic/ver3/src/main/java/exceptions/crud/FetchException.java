package exceptions.crud;

public class FetchException extends DAOException {
    public FetchException(String message) { super(message); }

    public FetchException() {}

}
