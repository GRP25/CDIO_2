package data;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;



public interface IUserDAO{
    UserDTO getUser(int userID);
    void updateUser(UserDTO user);
    void createUser(UserDTO user);
    void deleteUser(int userID);
    ArrayList<UserDTO> getUserList();
    boolean exists(int id);
    boolean exists(String cpr);
}
