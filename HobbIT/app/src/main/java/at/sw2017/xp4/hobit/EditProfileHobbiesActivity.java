package at.sw2017.xp4.hobit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class EditProfileHobbiesActivity extends AppCompatActivity {

    public static final String HOBBY1 = "Swimming";
    public static final String HOBBY2 = "Hiking";
    public static final String HOBBY3 = "Fitness studio";
    public static final String HOBBY4 = "Playing cards";
    public static final String HOBBY5 = "Computer games";
    public static final String HOBBY6 = "Solve quiz";
    public static final String HOBBY7 = "Ride a bicycle";
    public static final String HOBBY8 = "Puzzle";
    public static final String HOBBY9 = "Needle";
    public static final String HOBBY10 = "Model making";
    public static final String HOBBY11 = "Yoyo playing";
    public static final String HOBBY12 = "Dancing";
    public static final String HOBBY13 = "Climbing";
    public static final String HOBBY14 = "Astronomy";
    public static final String HOBBY15 = "Collecting";
    public static final String HOBBY16 = "Cooking";
    public static final String HOBBY17 = "Football";
    public static final String HOBBY18 = "Music";
    public static final String HOBBY19 = "others";

    Button Continue_button;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<String> hobbies = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_hobbies);

        hobbies.add(HOBBY1);
        hobbies.add(HOBBY2);
        hobbies.add(HOBBY3);
        hobbies.add(HOBBY4);
        hobbies.add(HOBBY5);
        hobbies.add(HOBBY6);
        hobbies.add(HOBBY7);
        hobbies.add(HOBBY8);
        hobbies.add(HOBBY9);
        hobbies.add(HOBBY10);
        hobbies.add(HOBBY11);
        hobbies.add(HOBBY12);
        hobbies.add(HOBBY13);
        hobbies.add(HOBBY14);
        hobbies.add(HOBBY15);
        hobbies.add(HOBBY16);
        hobbies.add(HOBBY17);
        hobbies.add(HOBBY18);
        hobbies.add(HOBBY19);

        for(int i = 1; i<=19; i++){
            String checkBoxName = "checkBox" + i;
            int id = getResources().getIdentifier(checkBoxName, "id", R.class.getPackage().getName());
            CheckBox checkBox = (CheckBox) findViewById(id);
            checkBoxes.add(checkBox);
        }

        Continue_button = (Button) findViewById(R.id.cont_button);
        Continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Hobbies = "";
                for(int i = 0; i<=18; i++){
                    if(checkBoxes.get(i).isChecked()==true)
                        Hobbies = Hobbies + hobbies.get(i) + "; ";
                }
                //finish parsing and sending to database when database is online

                Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
