package datalayer.MySQL;

import datalayer.GroupDAO;
import dto.GroupDTO;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.ArrayList;

import static datalayer.MySQL.Ctrl.*;

@RequestScoped
public class Group implements GroupDAO {
    @Override
    public ArrayList<GroupDTO> selectAll() {
        String sql =
                "SELECT group_id, group_title " +
                        "FROM usergroup";
        ArrayList<GroupDTO> groups = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            GroupDTO group;
            int      id;
            while (rs.next()) {
                id = rs.getInt("group_id");
                group = new GroupDTO();
                group.setId(id);
                group.setName(rs.getString("group_title"));
                try {
                    group.setPermissions(getGroupPermissions(getGroupPermissionIDs(id)));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                groups.add(group);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return groups;
    }

    @Override
    public GroupDTO select(int ID) {
        GroupDTO group = new GroupDTO();
        String sql =
                "SELECT group_id, group_title " +
                        "FROM usergroup " +
                        "WHERE group_id = ?;";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt("group_id");
            group.setId(id);
            group.setName(rs.getString("group_title"));
            group.setPermissions(getGroupPermissions(getGroupPermissionIDs(id)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return group;
    }

    @Override
    public void create(GroupDTO group) {
        String sql = "INSERT INTO usergroup (group_title) VALUES(?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, group.getName());
            pstmt.executeUpdate();
            int id = getLastGroupID();
            removePermissionRelations(id);
            setGroupPermissions(id, group.getPermissions());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int groupID) {
        String sql = "DELETE from usergroup WHERE group_id = ?";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, groupID);
            pstmt.executeUpdate();
            System.out.println("group succsessfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(int id, GroupDTO group) {
        String sql = "UPDATE usergroup SET group_title = ? "
                + "WHERE group_id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, group.getName());
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
            removePermissionRelations(id);
            setGroupPermissions(id, group.getPermissions());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean exists(int id) {
        ArrayList<GroupDTO> users = selectAll();
        for (GroupDTO u : users) {
            if (u.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private int getLastGroupID() {
        String sql = "SELECT group_id FROM usergroup";
        int    id  = -1;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}
