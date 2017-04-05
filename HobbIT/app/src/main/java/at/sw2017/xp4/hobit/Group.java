package at.sw2017.xp4.hobit;

/**
 * Created by feldh on 05.04.2017.
 */

public class Group {

    private int id_             = 0;
    private int hobby_          = 0;
    private String name_        = "";
    private String description_ = "";

    Group(int id, int hobby, String name, String description)
    {
        id_           = id;
        hobby_        = hobby;
        name_         = name;
        description_  = description;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public int getHobby_() {
        return hobby_;
    }

    public void setHobby_(int hobby_) {
        this.hobby_ = hobby_;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public void setDescription(String description) {
        this.description_ = description;
    }

    public Object getName() {
        return name_;
    }

    public Object getDescription() {
        return description_;
    }
}
