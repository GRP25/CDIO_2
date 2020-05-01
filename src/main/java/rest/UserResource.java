package rest;

import data.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("UserResource")
public class UserResource {
    UserService userService = new UserServiceImpl();

    @Path("{userID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser(@PathParam("userID") int userID) {
        return userService.getUser(userID);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(UserDTO user) {
        userService.updateUser(user);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(UserDTO user) {
        userService.createUser(user);
    }

    @Path("{userID}")
    @DELETE
    public void deleteUser(@PathParam("userID") int userID) {
        userService.deleteUser(userID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserDTO> getUserList() {
        return userService.getUserList();
    }

}