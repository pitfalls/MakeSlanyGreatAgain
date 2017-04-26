package at.sw2017.xp4.hobit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroupOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_overview);
        //String string = getString(R.id.textview_description);
        //name_of_group.getStr
        setTitle("<GroupName>");
        setOnClickListeners();

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
