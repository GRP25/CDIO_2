package functionality;

import data.UserDTO;
import data.text.DALException;

import java.util.ArrayList;
import java.util.List;

public interface IFunc{

    void deleteUser(int userID) throws DALException;

    void updateUser(int id, int attribute, String change, ArrayList<String> roles) throws DALException;

    void createUser(String name, String initials, String password, ArrayList<String> roles, String cpr) throws DALException;

    UserDTO getUser(int userID) throws DALException;

    List<UserDTO> getUserList() throws DALException;

}