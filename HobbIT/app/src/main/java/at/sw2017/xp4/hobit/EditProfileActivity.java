package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import at.sw2017.xp4.hobit.requests.GetUserRequest;
import at.sw2017.xp4.hobit.requests.UpdateUserRequest;

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


        editTextNickname = (EditText)findViewById(R.id.editTextProfileNickname);
        editTextForename = (EditText)findViewById(R.id.editTextProfileForename);
        editTextSurename = (EditText)findViewById(R.id.editTextProfileSurename);
        editTextLocation = (EditText)findViewById(R.id.editTextProfileLocation);

        final Button interestsButton = (Button) findViewById(R.id.buttonEditInterests);
        interestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), EditProfileHobbiesActivity.class);
                startActivity(intent);
            }
        });

        final Button saveButton = (Button) findViewById(R.id.ButtonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

        fillData();
        editInterrestsFormOpen();

    }

    public void editInterrestsFormOpen()
    {
        Button btnEditInterests = (Button) findViewById(R.id.buttonEditInterests);
        btnEditInterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, EditProfileHobbiesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void update() {
        fillData();
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
                builder.setMessage("Connection failed EPA")
                        .setNegativeButton("OK", null)
                        .create()
                        .show();
            }
        };

        GetUserRequest getUserRequest = new GetUserRequest(Globals.getInstance().getUserID(),
                getUserResponseListener, getUserErrorListener);

        RequestQueue queue = Volley.newRequestQueue(EditProfileActivity.this);
        queue.add(getUserRequest);
    }

    /* DA STIRBT ER SEHR OFT BEI DER COVERAGE!!*/ //TODO----------------------------------------------------------------------------------
    private void updateUser() {
        Response.Listener<String> updateUserResponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                        builder.setMessage("User updated")
                                .setPositiveButton("OK", null)
                                .create()
                                .show();
                    }
                    //--------------------------------------------------------------------------------------------------------------------

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener updateUserErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                builder.setMessage("Connection failed EPA2")
                        .setNegativeButton("OK", null)
                        .create()
                        .show();
            }
        };

        UpdateUserRequest getUserRequest = new UpdateUserRequest(
                Globals.getInstance().getUserID(),
                editTextNickname.getText().toString(),
                editTextForename.getText().toString(),
                editTextSurename.getText().toString(),
                editTextLocation.getText().toString(),
                updateUserResponseListener, updateUserErrorListener);

        RequestQueue queue = Volley.newRequestQueue(EditProfileActivity.this);
        queue.add(getUserRequest);
    }

}
