package g5.da1.da1_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class LoginActivity extends AppCompatActivity {
    Button loginBut, registerBut;
    EditText usernameEdit, passwordEdit;
    Firebase myFirebaseRef;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://shining-inferno-7431.firebaseio.com/");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBut = (Button) findViewById(R.id.loginButton);
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        registerBut = (Button) findViewById(R.id.registerButton);
        registerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        usernameEdit = (EditText) findViewById(R.id.UsernameText);
        passwordEdit = (EditText) findViewById(R.id.PasswordText);

    }

}
