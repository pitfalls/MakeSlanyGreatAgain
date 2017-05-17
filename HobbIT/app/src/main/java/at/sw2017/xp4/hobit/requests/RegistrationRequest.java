package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Milos on 3.5.2017.
 */

public class RegistrationRequest extends StringRequest{
        private static final String REGISTER_REQUEST_URL = "https://waterproofed-school.000webhostapp.com/Registration.php";
        private Map<String, String> params;

        public RegistrationRequest(String UserID, String NickName, String FirstName, String SurName, String Location, String Email, String Password, Response.Listener<String> listener, Response.ErrorListener errorListener){
            super(Request.Method.POST, REGISTER_REQUEST_URL, listener, errorListener);
            params = new HashMap<>();
            params.put("UserID", UserID);
            params.put("NickName", NickName);
            params.put("FirstName", FirstName);
            params.put("SurName", SurName);
            params.put("Location", Location);
            params.put("Email", Email);
            params.put("Password", Password);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
}
