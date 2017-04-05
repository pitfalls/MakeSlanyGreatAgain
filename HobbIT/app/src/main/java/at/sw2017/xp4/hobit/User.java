package at.sw2017.xp4.hobit;

/**
 * Created by feldh on 05.04.2017.
 */

private class User {

    private String  id_ = "";
    private String  nickName_ = "";
    private String  firstName_ = "";
    private String  surname_ = "";
    private String  location_ = "";

    public String getId() {
        return id_;
    }

    public String getNickName() {
        return nickName_;
    }

    public String getFirstName() {
        return firstName_;
    }

    public String getSurname() {
        return surname_;
    }

    public String getLocation() {
        return location_;
    }

    public void setId(String id_) {

        this.id_ = id_;
    }

    public void setNickName(String nickName_) {
        this.nickName_ = nickName_;
    }

    public void setFirstName(String firstName_) {
        this.firstName_ = firstName_;
    }

    public void setSurname(String surname_) {
        this.surname_ = surname_;
    }

    public void setLocation(String location_) {
        this.location_ = location_;
    }
    


    User(String id, String nickName, String firstName, String surname , String location)
    {

        id_ = id;
        nickName_ = nickName;
        firstName_ = firstName;
        surname_ = surname;
        location_ = location;

    }
}
