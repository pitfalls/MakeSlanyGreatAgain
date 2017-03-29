package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        final EditText editTextSurename = (EditText) findViewById(R.id.editTextProfileSurename);

        editTextSurename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSurename.setText("");
            }
        });

        final EditText editTextForename = (EditText) findViewById(R.id.editTextProfileForename);

        editTextForename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextForename.setText("");
            }
        });

        final EditText editTextNickname = (EditText) findViewById(R.id.editTextProfileNickname);

        editTextNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNickname.setText("");
            }
        });

        final EditText editTextLocation = (EditText) findViewById(R.id.editTextProfileLocation);
        editTextLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextLocation.setText("");
            }
        });

        final EditText editTextDescription = (EditText) findViewById(R.id.editTextProfileDescription);
        editTextDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDescription.setText("");
            }
        });
    }

}
