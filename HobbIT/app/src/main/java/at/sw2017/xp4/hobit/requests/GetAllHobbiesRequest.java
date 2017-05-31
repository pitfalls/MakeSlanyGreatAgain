package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gerd on 31.05.2017.
 */

public class GetAllHobbiesRequest extends StringRequest
{
    private static final String getAllHobbys = "https://waterproofed-school.000webhostapp.com/GetAllHobbies.php";
    private Map<String, String> params;

    public GetAllHobbiesRequest(Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Request.Method.POST, getAllHobbys, listener, errorListener);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
