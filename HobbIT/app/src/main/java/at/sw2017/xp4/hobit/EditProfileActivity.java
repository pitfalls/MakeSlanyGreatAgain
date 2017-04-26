package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.app.PendingIntent.getActivity;

public class EditProfileActivity extends AppCompatActivity {

    static int colorGray = 0xff888888;

    DataBaseConnection dbConnection;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //setOnClickListeners();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String userID = sharedPref.getString(getString(R.string.current_user), "");

        dbConnection = DataBaseConnection.getInstance(this);

        currentUser = dbConnection.getCurrentUser();

        final EditText editTextNickname = (EditText) findViewById(R.id.editTextProfileNickname);
        editTextNickname.setText(currentUser.getNickName());
        final EditText editTextForename = (EditText) findViewById(R.id.editTextProfileForename);
        editTextForename.setText(currentUser.getFirstName());
        final EditText editTextSurname = (EditText) findViewById(R.id.editTextProfileSurename);
        editTextSurname.setText(currentUser.getSurName());
    }

/*
    private void setOnClickListeners() {
        final EditText editTextSurename = (EditText) findViewById(R.id.editTextProfileSurename);

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

        final EditText editTextForename = (EditText) findViewById(R.id.editTextProfileForename);

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

        final EditText editTextNickname = (EditText) findViewById(R.id.editTextProfileNickname);

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

        final EditText editTextLocation = (EditText) findViewById(R.id.editTextProfileLocation);
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

        final EditText editTextDescription = (EditText) findViewById(R.id.editTextProfileDescription);
        editTextDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean bl) {
                if ( bl ) {
                    editTextDescription.setText("");
                }
                else
                {
                    String str = editTextDescription.getText().toString();
                    if ( str.equals("") ) {
                        editTextDescription.setText("Short description of person");
                    }
                }
            }
        });

        final Button interestsButton = (Button) findViewById(R.id.buttonEditInterests);
        interestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click

                final EditText editTextNickname = (EditText) findViewById(R.id.editTextProfileNickname);
                final EditText editTextForename = (EditText) findViewById(R.id.editTextProfileForename);
                final EditText editTextSurename = (EditText) findViewById(R.id.editTextProfileSurename);

                currentUser.setNickName(editTextNickname.getText().toString());
                currentUser.setFirstName(editTextForename.getText().toString());
                currentUser.setSurName(editTextSurename.getText().toString());

                Intent intent = new Intent(view.getContext(), EditProfileHobbiesActivity.class);
                startActivity(intent);
            }
        });
    }
*/
}
