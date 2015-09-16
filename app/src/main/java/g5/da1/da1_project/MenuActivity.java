package g5.da1.da1_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.firebase.client.Firebase;


public class MenuActivity extends AppCompatActivity {
    //Firebase myFirebaseRef;
    Button kontaktBut, mapsBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Firebase.setAndroidContext(this);
        //myFirebaseRef = new Firebase("https://shining-inferno-7431.firebaseio.com/");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mapsBut = (Button) findViewById(R.id.MapsBut);
        mapsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        kontaktBut = (Button) findViewById(R.id.KontaktBut);
        kontaktBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast toast = Toast.makeText(getApplicationContext(),"You have no Contacts!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}
