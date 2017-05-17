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
        fillData();
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
