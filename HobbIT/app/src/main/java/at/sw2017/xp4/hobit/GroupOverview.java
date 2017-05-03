package at.sw2017.xp4.hobit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class GroupOverview extends AppCompatActivity {

    private String GroupHobby = "";

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
