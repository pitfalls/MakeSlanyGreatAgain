package at.sw2017.xp4.hobit;

/**
 * Created by feldh on 05.04.2017.
 */

/*
*
* 	`_id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL,
	`Description`	TEXT,
	`Location`	TEXT*/

public class Hobby {

    private int id_             = 0;
    private String name_        = "";
    private String location_    = "";
    private String description_ = "";

    public int getId_() {
        return id_;
    }

    public String getName() {
        return name_;
    }

    public String getDescription() {
        return description_;
    }

    public String getLocation() {
        return location_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public void setDescription(String description) {
        this.description_ = description;
    }

    public void setLocation(String location) {
        this.location_ = location;
    }
}
