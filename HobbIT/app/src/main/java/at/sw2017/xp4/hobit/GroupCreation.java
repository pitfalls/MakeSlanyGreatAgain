package at.sw2017.xp4.hobit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import at.sw2017.xp4.hobit.requests.GetAllHobbiesRequest;
import at.sw2017.xp4.hobit.requests.GetAllLocationsRequest;
import at.sw2017.xp4.hobit.requests.GetHobbysRequest;
import at.sw2017.xp4.hobit.requests.GetUserRequest;
import at.sw2017.xp4.hobit.requests.GroupCreationRequest;

public class GroupCreation extends AppCompatActivity {

    private String GroupHobby = "";
    private String GroupLocation = "";

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);

        queue = Volley.newRequestQueue(GroupCreation.this);

        createHobbySpinner();
        createLocationSpinner();
        saveGroupCreation();
        cancelGroupCreation();
    }

    public void createHobbySpinner()
    {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item);//, spinnerArray);

        Response.Listener<String> getHobbiesResponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    JSONArray hobbies = jsonResponse.getJSONArray("hobbies");
                    for (int i = 0; i < hobbies.length(); i++) {
                        adapter.add(hobbies.get(i).toString());
                    }

                    Spinner spinner = (Spinner)findViewById(R.id.spinner_hobby);

                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            GroupHobby = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            GroupHobby = "";
                        }
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        };

        Response.ErrorListener getHobbiesErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroupCreation.this);
                builder.setMessage("Connection failed")
                        .setNegativeButton("OK", null)
                        .create()
                        .show();
            }
        };

        GetAllHobbiesRequest getHobbiesRequest = new GetAllHobbiesRequest(
                getHobbiesResponseListener, getHobbiesErrorListener);

        queue.add(getHobbiesRequest);
    }

    public void createLocationSpinner()
    {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item);//, spinnerArray);

        Response.Listener<String> getLocationsResponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        JSONArray hobbies = jsonResponse.getJSONArray("locations");
                        for (int i = 0; i < hobbies.length(); i++) {
                            adapter.add(hobbies.get(i).toString());
                        }

                        Spinner spinner = (Spinner)findViewById(R.id.spinner_location);

                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                GroupLocation = parent.getItemAtPosition(position).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                GroupLocation = "";
                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener getLocationsErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GroupCreation.this);
            builder.setMessage("Connection failed")
                    .setNegativeButton("OK", null)
                    .create()
                    .show();
            }
        };

        GetAllLocationsRequest getHobbiesRequest = new GetAllLocationsRequest(
                getLocationsResponseListener, getLocationsErrorListener);

        queue.add(getHobbiesRequest);
    }

    public void saveGroupCreation()
    {
        final EditText DescText = (EditText) findViewById(R.id.editText_Description); //TODO /n in description is not allowed
        final EditText NameText = (EditText) findViewById(R.id.editText_Groupname);

        Button ButtonSave = (Button) findViewById(R.id.btn_save);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Context context = getApplicationContext();
            CharSequence text = "Group Saved";
            int duration = Toast.LENGTH_LONG;
            Toast save_toast = Toast.makeText(context, text, duration);
            save_toast.show();
            String GroupDescription = DescText.getText().toString();
            String GroupName = NameText.getText().toString();

            //Write to database
            if(GroupDescription.length()>0 && GroupName.length()>0 ) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(GroupCreation.this, HobIT_Main.class);
                                GroupCreation.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(GroupCreation.this);
                                builder.setMessage("Group Creation failed")
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(GroupCreation.this);
                        builder.setMessage("Connection failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                };

                GroupCreationRequest groupCreationRequest =
                        new GroupCreationRequest(GroupName, GroupDescription, GroupHobby,
                                GroupLocation, responseListener, errorListener);

                queue.add(groupCreationRequest);

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroupCreation.this);
                builder.setMessage("Please fill in the Name and Description fields")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
            }
        });
    }

    public void cancelGroupCreation()
    {
        Button ButtonSave = (Button) findViewById(R.id.btn_cancel);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Group Canceled";
                int duration = Toast.LENGTH_LONG;
                Toast save_toast = Toast.makeText(context, text, duration);
                save_toast.show();
                Intent intent = new Intent(GroupCreation.this, HobIT_Main.class);
                startActivity(intent);
            }
        });
    }
}
