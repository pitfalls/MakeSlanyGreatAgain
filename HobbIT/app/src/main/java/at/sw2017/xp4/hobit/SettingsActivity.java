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

    public ArrayList<CheckBox> CheckBoxes = new ArrayList<CheckBox>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }
}
