package functionality;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MapPasswd implements ExceptionMapper<NotAValidPasswordException> {
    @Override
    public Response toResponse(NotAValidPasswordException e) {
        ErrorMessage err = new ErrorMessage(e.getMessage(),1,"https://mama.sh/");
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(err).build();
    }
}
