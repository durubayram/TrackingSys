package com.example.tracking.trackingsys;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText ad_soyad_gir,telefon_gir,mail_gir,sifre_gir;
    String ad_soyad,telefon,mail,sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ad_soyad_gir= findViewById(R.id.ad_soyad);
        telefon_gir = findViewById(R.id.telefon);
        mail_gir= findViewById(R.id.mail);
        sifre_gir= findViewById(R.id.sifre);
    }

    public void kayitOl(View view) {
        ad_soyad =ad_soyad_gir.getText().toString();
        telefon =telefon_gir.getText().toString();
        mail=mail_gir.getText().toString();
        sifre=sifre_gir.getText().toString();
        String method="kayit";
        BackgroundTask bg = new BackgroundTask(this);
        bg.execute(method,ad_soyad,telefon,mail,sifre);
    }
}

