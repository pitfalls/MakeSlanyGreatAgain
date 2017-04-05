package at.sw2017.xp4.hobit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ListHobbyGroups extends AppCompatActivity {

    public void Searching()
    {
        Button ButtonSave = (Button) findViewById(R.id.btn_search);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Searching...";
                int duration = Toast.LENGTH_SHORT;
                Toast save_toast = Toast.makeText(context, text, duration);
                save_toast.show();
                //Write to database
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hobby_groups);
        //setTitle("Individual");
        Searching();



    }
}
