package at.sw2017.xp4.hobit.requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chef on 3.5.2017.
 */

public class InsertHobbiesToDB extends StringRequest{
        private static final String REGISTER_REQUEST_URL = "https://waterproofed-school.000webhostapp.com/InsertHobbiesOfUser.php";
        private Map<String, String> params;

        public InsertHobbiesToDB(String UserID, ArrayList<Integer> hobbies, Response.Listener<String> listener, Response.ErrorListener errorListener){
            super(Method.POST, REGISTER_REQUEST_URL, listener, errorListener);
            params = new HashMap<>();
            params.put("UserID", UserID);

            for(int i = 1; i <= hobbies.size(); i++)
           {
               params.put("Hobby" + i , String.valueOf(hobbies.get(i-1)));
           }
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
}
