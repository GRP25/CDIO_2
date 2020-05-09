package functionality;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotANameException extends Exception {
    NotANameException(String message) {
        super(message);
    }

}
