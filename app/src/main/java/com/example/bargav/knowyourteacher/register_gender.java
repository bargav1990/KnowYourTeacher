package com.example.bargav.knowyourteacher;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class register_gender extends AppCompatActivity {
    TextView name;
    String male;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageButton male_button =(ImageButton)findViewById(R.id.male);
        setContentView(R.layout.activity_register_gender);
        Typeface font=Typeface.createFromAsset(getAssets(),"cathsgbr.ttf");
         name=(TextView)findViewById(R.id.gender);
        name.setTypeface(font);
    }
    public void male_function(View view)
    {
        male="1";
        Intent details=new Intent(this,details_registration.class);
        details.putExtra("gender",male);
        startActivity(details);
        finish();

    }

    public void female_function(View view)
    {
         male="0";
        Intent details=new Intent(this,details_registration.class);
        details.putExtra("gender",male);
        startActivity(details);
        finish();

    }
}
