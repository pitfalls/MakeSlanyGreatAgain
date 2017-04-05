package at.sw2017.xp4.hobit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GroupOverview extends AppCompatActivity {

    public void JoinGroupOverview()
    {
        Button ButtonSave = (Button) findViewById(R.id.btn_join);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Group Joined";
                int duration = Toast.LENGTH_LONG;
                Toast save_toast = Toast.makeText(context, text, duration);
                save_toast.show();
                //Write to database
                Intent intent = new Intent(GroupOverview.this, HobIT_Main.class);
                startActivity(intent);
            }
        });
    }


    public void CancelGroupOverview()
    {
        Button ButtonSave = (Button) findViewById(R.id.btn_back);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Canceled";
                int duration = Toast.LENGTH_SHORT;
                Toast save_toast = Toast.makeText(context, text, duration);
                save_toast.show();
                //Write to database
                Intent intent = new Intent(GroupOverview.this, ListHobbyGroups.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_overview);
        //String string = getString(R.id.textview_description);
        //name_of_group.getStr
        setTitle("<GroupName>");
        JoinGroupOverview();
        CancelGroupOverview();

    }
}
