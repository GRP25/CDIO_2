package data.sql;

import data.IUserDAO;
import data.UserDTO;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.ArrayList;

import static data.sql.Ctrl.*;

@RequestScoped
public class UserDAO implements IUserDAO {

    @Override
    public void createUser(UserDTO user) {
        String sql = "INSERT INTO user (user_name,user_init,user_cpr,user_password) VALUES(?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getInitials());
            pstmt.setLong(3, user.getCpr());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate();

            // getting id from last inserted user
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");

            String ID = "";
            while (rs.next()) {
                ID = rs.getString("LAST_INSERT_ID()");
            }
            
            // assigning roles to user
            sql = "INSERT INTO has_roles (user_id, roles_title) VALUES ";
            for (String role :  user.getRoles()) {
                sql += "('" + ID + "', '" + role + "'),";
            }
            sql = sql.substring(0,sql.length() - 1);
            stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<UserDTO> getUserList() {
        String sql = "SELECT * FROM user";
        ArrayList<UserDTO> users = new ArrayList<>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            UserDTO user;
            int     id;
            while (rs.next()) {
                user = new UserDTO();
                id = rs.getInt("user_id");
                user.setId(id);
                user.setName(rs.getString("user_name"));
                user.setInitials(rs.getString("user_init"));
                user.setCpr(rs.getLong("user_cpr"));
                user.setPassword(rs.getString("user_password"));
            //    user.setRoles(stringToGroup(rs.getString("user_groups")));
                users.add(user);
            }

            for (UserDTO userTemp : users) {
                stmt = conn.createStatement();
                userTemp.setRoles(get_user_roles(userTemp.getId(), stmt));
            }

            // closing connections
            conn.close();
            stmt.close();
            rs.close();
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    @Override
    public UserDTO getUser(int ID) {
        UserDTO user = new UserDTO();
        String sql = "SELECT * FROM user WHERE user_id = ?;";

        try
         {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();
             if(rs.next()) {
                 user.setId(ID);
                 user.setName(rs.getString("user_name"));
                 user.setInitials(rs.getString("user_init"));
                 user.setCpr(rs.getLong("user_cpr"));
                 user.setPassword(rs.getString("user_password"));
                 user.setRoles(get_user_roles(ID, pstmt));
             }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public void updateUser(UserDTO user) {
        String sql = "UPDATE user SET user_name = ? , "
                + "user_init = ? , "
                + "user_cpr = ? , "
                + "user_password = ? "
                + "WHERE user_id = ?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getInitials());
            pstmt.setLong(3, user.getCpr());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getId());
            // update
            pstmt.executeUpdate();

            // assigning roles to user
            sql = "DELETE FROM has_roles WHERE user_id = "+user.getId();
            pstmt.executeQuery(sql);
            sql = "INSERT INTO has_roles (user_id, roles_title) VALUES ";
            for (String role :  user.getRoles()) {
                sql += "('" + user.getId() + "', '" + role + "'),";
            }
            sql = sql.substring(0,sql.length() - 1);
            pstmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE from user WHERE user_id = ?";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("User succsessfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<String> get_user_roles(int id, Statement stmt) throws SQLException{
        String sql = "SELECT roles_title FROM has_roles WHERE user_id=" + id;
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<String> roleList = new ArrayList<>();

        while (rs.next()) {
            roleList.add(rs.getString("roles_title"));
        }

        return roleList;
    }
}
