package at.sw2017.xp4.hobit;

import java.util.ArrayList;

/**
 * Created by Milos on 3.5.2017.
 */

public class Globals {
    private static Globals instance;
    private static String UserID = "";
    private static String TempID = "";
    private static int StartStatus = 0;

    public ArrayList<String> getGlobal_array() {
        return global_array;
    }

    public void setGlobal_array(ArrayList<String> global_array) {
            this.global_array = global_array;
    }

    private ArrayList<String> global_array = new ArrayList<String>();

    public static String getUserID() {
        return UserID;
    }

    public static void setUserID(String userID) {
        UserID = userID;
    }

    public static String getTempID() { return TempID;}

    public static void setTempID(String tempID) { TempID = tempID;}

    public static int getStartStatus() { return StartStatus; }

    public static void setStartStatus(int startStatus) { StartStatus = startStatus; }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance = new Globals();
        }
        return instance;
    }

    private Globals() {}


}
