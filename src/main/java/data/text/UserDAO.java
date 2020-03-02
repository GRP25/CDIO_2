package data.text;

import data.IUserDAO;
import data.UserDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO implements Serializable {
    HashMap<Integer, UserDTO> userHMap = new HashMap<Integer, UserDTO>();
    String fileName = "C:..";

    private UserStore loadUsers() throws DALException {
        UserStore userStore = new UserStore();
        ObjectInputStream oIS = null;
        try {
            FileInputStream fIS = new FileInputStream(fileName);
            oIS = new ObjectInputStream(fIS);
            Object inObj = oIS.readObject();
            if (inObj instanceof UserStore){
                userStore = (UserStore) inObj;
            } else {
                throw new DALException("Wrong object in file");
            }
        } catch (FileNotFoundException e) {
            //No problem - just returning empty userstore
        } catch (IOException e) {
            throw new DALException("Error while reading disk!", e);
        } catch (ClassNotFoundException e) {
            throw new DALException("Error while reading file - Class not found!", e);
        } finally {
            if (oIS!=null){
                try {
                    oIS.close();
                } catch (IOException e) {
                    throw new DALException("Error closing pObjectStream!", e);
                }
            }
        }
        return userStore;
    }
    private void saveUsers(UserStore users) throws DALException {
        ObjectOutputStream oOS =null;
        try {
            FileOutputStream fOS = new FileOutputStream(fileName);
            oOS = new ObjectOutputStream(fOS);
            oOS.writeObject(users);
        } catch (FileNotFoundException e) {
            throw new DALException("Error locating file", e);
        } catch (IOException e) {
            throw new DALException("Error writing to disk", e);
        } finally {
            if (oOS!=null) {
                try {
                    oOS.close();
                } catch (IOException e) {
                    throw new DALException("Unable to close ObjectStream", e);
                }
            }
        }
    }



        public UserDTO getUser(int userID) {
            return userHMap.get(userID);
        }

        //TODO jeg ved ikke hvordan jeg skal lave den her metode lige nu da der er kommet flere parametre ind.

        public void updateUser(int userID, String Username, String initials, int CPR, String password, ArrayList<String> roles, int id) {

        }

        //TODO snak igennem alle de her parametre fordi nogle af dem giver ingen megning.

        public void createUser(String username, String initials, int CPR, String password, ArrayList<String> roles, int id) {
            UserDTO user = new UserDTO(username,password,roles,id,CPR);
            userHMap.put(user.getId(),user);
        }


        public void deleteUser(int userID) {
            userHMap.remove(userID);
        }


        public List<UserDTO> getUserList() {
            List<UserDTO> list = new ArrayList<UserDTO>();

            for(int id : userHMap.keySet()){
                list.add(userHMap.get(id));
            }
            return list;
        }

}