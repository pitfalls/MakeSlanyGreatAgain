package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anon on 10.05.17.
 */

public class joinGroupRequest extends StringRequest{

    private static final String JOIN_REQUEST_URL = "https://waterproofed-school.000webhostapp.com/JoinGroup.php";
    private Map<String, String> params;

    public joinGroupRequest(String userId, String groupId,Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Request.Method.POST, JOIN_REQUEST_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("userID", userId);
        params.put("groupID", groupId);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
