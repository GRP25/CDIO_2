import data.sql.UserDAO;
import data.IUserDAO;
import data.UserDTO;
import functionality.NotACPRException;
import functionality.NotANameException;
import functionality.NotAValidPasswordException;

import static functionality.Validation.*;
import static functionality.Conversion.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("users")
@RequestScoped
public class UserResource {

    @EJB
    private IUserDAO userDAO = new UserDAO();

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
    public Response create(UserDTO user) {

        if (userDAO.exists(user.getCpr())){
            return Response.status(Response.Status.NOT_FOUND).entity(user).build();
        }

        try {
            nameValidator(nameConversion(user.getName()));
            cprValidator(cprConversion(user.getCpr()));
            passwordValidator(user.getName(),user.getPassword());
        } catch (NotANameException | NotACPRException | NotAValidPasswordException e) {
            return Response.ok(e.getMessage()).build();
        }

        userDAO.createUser(user);
        URI loaction = UriBuilder.fromResource(UserResource.class)
                .path("/{user_id}")
                .resolveTemplate("user_id", user.getId())
                .build();
        return Response.created(loaction).entity(user).build();
    }

    @PUT
    @Path("/{user_id}")
    public Response update(@PathParam("user_id") int userId, UserDTO user) {

        try {
            nameValidator(nameConversion(user.getName()));
            cprValidator(cprConversion(user.getCpr()));
            passwordValidator(user.getName(),user.getPassword());
        } catch (NotANameException | NotACPRException | NotAValidPasswordException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }

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
