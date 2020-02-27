package functionality;

import data.UserDTO;

import java.util.ArrayList;

public interface IFunc{
    UserDTO getUser(int userID);
    void updateUser(int userID, String Username, String initials, int CPR, String password, ArrayList<Integer> roles);
    void createUser(String Username, String initials, int CPR, String password, ArrayList<Integer> roles);
    void deleteUser(int userID);
    ArrayList<UserDTO> getUserList();
}