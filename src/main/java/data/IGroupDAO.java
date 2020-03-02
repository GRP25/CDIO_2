package data;

import java.util.ArrayList;

public interface IGroupDAO {
    //GROUP STUFF
    ArrayList<GroupDTO> selectAllGroups();
    GroupDTO getGroup (int ID);
    void createGroup(String groupTitle);
    void updateGroup(int userID, String title);
    void deleteGroup(int groupID);

}
