    // SKAL RETURNERE ET STRING ARRAY
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
}
