package com.e.simampuscrud;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.e.simampuscrud.LoginRegister.LoginAdmin;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // langsung pindah ke MainActivity atau activity lain
        // begitu memasuki splash screen ini
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginAdmin.class));
                finish();
            }
        }, 3000L); //3000 L = 3 detik
    }
}