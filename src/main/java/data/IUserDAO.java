package data;

//import data.memory.UserDAO;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public interface IUserDAO{
    UserDTO getUser(int userID);
    void updateUser(UserDTO user);
    void createUser(UserDTO user);
    void deleteUser(int userID);
    ArrayList<UserDTO> getUserList();
    boolean exists(int id);
    boolean exists(long cpr);
}
