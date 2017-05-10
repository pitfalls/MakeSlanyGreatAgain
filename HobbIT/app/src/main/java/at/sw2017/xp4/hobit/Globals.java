package at.sw2017.xp4.hobit;

/**
 * Created by Milos on 3.5.2017.
 */

public class Globals {
    private static Globals instance;
    private static String UserID = "";
    private static String TempID = "";

    public static String getUserID() {
        return UserID;
    }

    public static void setUserID(String userID) {
        UserID = userID;
    }

    public static String getTempID() {
        return TempID;
    }

    public static void setTempID(String tempID) {
        TempID = tempID;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance = new Globals();
        }
        return instance;
    }

    private Globals() {}


}
