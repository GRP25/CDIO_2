package functionality;

import data.UserDTO;
import data.text.DALException;

import java.util.List;

public interface IFunc{

    void deleteUser(int userID) throws DALException;

    void updateUser(UserDTO user) throws DALException;

    void createUser(UserDTO user) throws DALException;

    UserDTO getUser(int userID) throws DALException;

    List<UserDTO> getUserList() throws DALException;


}