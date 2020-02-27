package data.memory;

import data.IUserDAO;
import data.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO implements IUserDAO {

    HashMap<Integer,UserDTO> userHMap = new HashMap<Integer, UserDTO>();

    @Override
    public UserDTO getUser(int userID) {
        return userHMap.get(userID);
    }

    @Override
    public void updateUser(UserDTO user) {
        UserDTO tempUser = user;
        
    }

    @Override
    public void createUser(UserDTO user) {
        userHMap.put(user.getId(),getUser(user.getId()));
    }

    @Override
    public void deleteUser(int userID) {
        userHMap.remove(userID);
    }

    @Override
    public List<UserDTO> getUserList() {
        List<UserDTO> list = new ArrayList<UserDTO>();

        for(int id : userHMap.keySet()){
           list.add(userHMap.get(id));
       }
        return list;
    }
}