package functionality.text;

import data.UserDTO;
import data.text.DALException;
import data.text.UserDAO;
import functionality.IFunc;

import java.util.List;

public class Func implements IFunc {

    UserDAO userDAO = new UserDAO();

    @Override
    public void deleteUser(int userID) {

    }

    @Override
    public void updateUser(UserDTO user) {
        //userDAO.updateUser(user.getId(), user.getName(), user.getInitials(), user.getCpr(), user.getPassword(), user.getRoles());

    }

    @Override
    public void createUser(UserDTO user) throws DALException {
            userDAO.createUser(user);
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