package at.sw2017.xp4.hobit;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

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
                finish();
            }
        });

        GroupData group = (GroupData) EventBus.getDefault().removeStickyEvent(GroupData.class);

        printDebugToast("Group Data received: " + group.getName() + " ID: " + group.getId());

        ///@todo place data of group object in the corresponding textfields
    }

    public void printDebugToast (CharSequence text )
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast save_toast = Toast.makeText(context, text, duration);
        save_toast.show();
    }

}
