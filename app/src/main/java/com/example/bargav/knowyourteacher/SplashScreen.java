package com.example.bargav.knowyourteacher;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        Typeface font=Typeface.createFromAsset(getAssets(),"cathsgbr.ttf");
        TextView name=(TextView)findViewById(R.id.know_your_teacher);
        name.setTypeface(font);
        Thread splash =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent open_main=new Intent("com.example.bargav.knowyourteacher.login_activity");
                            startActivity(open_main);
                            finish();

                        }
                    });

                 } catch (InterruptedException e){

        }

        }
        });
        splash.start();
        }
}
