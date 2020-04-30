package rest;

import data.UserDTO;

import java.util.ArrayList;

public interface UserService
{
    UserDTO getUser(int userID);
    void updateUser(UserDTO user);
    void createUser(UserDTO user);
    void deleteUser(int userID);
    ArrayList<UserDTO> getUserList();

}
