package at.sw2017.xp4.hobit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by Christof on 26.04.2017.
 */

public class SettingsActivity extends AppCompatActivity {

    private ArrayList<CheckBox> CheckBoxes = new ArrayList<>();

    CheckBox Real_name = (CheckBox) findViewById(R.id.real_name_checkBox);
    CheckBox Nick_name = (CheckBox) findViewById(R.id.nick_name_checkBox);
    CheckBox Location = (CheckBox) findViewById(R.id.location_checkBox);
    CheckBox Notifications = (CheckBox) findViewById(R.id.notifications_checkBox);
    CheckBox Inactivity = (CheckBox) findViewById(R.id.inactivity_checkBox);
    Button Save = (Button) findViewById(R.id. save_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        CheckBoxes.add(Real_name);
        CheckBoxes.add(Nick_name);
        CheckBoxes.add(Location);
        CheckBoxes.add(Notifications);
        CheckBoxes.add(Notifications);
        CheckBoxes.add(Inactivity);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
       /* Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for(int counter = 0; counter < CheckBoxes.size(); counter++)
               // {
                    // This Perform action on click

//                String Hobbies = "";
//                for(int i = 0; i<=18; i++){
//                    if(CheckBoxes.get(i).isChecked())
//                        Hobbies = Hobbies + hobbies.get(i) + "; ";
//                }
//                //finish parsing and sending to database when database is online

               // }
               // });
            }
        }*/
    }
}
