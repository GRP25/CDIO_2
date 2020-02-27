package data;

//import data.memory.UserDAO;

import java.util.ArrayList;

public interface IUserDAO{
    UserDTO getUser(int userID);
    void updateUser(int userID, String Username, String initials, int CPR, String password, ArrayList<Integer> roles);
    void createUser(String Username, String initials, int CPR, String password, ArrayList<Integer> roles);
    void deleteUser(int userID);
    ArrayList<UserDTO> getUserList();
}