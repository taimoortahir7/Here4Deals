package com.umairjabbar.here4deals;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.umairjabbar.here4deals.Auth.UserAuth;
import com.umairjabbar.here4deals.UtillityClasses.NetworkUtils;

public class Signup extends AppCompatActivity {

    Button signup;
    FormEditText emailEditText;
    FormEditText confirmEditText;
    FormEditText passwordEditText;
    FormEditText firstNameEditText;
    FormEditText lastNameEditText;
    FormEditText phoneEditText;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        pDialog = new ProgressDialog(this);

        emailEditText = findViewById(R.id.email_text);
        confirmEditText = findViewById(R.id.confirm_text);
        passwordEditText = findViewById(R.id.password_text);
        firstNameEditText = findViewById(R.id.first_name_text);
        lastNameEditText = findViewById(R.id.last_name_text);
        phoneEditText = findViewById(R.id.number_text);

        signup = findViewById(R.id.signup_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormEditText[] allFields = {emailEditText, confirmEditText, passwordEditText, firstNameEditText, lastNameEditText, phoneEditText};
                boolean allValid = true;
                for (FormEditText field : allFields) {
                    allValid = field.testValidity() && allValid;
                }
                if(allValid) {
                    register();
                }
            }
        });
    }

    private void register() {
        if(NetworkUtils.isConnected(Signup.this)) {
            pDialog.setMessage("Logging in...");
            pDialog.setCancelable(false);
            pDialog.show();

//            UserAuth.signup(firstNameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString(), pDialog, Signup.this);
            Intent intent = new Intent(Signup.this, MainActivity.class);
            startActivity(intent);
            pDialog.dismiss();

        } else {
            Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }
}
