package com.example.tracking.trackingsys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import java.nio.charset.StandardCharsets;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    private AlertDialog alertDialog;
    @SuppressLint("StaticFieldLeak")
    private
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Bilgisi...");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://localhost/webapp/register.php";
        String login_url = "http://localhost/webapp/login.php";
        String method = params[0];
        if (method.equals("kayit")) {
            String ad_soyad = params[1];
            String telefon = params[2];
            String mail = params[3];
            String sifre =params[4];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("ad_soyad", "UTF-8") +"="+ URLEncoder.encode(ad_soyad, "UTF-8") + "&" +
                        URLEncoder.encode("telefon", "UTF-8") +"="+ URLEncoder.encode(telefon, "UTF-8") + "&" +
                        URLEncoder.encode("mail", "UTF-8") +"="+ URLEncoder.encode(mail, "UTF-8") + "&" +
                        URLEncoder.encode("sifre", "UTF-8") +"="+ URLEncoder.encode(sifre, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Kayıt Başarılı...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(method.equals("giris"))
        {
            String giris_mail = params[1];
            String giris_sifre = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("giris_mail","UTF-8")+"="+URLEncoder.encode(giris_mail,"UTF-8")+"&"+
                        URLEncoder.encode("giris_sifre","UTF-8")+"="+URLEncoder.encode(giris_sifre,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine())!=null)
                {
                    response.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Kayıt Başarılı..."))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }
}
