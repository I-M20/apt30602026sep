package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class signUp extends AppCompatActivity {

    TextView openLogin;
    EditText signupEmail, signupPassword;
    Button signupButton;
    FirebaseAuth signupFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Connect TextView from XML
        openLogin = findViewById(R.id.openLoginPage);

        //initialize the UI objects

        signupEmail = findViewById(R.id.usernamesignup);
        signupPassword = findViewById(R.id.passwordsignup);
        signupButton = findViewById(R.id.buttonsignup);
        signupFirebase = FirebaseAuth.getInstance();


        // When "Click here to Login" is clicked
        openLogin.setOnClickListener(v -> {
            Intent intent = new Intent(signUp.this, MainActivity.class);
            startActivity(intent);
        });

        //onclick listener for the login

        signupButton.setOnClickListener(v-> signup());

    }

    private void signup(){

        String email = signupEmail.getText().toString().trim() ;
        String password = signupPassword.getText().toString().trim();


        signupFirebase.createUserWithEmailAndPassword(email,password).addOnCompleteListener( this, task->{

            if(task.isSuccessful()){

                Intent openlogin = new Intent(signUp.this, MainActivity.class);
                startActivity(openlogin);

                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

            } else{

                Toast.makeText(this, "Sign Up Not Successful", Toast.LENGTH_SHORT).show();

            }


        } );

    }
}