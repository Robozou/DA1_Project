package g5.da1.da1_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {
    private Button loginBut, registerBut;
    private EditText usernameEdit, passwordEdit;
    private Firebase myFirebaseRef;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://shining-inferno-7431.firebaseio.com/");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(this);
        usernameEdit = (EditText) findViewById(R.id.UsernameText);
        passwordEdit = (EditText) findViewById(R.id.PasswordText);
        loginBut = (Button) findViewById(R.id.loginButton);
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameEdit.getText().toString().length()>1&&passwordEdit.getText().toString().length()>1) {
                    myFirebaseRef.authWithPassword(usernameEdit.getText().toString(), passwordEdit.getText().toString(), new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            sessionManager.setLoginDetails(authData.getUid());
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        registerBut = (Button) findViewById(R.id.registerButton);
        registerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFirebaseRef.createUser(usernameEdit.getText().toString(), passwordEdit.getText().toString(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "You are registred you may now login!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }

}