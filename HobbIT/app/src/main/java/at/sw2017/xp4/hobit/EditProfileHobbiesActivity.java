package at.sw2017.xp4.hobit;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import at.sw2017.xp4.hobit.requests.GetHobbysRequest;
import at.sw2017.xp4.hobit.requests.GetUserHobbieIDs;
import at.sw2017.xp4.hobit.requests.GetUserRequest;
import at.sw2017.xp4.hobit.requests.InsertHobbiesToDB;

public class EditProfileHobbiesActivity extends AppCompatActivity {

    public static final String HOBBY1 = "Swimming";
    public static final String HOBBY2 = "Hiking";
    public static final String HOBBY3 = "Fitness studio";
    public static final String HOBBY4 = "Playing cards";
    public static final String HOBBY5 = "Computer games";
    public static final String HOBBY6 = "Solve quiz";
    public static final String HOBBY7 = "Ride a bicycle";
    public static final String HOBBY8 = "Puzzle";
    public static final String HOBBY9 = "Needle";
    public static final String HOBBY10 = "Model making";
    public static final String HOBBY11 = "Yoyo playing";
    public static final String HOBBY12 = "Dancing";
    public static final String HOBBY13 = "Climbing";
    public static final String HOBBY14 = "Astronomy";
    public static final String HOBBY15 = "Collecting";
    public static final String HOBBY16 = "Cooking";
    public static final String HOBBY17 = "Football";
    public static final String HOBBY18 = "Music";
    public static final String HOBBY19 = "others";

    public ArrayList<Integer> User_Hobbys = new ArrayList<>();
    Button Continue_button;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<Integer> checkedCheckboxes = new ArrayList<>();
    private ArrayList<String> hobbies = new ArrayList<>();


    public void toArrayList(JSONArray array) {
        if (array == null)
            return;

        if(User_Hobbys.size() == 0)
        {
            for (int i = 0; i < array.length(); i++) {
                User_Hobbys.add(Integer.valueOf(array.optString(i)));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_hobbies);

        hobbies.add(HOBBY1);
        hobbies.add(HOBBY2);
        hobbies.add(HOBBY3);
        hobbies.add(HOBBY4);
        hobbies.add(HOBBY5);
        hobbies.add(HOBBY6);
        hobbies.add(HOBBY7);
        hobbies.add(HOBBY8);
        hobbies.add(HOBBY9);
        hobbies.add(HOBBY10);
        hobbies.add(HOBBY11);
        hobbies.add(HOBBY12);
        hobbies.add(HOBBY13);
        hobbies.add(HOBBY14);
        hobbies.add(HOBBY15);
        hobbies.add(HOBBY16);
        hobbies.add(HOBBY17);
        hobbies.add(HOBBY18);
        hobbies.add(HOBBY19);

        for (int i = 1; i <= 19; i++) {
            String checkBoxName = "checkBox" + i;
            int id = getResources().getIdentifier(checkBoxName, "id", R.class.getPackage().getName());
            CheckBox checkBox = (CheckBox) findViewById(id);
            checkBoxes.add(checkBox);
        }

        setOnClickListeners();
//-----------------
//-----------------
//-----------------
//-----------------
//-----------------

        final Response.Listener<String> GroupResponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonResponse = new JSONObject(response);
                    final JSONArray allHobbies = jsonResponse.getJSONArray("HobbyIDs");
                    toArrayList(allHobbies);

                    for (int hobby: User_Hobbys) {
                        checkBoxes.get(hobby-1).setChecked(true);

                    }
                }
                catch (JSONException e)
                {
                    //   printDebugToast("Probleme");
                    e.printStackTrace();
                }
            }
        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileHobbiesActivity.this);
                builder.setMessage("you are not good enough to join!! ;-)")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        };

        GetUserHobbieIDs getUserHobbieIDs = new GetUserHobbieIDs(GroupResponseListener, errorListener);
        final RequestQueue queue = Volley.newRequestQueue(EditProfileHobbiesActivity.this);
        queue.add(getUserHobbieIDs);



//-------------------
//-------------------
//-------------------
//-------------------
//-------------------


    }

        private void setOnClickListeners() {


        final Button contButton = (Button) findViewById(R.id.cont_button);
        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkedCheckboxes.clear();
                for (int i = 1; i <= 19; i++) {
                    String checkBoxName = "checkBox" + i;
                    int id = getResources().getIdentifier(checkBoxName, "id", R.class.getPackage().getName());
                    CheckBox checkBox = (CheckBox) findViewById(id);
                    if(checkBox.isChecked())
                        checkedCheckboxes.add(i);
                }



                final Response.Listener<String> GroupResponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                };

                final Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileHobbiesActivity.this);
                        builder.setMessage("...")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                };

                InsertHobbiesToDB insertHobbiesToDB = new InsertHobbiesToDB(Globals.getInstance().getUserID(), checkedCheckboxes ,
                        GroupResponseListener, errorListener);

                RequestQueue queue = Volley.newRequestQueue(EditProfileHobbiesActivity.this);
                queue.add(insertHobbiesToDB);


                // This Perform action on click

//                String Hobbies = "";
//                for(int i = 0; i<=18; i++){
//                    if(checkBoxes.get(i).isChecked())
//                        Hobbies = Hobbies + hobbies.get(i) + "; ";
//                }
//                //finish parsing and sending to database when database is online

                Intent intent = new Intent(view.getContext(), HobIT_Main.class);
                startActivity(intent);
            }
        });

    }
}
