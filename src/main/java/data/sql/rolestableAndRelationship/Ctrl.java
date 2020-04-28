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
}
