package com.example.register_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ResepActivity extends AppCompatActivity {

    MaterialButton upbtn;
    MaterialButton unggahbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep);

        View logout = (View) findViewById(R.id.logout);
        upbtn = findViewById(R.id.upbtn);
        unggahbtn = findViewById(R.id.unggahbtn);

        //
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResepActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        //
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResepActivity.this,"Foto Berhasil di Upload",Toast.LENGTH_SHORT).show();
            }
        });

        //
        unggahbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResepActivity.this,"Resep Dokter Berhasil Disimpan",Toast.LENGTH_SHORT).show();
            }
        });

    }
}