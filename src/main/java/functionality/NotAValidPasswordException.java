package functionality;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAValidPasswordException extends Exception {
    NotAValidPasswordException(String msg) {
        super(msg);
    }
}
