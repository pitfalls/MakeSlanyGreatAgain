package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {

    static int colorGray = 0xff888888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setOnClickListeners();
    }

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
    }

}
