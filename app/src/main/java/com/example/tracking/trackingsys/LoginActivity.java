package com.example.tracking.trackingsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends Activity {
    EditText mail,sifre;
    String giris_mail,giris_sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail = findViewById(R.id.mail);
        sifre= findViewById(R.id.sifre);
    }

    public void kullaniciGiris(View view) {
        giris_mail=mail.getText().toString();
        giris_sifre=sifre.getText().toString();
        String method="giris";
        BackgroundTask bg = new BackgroundTask(this);
        bg.execute(method,giris_mail,giris_sifre);
    }

    public void kullaniciKayit(View view) {
        startActivity(new Intent(this,RegisterActivity.class));

    }}
