package functionality;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MapName implements ExceptionMapper<NotANameException> {
    @Override
    public Response toResponse(NotANameException e) {
        ErrorMessage err = new ErrorMessage(e.getMessage(),3,"https://mama.sh/");
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(err).build();
    }
}
