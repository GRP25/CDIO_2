package functionality;

import data.UserDTO;

import java.util.List;

public interface IFunc{

    void deleteUser(int userID);

    void updateUser(UserDTO user);

    void createUser(UserDTO user);

    UserDTO getUser(int userID);

    List<UserDTO> getUserList();


}