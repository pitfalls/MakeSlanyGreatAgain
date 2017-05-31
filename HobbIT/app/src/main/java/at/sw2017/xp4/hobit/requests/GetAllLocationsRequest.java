package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gerd on 31.05.2017.
 */

public class GetAllLocationsRequest extends StringRequest
{
    private static final String getAllLocations = "https://waterproofed-school.000webhostapp.com/GetAllLocations.php";
    private Map<String, String> params;

    public GetAllLocationsRequest(Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Request.Method.POST, getAllLocations, listener, errorListener);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
