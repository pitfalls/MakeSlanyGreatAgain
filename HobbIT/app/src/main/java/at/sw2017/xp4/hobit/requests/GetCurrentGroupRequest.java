package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christof on 07.06.2017.
 */

public class GetCurrentGroupRequest extends StringRequest {
    private static final String GET_CURRENT_GROUP_REQUEST_URL = "https://waterproofed-school.000webhostapp.com/GetCurrentGroup.php";
    private Map<String, String> params;

    public GetCurrentGroupRequest(String Name, Response.Listener<String> listener, Response.ErrorListener errorListener ){
        super(Request.Method.POST, GET_CURRENT_GROUP_REQUEST_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("Name", Name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


