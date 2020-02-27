package data.sql;

import data.GroupDTO;
import data.IGroupDAO;
import data.UserDTO;

import java.sql.*;
import java.util.ArrayList;

import static data.sql.Ctrl.connect;


public class GroupDAO implements IGroupDAO {
    public ArrayList<GroupDTO> selectAllGroups(){
        String sql =
                "SELECT group_id, group_title " +
                        "FROM usergroup";
        ArrayList<GroupDTO> groups = null;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int cols = rs.getMetaData().getColumnCount();
            GroupDTO group = null;
            while (rs.next()) {
                group.setId(rs.getInt("group_id"));
                group.setTitle(rs.getString("group_title"));
                groups.add(group);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return groups;
    }

    public GroupDTO getGroup(int ID) {
        GroupDTO group = null;
        String sql =
                "SELECT group_id, group_title " +
                        "FROM group" +
                        "WHERE group_id = ?;";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1,ID);
            ResultSet rs    = pstmt.executeQuery();
            group.setId(rs.getInt("group_id"));
            group.setTitle(rs.getString("group_title"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return group;
    }
    public void createGroup(String groupTitle) {
        String sql = "INSERT INTO usergroup (group_title) VALUES(?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, groupTitle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteGroup(int groupID) {

    }
    public void updateGroup(int userID, String title) {

    }
}
