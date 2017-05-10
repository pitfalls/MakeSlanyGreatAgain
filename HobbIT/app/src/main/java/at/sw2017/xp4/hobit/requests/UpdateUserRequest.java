package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gerd on 10.05.2017.
 */

public class UpdateUserRequest extends StringRequest {
    private static final String UPDATEUSER_REQUEST_URL = "https://waterproofed-school.000webhostapp.com/UpdateUser.php";
    private Map<String, String> params;

    public UpdateUserRequest(String UserID, String NickName, String FirstName, String SurName, String Location, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Request.Method.POST, UPDATEUSER_REQUEST_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("UserID", UserID);
        params.put("NickName", NickName);
        params.put("FirstName", FirstName);
        params.put("SurName", SurName);
        params.put("Location", Location);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
