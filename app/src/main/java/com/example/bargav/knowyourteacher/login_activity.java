package com.example.bargav.knowyourteacher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.Map;

public class login_activity extends Activity {

    EditText user_name,password;
    int names=0,passs=0,k=0,num,know,which=0;
    String user_name_string,pass_string,name_get,pass_get;
    Firebase fref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        Typeface font=Typeface.createFromAsset(getAssets(),"cathsgbr.ttf");
        TextView name=(TextView)findViewById(R.id.title);

        name.setTypeface(font);
        user_name=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        Firebase.setAndroidContext(this);
        String url="https://know-your-teacher-9d363.firebaseio.com/";
        fref = new Firebase(url);
        final Firebase number= fref.child("number");
        number.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nums_string =(String)dataSnapshot.getValue(String.class);
                num =Integer.parseInt(nums_string);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
    public void signup(View view)
    {
        Intent signup=new Intent(this,register_gender.class);
        startActivity(signup);
    }
    public void login(View view)
    {

            user_name_string=user_name.getText().toString();
            pass_string=password.getText().toString();
            check(num);
        TextView title=(TextView)findViewById(R.id.title);
            if(passs==1&&names==1)
            {
                Intent login=new Intent(this,MainActivity.class);
                login.putExtra("num",know);
                login.putExtra("which",which);
                startActivity(login);
                finish();
            }
            else
            {
                if(k%2!=0&&k!=0)
                {
                    Toast toast =Toast.makeText(getApplicationContext(),"Wrong Username Or password",Toast.LENGTH_SHORT);
                    toast.show();
                    k++;

                }
                else
                {

                    Toast toast =Toast.makeText(getApplicationContext(),"click again please",Toast.LENGTH_SHORT);
                    toast.show();
                    k++;
                }


            }


    }

    public void check(final int num)
    {
            fref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(int i=0;i<num;i++)
                    {
                        String name =dataSnapshot.child(""+i).child("name").getValue(String.class);
                        if (name.equals(user_name_string))
                        {
                            names=1;
                            know=i;
                        }
                        String password =dataSnapshot.child(""+i).child("password").getValue(String.class);
                        if (password.equals(pass_string))
                        {
                            passs=1;
                            break;
                        }

                    }



                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });



    }
}