import data.sql.UserDAO;
import data.UserDTO;
import functionality.NotACPRException;
import functionality.NotANameException;
import functionality.NotAValidPasswordException;

import static functionality.Validation.*;
import static functionality.Conversion.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("users")
@RequestScoped
public class UserResource {

    private UserDAO userDAO = new UserDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response users() {
        return Response.ok(userDAO.getUserList()).build();
    }

    @GET
    @Path("/{user_id}")
    public Response get(@PathParam("user_id") int userID) {
        UserDTO user = userDAO.getUser(userID);
        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserDTO user) throws NotANameException, NotACPRException, NotAValidPasswordException {

        if (userDAO.exists(user.getCpr())){
            return Response.status(Response.Status.NOT_FOUND).entity("Error: A user with this pwd exists").build();
        }

        nameValidator(nameConversion(user.getName()));
        cprValidator(String.valueOf(user.getCpr()));
        passwordValidator(user.getName(),user.getPassword());

        userDAO.createUser(user);
        URI loaction = UriBuilder.fromResource(UserResource.class)
                .path("/{user_id}")
                .resolveTemplate("user_id", user.getId())
                .build();
        return Response.created(loaction).build();
    }

    @PUT
    @Path("/{user_id}")
    public Response update(@PathParam("user_id") int userId, UserDTO user) throws NotANameException, NotACPRException, NotAValidPasswordException {

        nameValidator(nameConversion(user.getName()));
        cprValidator(String.valueOf(user.getCpr()));
        passwordValidator(user.getName(),user.getPassword());

        userDAO.updateUser(user);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{user_id}")
    public Response delete(@PathParam("user_id") int userID) {
        userDAO.deleteUser(userID);
        return Response.ok().build();

    }
}
