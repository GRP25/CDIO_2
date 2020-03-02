package data;

//import data.memory.UserDAO;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO{
    UserDTO getUser(int userID);
    void updateUser(int userID, String username, String initials, int CPR, String password, ArrayList<String> roles, int id);
    void createUser(String username, String initials, int CPR, String password, ArrayList<String> roles, int id);
    void deleteUser(int userID);
    List<UserDTO> getUserList();
}