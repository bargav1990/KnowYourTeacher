package com.example.bargav.knowyourteacher;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class background extends AsyncTask <String,Void,String>{

    Context ctx;
    AlertDialog alertDialog;
    background(Context ctx)
    {
        this.ctx=ctx;

    }

    @Override
    protected String doInBackground(String... params) {
        String login_url="http://10.0.2.2/knowyourteacher/login.php";
        String reg_url="http://10.0.2.2/knowyourteacher/register.php";
        //192.168.43.221  10.0.2.2
        String what=params[0];
        if (what.equals("register")) {
        try {

            String sno=params[1],name_string=params[2],password_string=params[3],gender=params[4], email_string=params[7],dep_string=params[5],year_string=params[6];
            URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("Sno", "UTF-8") + "=" + URLEncoder.encode(sno, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name_string, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password_string, "UTF-8") + "&" +
                        URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&" +
                        URLEncoder.encode("dep", "UTF-8") + "=" + URLEncoder.encode(dep_string, "UTF-8") + "&" +
                        URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode(year_string, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email_string, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream is = httpURLConnection.getInputStream();
                is.close();
                httpURLConnection.disconnect();

                return "registration suceess... ";
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(what.equals("login"))
        {
            String user_name_string=params[1],pass_string=params[2];
            try {
                URL url =new URL(login_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data=URLEncoder.encode("user_name_string","UTF-8")+"="+URLEncoder.encode(user_name_string,"UTF-8")+"&"+
                            URLEncoder.encode("pass_string","UTF-8")+"="+URLEncoder.encode(pass_string,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response="",line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("registration suceess... "))
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        else if(result.equals("login sucess... "))
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        alertDialog.setMessage(result);
        alertDialog.show();
    }
}
