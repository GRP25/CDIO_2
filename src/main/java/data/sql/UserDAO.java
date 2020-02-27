package data.sql;

import java.sql.*;

import static data.sql.Ctrl.connect;

public class UserDAO{
    public void createUser(String userName, String ini, int cpr, String password, int groupID) {
        /**
         * Create a user in the database
         * @param userName
         * @param ini
         * @param cpr
         * @param password
         * @param groupID
         */
        String sql = "INSERT INTO user (user_name,user_init,user_cpr,user_password,group_id) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2,ini);
            pstmt.setInt(3,cpr);
            pstmt.setString(4,password);
            pstmt.setInt(5,groupID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Object[][] selectAllUsers(){
        /**
         * Select all rows in the user table
         */
        String sql =
                "SELECT user.user_id, user.user_name, user.user_init, user.user_cpr, user.user_password, usergroup.group_title " +
                        "FROM user " +
                        "INNER JOIN usergroup ON usergroup.group_id=user.group_id";
        Object[][] users = null;
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            int cols = rs.getMetaData().getColumnCount();
            //int rows = 0;
            // (rs.next())
            //    rows++;
            users = new Object[100][cols];
            int col = 0;
            while (rs.next()) {
                users[col][0] = rs.getInt("user_id");
                users[col][1] = rs.getString("user_name");
                users[col][2] = rs.getString("user_init");
                users[col][3] = rs.getInt("user_cpr");
                users[col][4] = rs.getString("user_password");
                users[col][5] = rs.getString("group_title");
                col++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    public Object[] selectUser(int ID){
        /**
         * Will return an array containing all entities for user with the given id.
         * @param ID
         * @return
         */
        Object[] user = new Object[6];
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
            user[0] = rs.getInt("user_id");
            user[1] = rs.getString("user_name");
            user[2] = rs.getString("user_init");
            user[3] = rs.getInt("user_cpr");
            user[4] = rs.getString("user_password");
            user[5] = rs.getString("group_title");
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