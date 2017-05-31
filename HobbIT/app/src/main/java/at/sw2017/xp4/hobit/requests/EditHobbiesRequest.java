package at.sw2017.xp4.hobit.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.sw2017.xp4.hobit.Globals;
import at.sw2017.xp4.hobit.R;

/**
 * Created by Michaela on 31.05.2017.
 */

public class EditHobbiesRequest extends StringRequest {

    private static final String EDIT_HOBBIES_URL = "https://waterproofed-school.000webhostapp.com/EditHobbies.php";
    private Map<String, String> params;

    public EditHobbiesRequest(String UserID, List<Boolean> values, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, EDIT_HOBBIES_URL, listener, errorListener);
        params = new HashMap<>();

        params.put("UserID", UserID);

        if(values.get(0) == true)
        {
            params.put("House", "true");
        } else
        {
            params.put("House", "false");
        }

        if(values.get(1) == true)
        {
            params.put("Fashion", "true");
        } else
        {
            params.put("Fashion", "false");
        }

        if(values.get(2) == true)
        {
            params.put("Literature", "true");
        } else
        {
            params.put("Literature", "false");
        }

        if(values.get(3) == true)
        {
            params.put("Games", "true");
        } else
        {
            params.put("Games", "false");
        }

        if(values.get(4) == true)
        {
            params.put("Sports", "true");
        } else
        {
            params.put("Sports", "false");
        }

        if(values.get(5) == true)
        {
            params.put("Crafting", "true");
        } else
        {
            params.put("Crafting", "false");
        }

        if(values.get(6) == true)
        {
            params.put("Computer", "true");
        } else
        {
            params.put("Computer", "false");
        }

        if(values.get(7) == true)
        {
            params.put("Music", "true");
        } else
        {
            params.put("Music", "false");
        }

        if(values.get(8) == true)
        {
            params.put("Dancing", "true");
        } else
        {
            params.put("Dancing", "false");
        }

        if(values.get(9) == true)
        {
            params.put("Art", "true");
        } else
        {
            params.put("Art", "false");
        }

        if(values.get(10) == true)
        {
            params.put("Astronomy", "true");
        } else
        {
            params.put("Astronomy", "false");
        }

        if(values.get(11) == true)
        {
            params.put("Collecting", "true");
        } else
        {
            params.put("Collecting", "false");
        }

        if(values.get(12) == true)
        {
            params.put("Science", "true");
        } else
        {
            params.put("Science", "false");
        }

        if(values.get(13) == true)
        {
            params.put("Racing", "true");
        } else
        {
            params.put("Racing", "false");
        }

        if(values.get(14) == true)
        {
            params.put("TV", "true");
        } else
        {
            params.put("TV", "false");
        }

        if(values.get(15) == true)
        {
            params.put("Traveling", "true");
        } else
        {
            params.put("Traveling", "false");
        }

        if(values.get(16) == true)
        {
            params.put("Extreme", "true");
        } else
        {
            params.put("Extreme", "false");
        }

        if(values.get(17) == true)
        {
            params.put("Health", "true");
        } else
        {
            params.put("Health", "false");
        }

        if(values.get(18) == true)
        {
            params.put("Other", "true");
        } else
        {
            params.put("Other", "false");
        }
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}