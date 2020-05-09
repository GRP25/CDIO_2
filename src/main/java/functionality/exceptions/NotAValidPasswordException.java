package functionality.exceptions;

public class NotAValidPasswordException extends UserException {
    NotAValidPasswordException(String msg) {
        super(msg);
    }
}
