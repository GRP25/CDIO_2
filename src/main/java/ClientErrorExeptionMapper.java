import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientErrorExeptionMapper implements ExceptionMapper<ClientErrorException> {
    @Override
    public Response toResponse(ClientErrorException exception) {
        Response response = exception.getResponse();
        Response.StatusType statusType = response.getStatusInfo();
        int statusCode = statusType.getStatusCode();
        String reasonPhrase = statusType.getReasonPhrase();
        String message = "An HTTP error occurred";
        ErrorResponse entity = new ErrorResponse(statusCode, reasonPhrase, message);
        return Response.ok().entity(entity).build();
    }
}
