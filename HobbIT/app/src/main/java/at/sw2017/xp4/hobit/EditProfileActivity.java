package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import at.sw2017.xp4.hobit.requests.GetUserRequest;

public class EditProfileActivity extends AppCompatActivity {

    static int colorGray = 0xff888888;

    private EditText editTextNickname;
    private EditText editTextForename;
    private EditText editTextSurename;
    private EditText editTextLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setOnClickListeners();

        fillData();
    }

    private void setOnClickListeners() {
       editTextSurename = (EditText) findViewById(R.id.editTextProfileSurename);

        //editTextSurename
        editTextSurename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean bl) {
                if ( bl ) {
                   // editTextSurename.setTextColor(colorGray);
                    editTextSurename.setText("");
                }
                else
                {
                    String str = editTextSurename.getText().toString();
                    if ( str.equals("") ) {
                        editTextSurename.setText("Surename");
                    }
                }
            }
        });

       editTextForename = (EditText) findViewById(R.id.editTextProfileForename);

        editTextForename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean bl) {
                if ( bl ) {
                    editTextForename.setText("");
                }
                else
                {
                    String str = editTextForename.getText().toString();
                    if ( str.equals("") ) {
                        editTextForename.setText("Forename");
                    }
                }
            }
        });

        editTextNickname = (EditText) findViewById(R.id.editTextProfileNickname);

        editTextNickname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean bl) {
                if ( bl ) {
                    editTextNickname.setText("");
                }
                else
                {
                    String str = editTextNickname.getText().toString();
                    if ( str.equals("") ) {
                        editTextNickname.setText("Nickname");
                    }
                }
            }
        });

        editTextLocation = (EditText) findViewById(R.id.editTextProfileLocation);
        editTextLocation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean bl) {
                if ( bl ) {
                    editTextLocation.setText("");
                }
                else
                {
                    String str = editTextLocation.getText().toString();
                    if ( str.equals("") ) {
                        editTextLocation.setText("Location");
                    }
                }
            }
        });

        final Button interestsButton = (Button) findViewById(R.id.buttonEditInterests);
        interestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), EditProfileHobbiesActivity.class);
                startActivity(intent);
            }
        });

        final Button backButton = (Button) findViewById(R.id.BackEditProfile);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), HobIT_Main.class);
                startActivity(intent);
            }
        });
    }

    private void fillData() {

        Response.Listener<String> getUserResponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        editTextNickname.setText(jsonResponse.getString("NickName"));
                        editTextForename.setText(jsonResponse.getString("FirstName"));
                        editTextSurename.setText(jsonResponse.getString("SurName"));
                        editTextLocation.setText(jsonResponse.getString("Location"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener getUserErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                builder.setMessage("Connection failed")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        };

        GetUserRequest getUserRequest = new GetUserRequest(Globals.getInstance().getUserID(),
                getUserResponseListener, getUserErrorListener);

        RequestQueue queue = Volley.newRequestQueue(EditProfileActivity.this);
        queue.add(getUserRequest);
    }
}
