package rest;

import data.UserDTO;
import functionality.NotACPRException;
import functionality.NotANameException;
import functionality.NotAValidPasswordException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static functionality.Conversion.cprConversion;
import static functionality.Conversion.nameConversion;
import static functionality.Validation.*;


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
        try {
            nameValidator(nameConversion(user.getName()));
            cprValidator(cprConversion(user.getCpr()));
            passwordValidator(user.getName(), user.getPassword());
        }
        catch (NotANameException | NotACPRException | NotAValidPasswordException e) {
            System.out.println("Invalid Name, CPR or PW");
        }
        userService.updateUser(user);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(UserDTO user) {
        try {
            nameValidator(nameConversion(user.getName()));
            cprValidator(cprConversion(user.getCpr()));
            passwordValidator(user.getName(), user.getPassword());
        }
        catch (NotANameException | NotACPRException | NotAValidPasswordException e) {
            System.out.println("Invalid Name, CPR or PW");
        }
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