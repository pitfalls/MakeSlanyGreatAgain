package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gerd on 03.05.2017.
 */

public class GetUserRequest extends StringRequest {
    private static final String GETUSER_REQUEST_URL = "https://waterproofed-school.000webhostapp.com/GetUser.php";
    private Map<String, String> params;

    public GetUserRequest(String UserID, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, GETUSER_REQUEST_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("UserID", UserID);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
