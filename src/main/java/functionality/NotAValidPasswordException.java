package functionality;

public class NotAValidPasswordException extends Exception {
    NotAValidPasswordException(String msg) {
        super(msg);
    }
}
