package at.sw2017.xp4.hobit;

import android.content.Intent;
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

import at.sw2017.xp4.hobit.requests.LoginRequest;
import at.sw2017.xp4.hobit.requests.RegistrationRequest;

public class RegisterUser extends AppCompatActivity {

    private EditText nickName;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText location;
    private EditText password;
    private EditText passwordCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        nickName = (EditText)findViewById(R.id.nickName);
        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.lastName);
        email = (EditText)findViewById(R.id.email);
        location = (EditText)findViewById(R.id.location);
        password = (EditText)findViewById(R.id.password);
        passwordCheck = (EditText)findViewById(R.id.passwordCheck);


        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick = nickName.getText().toString();
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();
                String mail = email.getText().toString();
                String loc = location.getText().toString();
                String pwd = password.getText().toString();
                String pwdCheck = passwordCheck.getText().toString();

                if (nick.equals("") ||
                        first.equals("") ||
                        last.equals("") ||
                        mail.equals("") ||
                        loc.equals("") ||
                        pwd.equals("") || !pwd.equals(pwdCheck)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUser.this);
                    builder.setMessage("Please check your input!")
                            .setNegativeButton("OK", null)
                            .create()
                            .show();
                    return;
                }


                Response.Listener<String> registerResponseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUser.this);
                                builder.setMessage("Nice, you are now registered!")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                                Globals.getInstance().setUserID(jsonResponse.getString("UserID"));
                                Intent login = new Intent(RegisterUser.this, HobIT_Main.class);
                                startActivityForResult(login, 1);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUser.this);
                                builder.setMessage("This e-mail address is already registered!")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener registerErrorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUser.this);
                        builder.setMessage("Connection failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                };

                RegistrationRequest registrationRequest =
                        new RegistrationRequest(Globals.getInstance().getTempID(),
                                nick,
                                first,
                                last,
                                loc,
                                mail,
                                pwd,
                                registerResponseListener, registerErrorListener);

                RequestQueue queue = Volley.newRequestQueue(RegisterUser.this);
                queue.add(registrationRequest);
            }
        });
    }
}
