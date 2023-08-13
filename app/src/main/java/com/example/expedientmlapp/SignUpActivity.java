package com.example.expedientmlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUserName, signupPassword;
    TextView loginRedirect;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupName=findViewById(R.id.signup_name);
        signupEmail=findViewById(R.id.signup_email);
        signupUserName=findViewById(R.id.signup_username);
        signupPassword=findViewById(R.id.signup_password);

        signupButton=findViewById(R.id.signup_button);

        loginRedirect=findViewById(R.id.loginRedirect);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name=signupName.getText().toString();
                String email=signupEmail.getText().toString();
                String username=signupUserName.getText().toString();
                String password=signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignUpActivity.this,"Successfully registered", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}