package data;

import data.sql.UserDAO;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        IUserDAO db = new UserDAO();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Admin");
        roles.add("Foreman");
        UserDTO user = new UserDTO("Bob Marley", "BM","dontworrybehappy",roles,1,1202921111);
        db.createUser(user);
        ArrayList<UserDTO> users = db.getUserList();
        for(UserDTO u : users){
            System.out.println(u.getName());
        }
    }
}
