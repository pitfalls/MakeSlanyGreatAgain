package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Milos on 26.4.2017.
 */

public class GroupCreationRequest extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "https://waterproofed-school.000webhostapp.com/GroupCreation.php";
    private Map<String, String> params;

    public GroupCreationRequest(String name, String description, String hobby, String location, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("description", description);
        params.put("hobby", hobby);
        params.put("location", location);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
