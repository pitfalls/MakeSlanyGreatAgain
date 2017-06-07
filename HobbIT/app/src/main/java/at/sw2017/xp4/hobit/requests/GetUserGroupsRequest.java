package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Milos on 17.5.2017.
 */

public class GetUserGroupsRequest extends StringRequest{

    private static final String GET_USER_GROUPS_URL = "https://waterproofed-school.000webhostapp.com/GetUserGroups.php";
    private Map<String, String> params;

    public GetUserGroupsRequest(Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Request.Method.POST, GET_USER_GROUPS_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("userID", "1" );
        /*Globals.getInstance().getUserID()*/
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
