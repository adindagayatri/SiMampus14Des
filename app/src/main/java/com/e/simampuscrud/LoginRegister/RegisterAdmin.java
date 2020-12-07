package com.e.simampuscrud.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.simampuscrud.R;

public class RegisterAdmin extends AppCompatActivity {
    EditText mInputUsername, mInputPassword;
    Button btnSignup;
    DBHelperLogin dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        dbHelper = new DBHelperLogin(this);
        mInputUsername = (EditText) findViewById(R.id.input_username);
        mInputPassword = (EditText) findViewById(R.id.input_password);
        //mInputConfirmPass = (EditText) findViewById(R.id.input_conf_password);
        btnSignup = (Button) findViewById(R.id.register_admin);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tUsername = mInputUsername.getText().toString().trim();
                String tPassword = mInputPassword.getText().toString().trim();
                //String tCnf_pwd = mInputConfirmPass.getText().toString().trim();

                if(tPassword.equals(tPassword)){
                    long val = dbHelper.addAdmin(tUsername, tPassword);
                    if(val > 0){
                        Toast.makeText(RegisterAdmin.this,"Register Success !",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterAdmin.this,LoginAdmin.class);
                        startActivity(moveToLogin);
                    } else{
                        Toast.makeText(RegisterAdmin.this,"Register Error !",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterAdmin.this,"Wrong Password !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}