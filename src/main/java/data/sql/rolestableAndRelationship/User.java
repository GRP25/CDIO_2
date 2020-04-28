package datalayer.MySQL;

import datalayer.UserDAO;
import dto.UserDTO;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.ArrayList;

import static datalayer.MySQL.Ctrl.*;
import static utilities.CosoleColors.RED_UNDERLINED;
import static utilities.CosoleColors.RESET;

@RequestScoped
public class User implements UserDAO {
    private int getLastUserID(long userCPR) {
        String sql = "SELECT user_id FROM user WHERE user_cpr = ?";
        int    id  = -1;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, userCPR);
            ResultSet rs = pstmt.executeQuery();
            id = rs.getInt("user_id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void create(UserDTO user) {
        String sql = "INSERT INTO user (user_name,user_init,user_cpr,user_password) VALUES(?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getInitials());
            pstmt.setLong(3, user.getCpr());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate();
            setUserGroups(getLastUserID(user.getCpr()), user.getGroup());
        } catch (SQLException e) {
            System.out.println(RED_UNDERLINED + "Another user exists with this CPR number" + RESET);
        }


    }

    @Override
    public ArrayList<UserDTO> selectAll() {
        String sql =
                "SELECT user_id, user_name, user_init, user_cpr, user_password " +
                        "FROM user ";
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
                user.setCpr(rs.getInt("user_cpr"));
                user.setPassword(rs.getString("user_password"));
                user.setGroups(getUserGroups(getUserGroupIDs(id)));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public UserDTO select(int ID) {
        UserDTO user = new UserDTO();
        String sql = "SELECT user_name, user_init, user_cpr, user_password " +
                "FROM user " +
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
            user.setCpr(rs.getInt("user_cpr"));
            user.setPassword(rs.getString("user_password"));
            user.setGroups(getUserGroups(getUserGroupIDs(ID)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public void update(int userID, UserDTO user) {
        String sql = "UPDATE user SET user_name = ? , "
                + "user_init = ? , "
                + "user_cpr = ? , "
                + "user_password = ? "
                + "WHERE user_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getInitials());
            pstmt.setLong(3, user.getCpr());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, userID);
            // update
            pstmt.executeUpdate();
            removeGroupRelations(userID);
            setUserGroups(userID, user.getGroup());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
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

    @Override
    public boolean exists(long userCPR) {
        ArrayList<UserDTO> users = selectAll();
        for (UserDTO u : users) {
            if (u.getCpr() == userCPR) {
                return true;
            }
        }
        return false;
    }

}
