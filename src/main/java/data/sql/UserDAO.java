package data.sql;

import data.IUserDAO;
import data.UserDTO;

import java.sql.*;
import java.util.ArrayList;

import static data.sql.Ctrl.connect;

public class UserDAO implements IUserDAO {
    public void createUser(String userName, String ini, int cpr, String password, ArrayList<Integer> rolesID) {
        /**
         * Create a user in the database
         * @param userName
         * @param ini
         * @param cpr
         * @param password
         * @param groupID
         */
        String createUser = "INSERT INTO user (user_name,user_init,user_cpr,user_password) VALUES(?,?,?,?)";
        String getUserID = "SELECT MAX(user_id) FROM user";
        String createRelationship = "INSERT INTO has_role (user_id,group_id)";

        //First insert new user in userTable
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(createUser)) {
            pstmt.setString(1, userName);
            pstmt.setString(2,ini);
            pstmt.setInt(3,cpr);
            pstmt.setString(4,password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //Next get new users ID and insert new users relations in relation table
        //TODO JEG VED IKKE OM DET HER VIRKER!!
        try (Connection conn = connect();
        PreparedStatement pstmt1 = conn.prepareStatement(getUserID);
        PreparedStatement pstmt2 = conn.prepareStatement(createRelationship)) {
            ResultSet rs    = pstmt1.executeQuery();
            int userID = rs.getInt("user_id");

            for(int roleID : rolesID){
                pstmt2.setInt(0,userID);
                pstmt2.setInt(1,roleID);
                pstmt2.executeUpdate();
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<UserDTO> getUserList(){
        /**
         * Select all rows in the user table
         */
        String sql =
                "SELECT user.user_id, user.user_name, user.user_init, user.user_cpr, user.user_password, usergroup.group_title " +
                        "FROM user " +
                        "INNER JOIN usergroup ON usergroup.group_id=user.group_id";
        String getUserGroups = "SELECT group_id FROM has_role WHERE user_id = ?";
        ArrayList<UserDTO> users = null;
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            ArrayList<Integer> group_ids = null;
            UserDTO user                 = null;
            while (rs.next()) {
                group_ids.clear();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("user_name"));
                user.setInitials(rs.getString("user_init"));
                user.setCpr(rs.getInt("user_cpr"));
                user.setPassword(rs.getString("user_password"));
                user.setRoles(getUserRoles(rs.getInt("user_id")));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    private ArrayList<Integer> getUserRoles(int userID){
        String getUserGroups = "SELECT group_id FROM has_role WHERE user_id = ?";

        ArrayList<Integer> userRoles = null;
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(getUserGroups)) {

            // loop through the result set
            ArrayList<Integer> group_ids = null;
            UserDTO            user      = null;
            while (rs.next()) {
                group_ids.add(rs.getInt("group_id"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userRoles;
    }

    public UserDTO getUser(int ID){
        /**
         * Will return an array containing all entities for user with the given id.
         * @param ID
         * @return
         */
        UserDTO user = null;
        String sql =
                "SELECT user.user_id, user.user_name, user.user_init, user.user_cpr, user.user_password, usergroup.group_title " +
                        "FROM user " +
                        "INNER JOIN usergroup ON usergroup.group_id=user.group_id " +
                        "WHERE user.user_id = ?;";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1,ID);
            ResultSet rs    = pstmt.executeQuery();
            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("user_name"));
            user.setInitials(rs.getString("user_init"));
            user.setCpr(rs.getInt("user_cpr"));
            user.setPassword(rs.getString("user_password"));
            user.setRoles(getUserRoles(rs.getInt("user_id")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public int getUserGroupID(int userID){
        int id = 0;
        String SQL = "SELECT group_id FROM user WHERE user_id = ?";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)
        ) {
            pstmt.setInt(1,userID);
            ResultSet rs    = pstmt.executeQuery();
            id = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    public void updateUser(int id, String userName, String ini, int cpr, String password, int groupID) {
        /**
         * Update user with ID
         * @param id
         * @param userName
         * @param ini
         * @param cpr
         * @param password
         * @param groupID
         */
        String sql = "UPDATE user SET user_name = ? , "
                + "user_init = ? , "
                + "user_cpr = ? , "
                + "user_password = ? , "
                + "group_id = ? "
                + "WHERE user_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, userName);
            pstmt.setString(2, ini);
            pstmt.setInt(3, cpr);
            pstmt.setString(4, password);
            pstmt.setInt(5, groupID);
            pstmt.setInt(6, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteUser(int id){
        /**
         * Deletes user with the given id
         * @param id
         */
        String sql = "DELETE from user WHERE user_id = ?";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            System.out.println("User succsessfully deleted");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}