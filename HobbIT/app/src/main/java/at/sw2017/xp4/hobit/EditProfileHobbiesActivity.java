package at.sw2017.xp4.hobit;

import android.content.Intent;
import android.support.annotation.BoolRes;
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
import java.util.List;

import at.sw2017.xp4.hobit.requests.EditHobbiesRequest;
import at.sw2017.xp4.hobit.requests.GetUserHobbyIDsRequest;

public class EditProfileHobbiesActivity extends AppCompatActivity {

    public String HOBBY1, HOBBY2, HOBBY3, HOBBY4, HOBBY5;
    public String HOBBY6, HOBBY7, HOBBY8, HOBBY9, HOBBY10;
    public String HOBBY11, HOBBY12, HOBBY13, HOBBY14, HOBBY15;
    public String HOBBY16, HOBBY17, HOBBY18, HOBBY19;
    private RequestQueue queue;


    Button Continue_button;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<String> hobbies = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_hobbies);
        HOBBY1 = this.getString(R.string.hobby_1);
        HOBBY2 = this.getString(R.string.hobby_2);
        HOBBY3 = this.getString(R.string.hobby_3);
        HOBBY4 = this.getString(R.string.hobby_4);
        HOBBY5 = this.getString(R.string.hobby_5);
        HOBBY6 = this.getString(R.string.hobby_6);
        HOBBY7 = this.getString(R.string.hobby_7);
        HOBBY8 = this.getString(R.string.hobby_8);
        HOBBY9 = this.getString(R.string.hobby_9);
        HOBBY10 = this.getString(R.string.hobby_10);
        HOBBY11 = this.getString(R.string.hobby_11);
        HOBBY12 = this.getString(R.string.hobby_12);
        HOBBY13 = this.getString(R.string.hobby_13);
        HOBBY14 = this.getString(R.string.hobby_14);
        HOBBY15 = this.getString(R.string.hobby_15);
        HOBBY16 = this.getString(R.string.hobby_16);
        HOBBY17 = this.getString(R.string.hobby_17);
        HOBBY18 = this.getString(R.string.hobby_18);
        HOBBY19 = this.getString(R.string.hobby_19);


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

        queue = Volley.newRequestQueue(EditProfileHobbiesActivity.this);

        Response.Listener<String> loadListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success)
                    {
                        final JSONArray hobbyIDs = jsonResponse.getJSONArray("HobbyIDs");
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(EditProfileHobbiesActivity.this);
                        builder1.setMessage(hobbyIDs.length()+" elements should be chcked")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                        for(int i = 0; i < hobbyIDs.length(); i++)
                        {

                            int current = hobbyIDs.getInt(i);
                            AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileHobbiesActivity.this);
                            builder.setMessage(current + "should be checked")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                            checkBoxes.get(current).setChecked(true);
                        }

                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileHobbiesActivity.this);
                        builder.setMessage("Something went wrong")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener loadErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileHobbiesActivity.this);
                builder.setMessage("Connection failed")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        };

        GetUserHobbyIDsRequest getUserHobbyIDsRequest = new GetUserHobbyIDsRequest(Globals.getInstance().getUserID(), loadListener, loadErrorListener);
        queue.add(getUserHobbyIDsRequest);


        setOnClickListeners();
    }

        private void setOnClickListeners() {

        final Button contButton = (Button) findViewById(R.id.cont_button);
        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click

//                String Hobbies = "";
//                for(int i = 0; i<=18; i++){
//                    if(checkBoxes.get(i).isChecked())
//                        Hobbies = Hobbies + hobbies.get(i) + "; ";
//                }
//                //finish parsing and sending to database when database is online


               // Intent intent = new Intent(view.getContext(), HobIT_Main.class);
                // startActivity(intent);

                List<Boolean> values = new ArrayList<Boolean>();

                for(int i = 0; i < 19; i++)
                {
                    if(checkBoxes.get(i).isChecked()){
                        values.add(true);
                    }
                    else{
                        values.add(false);
                    }
                }


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if(success)
                            {
                                Intent intent = new Intent(EditProfileHobbiesActivity.this, HobIT_Main.class);
                                startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileHobbiesActivity.this);
                                builder.setMessage("Something went wrong")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileHobbiesActivity.this);
                        builder.setMessage("Connection failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                };

                EditHobbiesRequest editHobbiesRequest = new EditHobbiesRequest(Globals.getInstance().getUserID(), values, responseListener, errorListener);
                queue.add(editHobbiesRequest);

            }
        });

    }
}
