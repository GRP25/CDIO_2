package functionality.memory;

import data.IUserDAO;
import data.UserDTO;
import data.memory.UserDAO;
import functionality.IFunc;

import java.util.List;

public class Func implements IFunc {

    private IUserDAO iUserDAO;

    @Override
    public void deleteUser(int userID) {



    }

    @Override
    public void updateUser(UserDTO user) {

    }

    @Override
    public void createUser(UserDTO user) {

        iUserDAO = new UserDAO();

    }

    @Override
    public UserDTO getUser(int userID) {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() {
        return null;
    }
}