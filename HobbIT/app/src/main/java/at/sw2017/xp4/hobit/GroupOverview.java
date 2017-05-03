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
import java.util.Arrays;

import at.sw2017.xp4.hobit.requests.GetHobbiesDestinationRequest;
import at.sw2017.xp4.hobit.requests.GroupCreationRequest;

public class GroupOverview extends AppCompatActivity {

    private String GroupHobby = "";


    private void fillSpinnerArray()
    {
        for (int i_ = 0; i_ < iter; i_++)
        {
            spinnerArray[i_] = (filteredHobbies[i_]);
        }
      adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
    }

    public void printDebugToast (CharSequence text )
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast save_toast = Toast.makeText(context, text, duration);
        save_toast.show();
    }

    String[] arr, filteredHobbies;
    int iter = 0;
    String[] spinnerArray;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_overview);
        //String string = getString(R.id.textview_description);
        //name_of_group.getStr

        setTitle("<GroupName>");
        setOnClickListeners();
        CreateHobbySpinner();
        CreateHobbyLocation();

    }


// Source : http://stackoverflow.com/questions/15871309/convert-jsonarray-to-string-array
    public static String[] toStringArray(JSONArray array) {
        if(array==null)
            return null;

        String[] arr=new String[array.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=array.optString(i);
        }
        return arr;
    }



    public void CreateHobbySpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerHobbies);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hobbies_array, android.R.layout.simple_spinner_item);

        //with Database we have to use a CoursorAdapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

    public void CreateHobbyLocation()
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations_array, android.R.layout.simple_spinner_item);

        //with Database we have to use a CoursorAdapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

    private void setOnClickListeners() {

        final EditText hobby_input = (EditText) findViewById(R.id.txtview_hobby_input);

        //editTextSurename
        hobby_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean bl) {
                hobby_input.setText("--------");
            }
        });
    //    printDebugToast("hallosssAS");

        //##########################################################################################

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    printDebugToast("hallo");

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    final JSONArray allHobbies = jsonResponse.getJSONArray("hobby");

                    //---------------------------------------------------------------------------------- CODE
                    //----------------------------------------------------------------------------------

                  //  printDebugToast("hallo");

                   //**********************************************************************************
                    final EditText hobby_input = (EditText) findViewById(R.id.txtview_hobby_input);

                    //editTextSurename
                /*    hobby_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean bl) {
                            hobby_input.setText("sfdasfasdfff");
                        }
                    });*/

                    //final EditText hobby_input = (EditText) findViewById(R.id.txtview_description_input);

                    //editTextSurename
                    // hobby_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    //      @Override
                    //    public void onFocusChange(View v, boolean bl) {
                    //           hobby_input.setText(text.toString().);
                    //      }
                    // });

                    //**********************************************************************************

                /*    for (int i = 0; i < allHobbies.length(); i++) {
                        printDebugToast(allHobbies.getJSONObject(i).toString());
                    }
*/
                    arr = toStringArray(allHobbies);
                    filteredHobbies = new String[arr.length];
                    filteredHobbies[0] = arr[0];

                    for (int i = 0; i < allHobbies.length(); i++) {

                        //printDebugToast(arr[i]);
                     if(!filteredHobbies[iter].contains(arr[i]))
                     {
                         iter++;
                         filteredHobbies[iter] = arr[i];
                         printDebugToast(arr[i]);
                     }
                    }

                    printDebugToast("Hier seien nun ich!!");

                    ////////////////////////////////////////////////////////////////////////////////////////////
                    // spinner füllen

                    spinnerArray = new String[arr.length];
                    fillSpinnerArray();
                    Spinner s = (Spinner) findViewById(R.id.spinnerHobbies);

                    TextView textView = (TextView) findViewById(R.id.txtview_description_input);
                    textView.setText(Arrays.toString(spinnerArray));

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s.setAdapter(adapter);

                    printDebugToast("Juuunge fertisch!");

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
        RequestQueue queue = Volley.newRequestQueue(GroupOverview.this);
        queue.add(getHobbiesDestinationRequest);



        //##########################################################################################


        final Button backButton = (Button) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), HobIT_Main.class);
                startActivity(intent);
            }
        });


    }
}
