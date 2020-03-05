package data;

//import data.memory.UserDAO;

import data.text.DALException;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO{
    UserDTO getUser(int userID) throws DALException;
    void updateUser(UserDTO user) throws DALException;
    void createUser(UserDTO user) throws DALException;
    void deleteUser(int userID) throws DALException;
    ArrayList<UserDTO> getUserList() throws DALException;
}