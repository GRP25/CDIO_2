import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.ArrayList;

@RequestScoped
public class Ctrl {


    static void removeGroupRelations(int userID) {
        String sql = "DELETE from has_group WHERE user_id = ?";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void removePermissionRelations(int id) {
        String sql = "DELETE from has_permission WHERE group_id = ?";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static ArrayList<GroupDTO> getUserGroups(ArrayList<Integer> groupIDs) {
        ArrayList<GroupDTO> groups = new ArrayList<>();
        GroupDAO            link   = new Group();
        GroupDTO            group;
        for (int id : groupIDs) {
            group = link.select(id);
            groups.add(group);
        }
        return groups;
    }

    static ArrayList<Integer> getUserGroupIDs(int userID) {
        String             sql         = "SELECT group_id FROM has_group WHERE user_id = ?";
        ArrayList<Integer> userRolesID = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                userRolesID.add(rs.getInt("group_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userRolesID;
    }

    static void setUserGroups(int UserID, ArrayList<GroupDTO> groups) {
        String sql = "INSERT INTO has_group (user_id, group_id) VALUES(?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (GroupDTO group : groups) {
                pstmt.setInt(1, UserID);
                pstmt.setInt(2, group.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    static ArrayList<PermissionDTO> getGroupPermissions(ArrayList<Integer> ids) {
        ArrayList<PermissionDTO> permissions = new ArrayList<>();
        PermissionDAO            link        = new Permission();
        PermissionDTO            permission;
        for (int id : ids) {
            permission = link.select(id);
            permissions.add(permission);
        }
        return permissions;
    }

    static ArrayList<Integer> getGroupPermissionIDs(int id) {
        String             sql      = "SELECT permission_id FROM has_permission WHERE group_id = ?";
        ArrayList<Integer> groupIds = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                groupIds.add(rs.getInt("permission_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return groupIds;
    }

    static void setGroupPermissions(int id, ArrayList<PermissionDTO> permissions) {
        String sql = "INSERT INTO has_permission (group_id, permission_id) VALUES(?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (PermissionDTO permission : permissions) {
                pstmt.setInt(1, id);
                pstmt.setInt(2, permission.getId());
                pstmt.executeUpdate();
            }
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
