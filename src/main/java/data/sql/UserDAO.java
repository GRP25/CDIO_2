package data.sql;

import data.IUserDAO;
import data.UserDTO;

import java.sql.*;
import java.util.ArrayList;

import static data.sql.Ctrl.*;

public class UserDAO implements IUserDAO {

    public void createUser(UserDTO user) {
        String sql = "INSERT INTO user (user_name,user_init,user_cpr,user_password, user_groups) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getInitials());
            pstmt.setLong(3, user.getCpr());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, groupToString(user.getRoles()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Another user exists with this CPR number");
        }
    }

    public ArrayList<UserDTO> getUserList() {
        String sql =
                "SELECT * FROM user";
        ArrayList<UserDTO> users = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
                user.setRoles(stringToGroup(rs.getString("user_groups")));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public UserDTO getUser(int ID) {
        UserDTO user = new UserDTO();
        String sql = "SELECT * FROM user" +
                "WHERE user_id = ?;";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();
            user.setId(ID);
            user.setName(rs.getString("user_name"));
            user.setInitials(rs.getString("user_init"));
            user.setCpr(rs.getLong("user_cpr"));
            user.setPassword(rs.getString("user_password"));
            user.setRoles(stringToGroup(rs.getString("user_groups")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void updateUser(UserDTO user) {
        String sql = "UPDATE user SET user_name = ? , "
                + "user_init = ? , "
                + "user_cpr = ? , "
                + "user_password = ? "
                + "user_groups = ? "
                + "WHERE user_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getInitials());
            pstmt.setLong(3, user.getCpr());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getId());
            pstmt.setString(6, groupToString(user.getRoles()));
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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

}
