package at.sw2017.xp4.hobit;

import java.util.ArrayList;

import at.sw2017.xp4.hobit.GroupData;

/**
 * Created by Herr Milos on 17.5.2017.
 */

public class HobbyData {

    private String name;
    private ArrayList<GroupData> groupList = new ArrayList<>();

    public HobbyData(String name, ArrayList<GroupData> groupList) {
        super();
        this.name = name;
        this.groupList = groupList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<GroupData> getGroupList() {
        return groupList;
    }

}
