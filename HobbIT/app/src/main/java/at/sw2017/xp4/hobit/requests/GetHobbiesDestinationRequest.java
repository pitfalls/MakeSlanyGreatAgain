package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anon on 03.05.17.
 */

public class GetHobbiesDestinationRequest extends StringRequest {

    private static final String GET_HOBBIES_DEST_URL = "https://waterproofed-school.000webhostapp.com/getHobbieGroups_Locations.php";
    private Map<String, String> params;


    public GetHobbiesDestinationRequest(Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Request.Method.POST, GET_HOBBIES_DEST_URL, listener, errorListener);
        params = new HashMap<>();
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }


}



