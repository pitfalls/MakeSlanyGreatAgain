package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michaela on 31.05.2017.
 */

public class GetUserHobbyIDsRequest extends StringRequest {

    private static final String GET_USER_HOBBY_IDS_URL = "https://waterproofed-school.000webhostapp.com/GetUserHobbyIDs.php";
    private Map<String, String> params;

    public GetUserHobbyIDsRequest(String UserID, Response.Listener<String> listener, Response.ErrorListener errorListener) {

        super(Request.Method.POST, GET_USER_HOBBY_IDS_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("UserID", UserID);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
