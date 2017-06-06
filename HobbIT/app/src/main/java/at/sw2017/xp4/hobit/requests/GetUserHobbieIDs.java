package at.sw2017.xp4.hobit.requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import at.sw2017.xp4.hobit.Globals;

/**
 * Created by Feldferdl #7 on 6.6.2017.
 */

public class GetUserHobbieIDs extends StringRequest
{
    private static final String getUserHobbys = "https://waterproofed-school.000webhostapp.com/GetUserHobbyIDs.php";
    private Map<String, String> params;

    public GetUserHobbieIDs(Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, getUserHobbys, listener, errorListener);
        params = new HashMap<>();
        params.put("UserID", Globals.getUserID());
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }
}
