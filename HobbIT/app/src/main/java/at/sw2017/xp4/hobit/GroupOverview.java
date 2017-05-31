package at.sw2017.xp4.hobit;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

import at.sw2017.xp4.hobit.requests.GetHobbiesDestinationRequest;
import at.sw2017.xp4.hobit.requests.joinGroupRequest;

import de.greenrobot.event.EventBus;

public class GroupOverview extends AppCompatActivity {

    GroupData group;


    private void setAdapterContent(List<String> content)
    {
      adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, content);
    }

    List<String> fullHobbyList = new ArrayList<String>();
    List<String> hobbyList = new ArrayList<String>();
    List<String> fullLocationList = new ArrayList<String>();
    List<String> locationList = new ArrayList<String>();
    List<String> fullGroupList = new ArrayList<String>();
    List<String> groupList = new ArrayList<String>();

    String currentId = "";
    TextView descriptionView ;
    String[][] spinnerArray;
    ArrayAdapter<String> adapter;
    Spinner spinnerHobby, spinnerLocation, spinnerGroup;
    EditText groupInput,locationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_overview);

        setTitle("<GroupName>");
        setOnClickListeners();
        setOnClickListeners();

        getObjectFromEventQueue();
    }

    private void getObjectFromEventQueue ()
    {
        group = (GroupData) EventBus.getDefault().removeStickyEvent(GroupData.class);

        if (group == null)
        {
            group = new GroupData("Default Group", 0);
        }

        ///@todo place data of group object in the corresponding textfields
    }



    //----------------------------------------------------------------------------------------------
    void HandleSpinnercontent(String groupFilter, String locationFilter)
    {
        hobbyList.clear();
        locationList.clear();
        groupList.clear();

        if (groupFilter.equals("") && locationFilter.equals(""))
        {
            for (String currentHobby : fullHobbyList)
            {
                hobbyList.add(currentHobby);
            }

            for (String currentLocation : fullLocationList)
            {
                locationList.add(currentLocation);
            }

            for (String currentGroup : fullGroupList)
            {
                groupList.add(currentGroup);
            }

            UpdateSpinner();

            currentId = spinnerArray[4][0];
            descriptionView.setText(spinnerArray[3][0]);

            return;
        }

        boolean idSet = false;
        int idPosition = 0;

        if (groupFilter.equals(""))
        {
            for(int iter = 0; iter < spinnerArray[0].length; iter++)
            {
                // [1] location
                if (spinnerArray[1][iter].contains(locationFilter))
                {
                    AddToSpinner(iter);
                    if (!idSet)
                    {
                        idSet = true;
                        idPosition = iter;
                    }
                }
            }

        }
        else if(locationFilter.equals(""))
        {
            for(int iter = 0; iter < spinnerArray[0].length; iter++)
            {
                // [2] group
                if (spinnerArray[2][iter].contains(groupFilter))
                {
                    AddToSpinner(iter);
                    if (!idSet)
                    {
                        idSet = true;
                        idPosition = iter;
                    }
                }
            }
        }
            else
        {
            for(int iter = 0; iter < spinnerArray[0].length; iter++) {
                if (spinnerArray[1][iter].contains(locationFilter) && spinnerArray[2][iter].contains(groupFilter)) {
                    AddToSpinner(iter);
                    if (!idSet)
                    {
                        idSet = true;
                        idPosition = iter;
                    }
                }
            }
        }

        hobbyList.add("");
        locationList.add("");
        groupList.add("");
        UpdateSpinner();

        currentId = spinnerArray[4][idPosition];
        descriptionView.setText(spinnerArray[3][idPosition]);

        if (groupList.isEmpty())
        {
            currentId = "";
            descriptionView.setText("");
        }

    }

    //----------------------------------------------------------------------------------------------
    void UpdateSpinner()
    {
        setAdapterContent(hobbyList);
        spinnerHobby.setAdapter(adapter);

        setAdapterContent(locationList);
        spinnerLocation.setAdapter(adapter);

        setAdapterContent(groupList);
        spinnerGroup.setAdapter(adapter);

    }

    //----------------------------------------------------------------------------------------------
    void AddToSpinner(int iter)
    {
        if (!hobbyList.contains(spinnerArray[0][iter]))
        {
            hobbyList.add(spinnerArray[0][iter]);
        }
        if (!locationList.contains(spinnerArray[1][iter]))
        {
            locationList.add(spinnerArray[1][iter]);
        }
        if (!groupList.contains(spinnerArray[2][iter]))
        {
            groupList.add(spinnerArray[2][iter]);
        }
    }

    //----------------------------------------------------------------------------------------------
    private void setOnClickListeners() {

        groupInput    = (EditText) findViewById(R.id.txtGroupText);
        locationInput = (EditText) findViewById(R.id.txtview_location_input);

        groupInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                HandleSpinnercontent(groupInput.getText().toString(), locationInput.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //---------------------------------------------------------
        locationInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                HandleSpinnercontent(groupInput.getText().toString(), locationInput.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //------------------------------------------------------------------------------------------

        final Response.Listener<String> joinResponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {


                    JSONObject jsonResponse = new JSONObject(response);

                    boolean success = jsonResponse.getBoolean("success");

                    if (success)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GroupOverview.this);
                        builder.setMessage("You have successfully joined the group- No one cares")
                                .setNegativeButton("Ok", null)
                                .create()
                                .show();
                    }
                    else
                    {
                        printDebugToast(currentId);

                            AlertDialog.Builder builder = new AlertDialog.Builder(GroupOverview.this);
                            builder.setMessage("You are already member of this group :-)")
                                    .setNegativeButton("Ok", null)
                                    .create()
                                    .show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        final Response.ErrorListener joinErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroupOverview.this);
                builder.setMessage("you are not good enough to join!! ;-)")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        };

        //------------------------------------------------------------------------------------------ responseListener
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                   // printDebugToast("hallo");

                    JSONObject jsonResponse = new JSONObject(response);
                    final JSONArray allHobbies         = jsonResponse.getJSONArray("hobby");
                    final JSONArray allLocations       = jsonResponse.getJSONArray("location");
                    final JSONArray allGroups          = jsonResponse.getJSONArray("group");
                    final JSONArray allDescritpions    = jsonResponse.getJSONArray("description");
                    final JSONArray allIds             = jsonResponse.getJSONArray("id");

                    //---------------------------------------------------------------------------------- CODE
                    //----------------------------------------------------------------------------------

                    for (int i = 0; i < allHobbies.length(); i++)
                    {
                        if(!fullHobbyList.contains(allHobbies.get(i)))
                        {
                            fullHobbyList.add( allHobbies.getString(i) );
                        }
                    }

                    for (int i = 0; i < allLocations.length(); i++)
                    {
                        if(!fullLocationList.contains(allLocations.get(i)))
                        {
                            fullLocationList.add( allLocations.getString(i) );
                        }
                    }

                    for (int i = 0; i < allGroups.length(); i++)
                    {
                        if(!fullGroupList.contains(allGroups.get(i)))
                        {
                            fullGroupList.add( allGroups.getString(i) );
                        }
                    }

                    spinnerArray = new String[5][allHobbies.length()];

                    for (int i = 0; i < allLocations.length(); i++)
                    {
                        spinnerArray[0][i] = allHobbies.getString(i);
                        spinnerArray[1][i] = allLocations.getString(i);
                        spinnerArray[2][i] = allGroups.getString(i);
                        spinnerArray[3][i] = allDescritpions.getString(i);
                        spinnerArray[4][i] = String.valueOf(allIds.getInt(i));
                    }


                    descriptionView = (TextView) findViewById(R.id.txtview_description_input);

                    spinnerHobby = (Spinner) findViewById(R.id.spinnerHobbies);
                    setAdapterContent(fullHobbyList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerHobby.setAdapter(adapter);

                    spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
                    setAdapterContent(fullLocationList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerLocation.setAdapter(adapter);

                    spinnerGroup = (Spinner) findViewById(R.id.spinnerGroup);
                    setAdapterContent(fullGroupList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerGroup.setAdapter(adapter);
                    spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String item = spinnerGroup.getSelectedItem().toString();

                            for (int iter = 0; iter < spinnerArray[0].length; iter++)
                            {
                                if (item.equals(spinnerArray[2][iter]))
                                {
                                    currentId = spinnerArray[4][iter];
                                    descriptionView.setText(spinnerArray[3][iter]);
                                    return;
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    currentId = spinnerArray[4][0];

                    descriptionView.setText(spinnerArray[3][0]);

                  //  printDebugToast("Juuunge fetish!");

                    //----------------------------------------------------------------------------------
                    //----------------------------------------------------------------------------------

                } catch (JSONException e) {
                    printDebugToast("ALLES SCHEIẞE");
                    e.printStackTrace();
                }

                //-------------------------------------------------------------
            }
        };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroupOverview.this);
                builder.setMessage("Connection failed..shit happens")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        };

        GetHobbiesDestinationRequest getHobbiesDestinationRequest = new GetHobbiesDestinationRequest(responseListener, errorListener);
        final RequestQueue queue = Volley.newRequestQueue(GroupOverview.this);
        queue.add(getHobbiesDestinationRequest);

        Button joinButton = (Button) findViewById(R.id.btn_join);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String testId = Globals.getInstance().getUserID();

                if (currentId.isEmpty())
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GroupOverview.this);
                    builder.setMessage("No Group was selected :) punk - testId: ")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
                else {
                  joinGroupRequest joinRequest = new joinGroupRequest(testId, currentId, joinResponseListener, joinErrorListener);

                  queue.add(joinRequest);
              }}

        });

        //##########################################################################################




    }

    public void printDebugToast (CharSequence text )
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast save_toast = Toast.makeText(context, text, duration);
        save_toast.show();
    }
}
