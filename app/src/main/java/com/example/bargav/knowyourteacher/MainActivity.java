package com.example.bargav.knowyourteacher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    int which,gender,know;
    String name,year,dep;
    int num;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView names=(TextView)findViewById(R.id.name);
        final TextView years=(TextView)findViewById(R.id.year);
        final TextView deps=(TextView)findViewById(R.id.department);
        Firebase.setAndroidContext(this);
        String url="https://know-your-teacher-9d363.firebaseio.com/";
       Firebase fref = new Firebase(url);
        final de.hdodenhof.circleimageview.CircleImageView pic =(de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.pic);
        which =getIntent().getExtras().getInt("which");
        if(which==1)
        {
            // for registration
            name=getIntent().getExtras().getString("name");
            year=getIntent().getExtras().getString("year");
            dep=getIntent().getExtras().getString("dep");
            gender=getIntent().getExtras().getInt("gender");
            names.setText(name);
            years.setText(year);
            deps.setText(dep);
            if(gender==1)

                pic.setImageResource(R.drawable.boy_profile);
            if(gender==0)
                pic.setImageResource(R.drawable.girl_profile);


        }
        if(which==0)
        {
            //for login
            know=getIntent().getExtras().getInt("know");
            fref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    name=dataSnapshot.child(""+know).child("name").getValue(String.class);
                    dep=dataSnapshot.child(""+know).child("department").getValue(String.class);
                    year=dataSnapshot.child(""+know).child("year").getValue(String.class);
                    String genders=dataSnapshot.child(""+know).child("gender").getValue(String.class);
                    gender =Integer.parseInt(genders);
                    names.setText(name);
                    years.setText(year);
                    deps.setText(dep);
                    if(gender==1)
                        pic.setImageResource(R.drawable.boy_profile);
                    if(gender==0)
                        pic.setImageResource(R.drawable.girl_profile);


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
           }


    }

    public void search(View view)
    {
        EditText name =(EditText)findViewById(R.id.search_teacher);
        ScrollView scroll = (ScrollView) findViewById(R.id.scrollView);
        final LayoutInflater inflater =  (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout parent = (LinearLayout) findViewById(R.id.scLl);
        String url="https://know-your-teacher.firebaseio.com/";
        final String name_prof=name.getText().toString();
        Firebase.setAndroidContext(this);
        Firebase fref = new Firebase(url);
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

        fref.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
      for(int i=0;i<num;i++)
      {
          String name=dataSnapshot.child(""+i).child("name").getValue(String.class);
          if(name.equals(name_prof))
          {

              String dep=dataSnapshot.child(""+i).child("department").getValue(String.class);
              View custom = inflater.inflate(R.layout.teacher_wid, null);
              TextView name_teacher=(TextView)custom.findViewById(R.id.name_teacher);
              TextView dep_teacher=(TextView)custom.findViewById(R.id.department_teacher);
              name_teacher.setText(name);
              dep_teacher.setText(dep);
              parent.addView(custom);

          }
      }
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
});

    }
 }
