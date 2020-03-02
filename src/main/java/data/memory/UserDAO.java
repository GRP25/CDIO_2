package data.memory;//package data.memory;

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

    //TODO jeg ved ikke hvordan jeg skal lave den her metode lige nu da der er kommet flere parametre ind.
    @Override
    public void updateUser(int userID, String Username, String initials, int CPR, String password, ArrayList<String> roles, int id) {

    }

    //TODO snak igennem alle de her parametre fordi nogle af dem giver ingen megning.
    @Override
    public void createUser(String username, String initials, int CPR, String password, ArrayList<String> roles, int id) {
        UserDTO user = new UserDTO(username,password,roles,id,CPR);
        userHMap.put(user.getId(),user);
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