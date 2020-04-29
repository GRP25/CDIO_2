package data;

import data.sql.UserDAO;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        IUserDAO db = new UserDAO();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Admin");
        roles.add("Produktionsleder");
        UserDTO user = new UserDTO("Oliver Poulsen", "BM","dontworrybehappy",roles,1,1652022111);
        db.updateUser(user);


        //db.deleteUser(4);





        //UserDTO user = db.getUser(7);
        //System.out.println(user);
        //for(String r : user.getRoles()) {
        //    System.out.println(r);
        //}






        //roles.add("Pharmaceut");
        //UserDTO user = new UserDTO("Oliver Poulsen", "BM","dontworrybehappy",roles,1,1652022111);
        //db.createUser(user);
        //ArrayList<UserDTO> users = db.getUserList();
        //for(UserDTO u : users){
        //    System.out.println(u.getName());
        //    for(String r : u.getRoles()) {
        //        System.out.println(r);
        //    }
        //}
    }
}
