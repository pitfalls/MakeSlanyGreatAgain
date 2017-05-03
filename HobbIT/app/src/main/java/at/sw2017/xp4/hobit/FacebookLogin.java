package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import at.sw2017.xp4.hobit.requests.GetUserRequest;
import at.sw2017.xp4.hobit.requests.LoginRequest;

/**
 * Created by Gerd on 29.03.2017.
 */

//TODO: onCreate check if fb is already logged in and log out

public class FacebookLogin extends AppCompatActivity {
    String userId = "";

    private LoginButton loginButton;
    private EditText username;
    private EditText password;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Facebook login

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_facebook_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                userId = loginResult.getAccessToken().getUserId();

                Response.Listener<String> getUserResponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Globals.getInstance().setUserID(userId);
                                Intent login = new Intent(FacebookLogin.this, HobIT_Main.class);
                                startActivityForResult(login, 1);
                            } else {
                                Globals.getInstance().setTempID(userId);
                                Intent login = new Intent(FacebookLogin.this, RegisterUser.class);
                                startActivityForResult(login, 1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener getUserErrorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FacebookLogin.this);
                        builder.setMessage("Connection failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                };

                GetUserRequest getUserRequest = new GetUserRequest(userId, getUserResponseListener, getUserErrorListener);

                RequestQueue queue = Volley.newRequestQueue(FacebookLogin.this);
                queue.add(getUserRequest);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) { }
        });

        setOnClickListeners();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setOnClickListeners() {
        final Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr = username.getText().toString();
                String pwd = password.getText().toString();

                if (usr.length() == 0 || pwd.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FacebookLogin.this);
                    builder.setMessage("Empty user credentials!")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    return;
                }

                Response.Listener<String> loginResponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Globals.getInstance().setUserID(jsonResponse.getString("UserID"));
                                Intent login = new Intent(FacebookLogin.this, HobIT_Main.class);
                                startActivityForResult(login, 1);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FacebookLogin.this);
                                builder.setMessage("Incorrect user credentials!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener loginErrorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FacebookLogin.this);
                        builder.setMessage("Connection failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                };

                LoginRequest loginRequest = new LoginRequest(usr, pwd, loginResponseListener, loginErrorListener);

                RequestQueue queue = Volley.newRequestQueue(FacebookLogin.this);
                queue.add(loginRequest);
            }
        });

        final Button registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(FacebookLogin.this, RegisterUser.class);
                startActivityForResult(login, 1);
            }
        });
    }
}

