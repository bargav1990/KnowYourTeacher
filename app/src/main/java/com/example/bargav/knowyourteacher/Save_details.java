package com.example.bargav.knowyourteacher;

import com.firebase.client.Firebase;

/**
 * Created by bargav on 7/19/2016.
 */
public class Save_details {
    private String name,password,gender,year,department,emailid,num;



   public Save_details(String name, String gender, String password,String year, String department, String emailid)
    {
        this.name=name;
        this.gender=gender;
        this.password=password;
        this.year=year;
        this.department=department;
        this.emailid=emailid;

    }
    public Save_details(String name)
    {
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getYear() {
        return year;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmailid() {
        return emailid;
    }

}

