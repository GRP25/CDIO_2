package data.text;

public class DALException extends Exception {

    private int id;
    public DALException(String message) {
        super(message);
    }

    public DALException(String message, Throwable cause) {
        super(message, cause);
    }

    public DALException(int id) {
        super("Id nr: " + id + " findes allerede.");

    }
}
