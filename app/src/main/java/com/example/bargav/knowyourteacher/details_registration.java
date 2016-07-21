package com.example.bargav.knowyourteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class details_registration extends AppCompatActivity {
    Firebase fref;
    int which;
    EditText name_edit,email_edit,password_edit;
    Spinner dep,year;
    String url="https://know-your-teacher-9d363.firebaseio.com/";
    String gender="";
    ArrayAdapter<CharSequence> department,years;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        fref = new Firebase(url);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_registration);
        dep =(Spinner)findViewById(R.id.dep);
        year =(Spinner)findViewById(R.id.year);
        department=ArrayAdapter.createFromResource(this,R.array.Department,android.R.layout.select_dialog_singlechoice);
        department.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        dep.setAdapter(department);
        years=ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.select_dialog_singlechoice);
        years.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        year.setAdapter(years);
    }
    public void submit(View view)
    {
        final TextView sign_up=(TextView)findViewById(R.id.sign_up);

        String name_string,email_string,dep_string,year_string,password_string;
        dep_string = dep.getSelectedItem().toString();
        year_string = year.getSelectedItem().toString();
        name_edit=(EditText)findViewById(R.id.name);
        password_edit=(EditText)findViewById(R.id.password);
        password_string=password_edit.getText().toString();
        name_string=name_edit.getText().toString();
        email_edit=(EditText)findViewById(R.id.emailid);
        email_string=name_edit.getText().toString();
        gender=getIntent().getExtras().getString("gender");


        final Save_details sd =new Save_details(name_string,gender,password_string,year_string,dep_string,email_string);

        if(name_string.isEmpty()||password_string.isEmpty()||email_string.isEmpty())
        {
            Toast toast =Toast.makeText(getApplicationContext(),"Please Fill In all the Details",Toast.LENGTH_SHORT);
            toast.show();

        }
        else
        {
            final Firebase number= fref.child("number");
            number.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String nums_string =(String)dataSnapshot.getValue(String.class);
                    int num =Integer.parseInt(nums_string);
                    String user_ulr="https://know-your-teacher-9d363.firebaseio.com/"+nums_string;
                    Firebase num_user = new Firebase(user_ulr);
                    num_user.setValue(sd);
                    num++;
                    number.setValue(num);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            which=1;
            Intent send = new Intent(this,MainActivity.class);
            send.putExtra("which",which);
            send.putExtra("name",name_string);
            send.putExtra("dep",dep_string);
            send.putExtra("year",year_string);
            send.putExtra("gender",gender);
            startActivity(send);
            finish();
        }

    }
}
