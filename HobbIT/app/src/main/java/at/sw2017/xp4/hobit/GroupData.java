package at.sw2017.xp4.hobit;

/**
 * Created by andy on 03.05.2017.
 */

/**
 * represents a group and all the data elements related with a group
 */
public class GroupData {
    private int id;
    private String name;

    public GroupData(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
