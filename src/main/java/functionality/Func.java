package functionality;

import data.UserDTO;
import data.text.DALException;
import data.*;

import java.util.List;

public class Func implements IFunc {

    IUserDAO data;

    public Func(IUserDAO dataImport) {
        this.data = dataImport;
    }

    @Override
    public void deleteUser(int userID) throws DALException {
        this.data.deleteUser(userID);    
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        this.data.updateUser(user);
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        this.data.createUser(user);

    }

    @Override
    public UserDTO getUser(int userID) throws DALException {

        return this.data.getUser(userID);
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return this.data.getUserList();
    }
}
