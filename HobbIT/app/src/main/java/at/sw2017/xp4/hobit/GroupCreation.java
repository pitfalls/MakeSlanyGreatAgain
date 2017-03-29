package at.sw2017.xp4.hobit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class GroupCreation extends AppCompatActivity {

    public void CreateHobbySpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_hobby);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hobbies_array, android.R.layout.simple_spinner_item);
        //with Database we have to use a CoursorAdapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void CreateLocationSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_location);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations_array, android.R.layout.simple_spinner_item);
        //with Database we have to use a CoursorAdapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    public void SaveGroupCreation()
    {
        Button ButtonSave = (Button) findViewById(R.id.btn_save);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Group Saved";
                int duration = Toast.LENGTH_LONG;
                Toast save_toast = Toast.makeText(context, text, duration);
                save_toast.show();
                //Write to database
                Intent intent = new Intent(GroupCreation.this, HobIT_Main.class);
                startActivity(intent);
            }
        });
    }



    public void CancelGroupCreation()
    {
        Button ButtonSave = (Button) findViewById(R.id.btn_cancel);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Group Canceled";
                int duration = Toast.LENGTH_LONG;
                Toast save_toast = Toast.makeText(context, text, duration);
                save_toast.show();
                Intent intent = new Intent(GroupCreation.this, HobIT_Main.class);
                startActivity(intent);
            }
        });
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);

       CreateHobbySpinner();
        CreateLocationSpinner();
        SaveGroupCreation();
        CancelGroupCreation();

    }
}
