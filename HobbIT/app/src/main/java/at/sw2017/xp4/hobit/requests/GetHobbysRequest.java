package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import at.sw2017.xp4.hobit.Globals;

/**
 * Created by Christof on 17.05.2017.
 */

public class GetHobbysRequest extends StringRequest
{
    private static final String getUserHobbys = "https://waterproofed-school.000webhostapp.com/getUserHobbys.php";
    private Map<String, String> params;

    public GetHobbysRequest(Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Request.Method.POST, getUserHobbys, listener, errorListener);
        params = new HashMap<>();
        params.put("UserID", Globals.getUserID());
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
