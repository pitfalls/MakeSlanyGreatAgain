package at.sw2017.xp4.hobit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Christof on 10.05.2017.
 */

public class HobbiT_Main_Startscreen extends AppCompatActivity {

    private static final HobbiT_Main_Startscreen ourInstance = new HobbiT_Main_Startscreen();

    public static HobbiT_Main_Startscreen getInstance() {
        return ourInstance;
    }

    public HobbiT_Main_Startscreen()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbit_startscreen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login2 = new Intent(HobbiT_Main_Startscreen.this, FacebookLogin.class);
                startActivityForResult(login2, 1);
                finish();
            }
        }, 5000);

    }
}
