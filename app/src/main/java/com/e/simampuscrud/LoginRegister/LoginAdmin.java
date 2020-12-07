package com.e.simampuscrud.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.simampuscrud.MainActivity;
import com.e.simampuscrud.R;

public class LoginAdmin extends AppCompatActivity {
    EditText mUsername, mPassword;
    Button btnLogin;
    DBHelperLogin dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        dbHelper = new DBHelperLogin(this);
        mUsername = (EditText) findViewById(R.id.admin_username);
        mPassword = (EditText) findViewById(R.id.admin_password);
        btnLogin = (Button) findViewById(R.id.login_admin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tUsername = mUsername.getText().toString().trim();
                String tPassword = mPassword.getText().toString().trim();
                Boolean res = dbHelper.checkUser(tUsername, tPassword);
                if(res == true){
                    Intent intent = new Intent(LoginAdmin.this, MainActivity.class);
                    Toast.makeText(LoginAdmin.this, "Login Success !", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginAdmin.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}