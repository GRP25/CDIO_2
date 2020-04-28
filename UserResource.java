import datalayer.MySQL.User;
import datalayer.UserDAO;
import dto.UserDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("users")
@RequestScoped
public class UserResource {

    private UserDAO userDAO = new User();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response users() {
        return Response.ok(userDAO.selectAll()).build();
    }

    @GET
    @Path("/{user_id}")
    public Response get(@PathParam("user_id") int userID) {
        UserDTO user = userDAO.select(userID);
        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(UserDTO user) {
        if (userDAO.exists(user.getCpr())) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        userDAO.create(user);
        URI loaction = UriBuilder.fromResource(UserResource.class)
                .path("/{user_id}")
                .resolveTemplate("user_id", user.getId())
                .build();
        return Response.created(loaction).build();
    }

    @PUT
    @Path("/{user_id}")
    public Response update(@PathParam("user_id") int userId, UserDTO user) {
        userDAO.update(userId, user);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{user_id}")
    public Response delete(@PathParam("user_id") int userID) {
        userDAO.delete(userID);
        return Response.ok().build();

    }
}
