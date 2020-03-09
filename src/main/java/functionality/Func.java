package functionality;

import data.UserDTO;
import data.text.DALException;

import java.util.List;

public class Func implements IFunc {

    @Override
    public void deleteUser(int userID) throws DALException {

    }

    @Override
    public void updateUser(UserDTO user) {

    }

    @Override
    public void createUser(UserDTO user) throws DALException {

    }

    @Override
    public UserDTO getUser(int userID) throws DALException {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return null;
    }
}
