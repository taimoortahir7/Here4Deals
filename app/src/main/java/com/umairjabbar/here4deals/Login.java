package com.umairjabbar.here4deals;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.umairjabbar.here4deals.Auth.UserAuth;
import com.umairjabbar.here4deals.UtillityClasses.NetworkUtils;

public class Login extends AppCompatActivity {

    Button loginBtn;
    FormEditText usernameText;
    FormEditText passwordText;
    ProgressDialog pDialog;
    TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pDialog = new ProgressDialog(this);

        usernameText = findViewById(R.id.username_text);
        passwordText = findViewById(R.id.password_text);
        loginBtn = findViewById(R.id.login_btn);

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FormEditText[] allFields = {usernameText, passwordText};
                    boolean allValid = true;
                    for (FormEditText field : allFields) {
                        allValid = field.testValidity() && allValid;
                    }
                    if(allValid) {
                        login();
                    }
                }
            });

            registerText = findViewById(R.id.register_text);
            registerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Signup.class);
                    startActivity(intent);
                }
            });

    }



    private void login() {

        if(NetworkUtils.isConnected(Login.this)) {
            pDialog.setMessage("Logging in...");
            pDialog.setCancelable(false);
            pDialog.show();

            UserAuth.login(usernameText.getText().toString(), passwordText.getText().toString(), pDialog, Login.this);

        } else {
            Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }
}
