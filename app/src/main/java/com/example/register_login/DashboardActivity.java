package com.example.register_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView telpon;
    ImageView lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //
        TextView nama = (TextView) findViewById(R.id.nama);
        TextView email = (TextView) findViewById(R.id.email);
        TextView seeall = (TextView) findViewById(R.id.seeall);
        ImageView logout = (ImageView) findViewById(R.id.image_logout);
        ImageView edit = (ImageView) findViewById(R.id.image_profile2);
        ImageView setting = (ImageView) findViewById(R.id.image_settings2);
        ImageView image_resep = (ImageView) findViewById(R.id.image_resep);
        ImageView image_beli = (ImageView) findViewById(R.id.image_beli);
        ImageView image_foto = (ImageView) findViewById(R.id.image_foto);
        telpon = findViewById(R.id.image_telpon);
        lokasi = findViewById(R.id.image_location);

        //
        Intent intent = getIntent();
        String strusername1 = getIntent().getStringExtra("keyusername1");
        nama.setText(strusername1);

        //
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String strusername3 = acct.getDisplayName();
            String stremail1 = acct.getEmail();
            nama.setText(strusername3);
            email.setText(stremail1);
        }

        //
        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:-8.675829,115.212685?q="+Uri.parse("-8.675829,115.212685"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        //
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        //
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        //
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        //
        image_resep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ResepActivity.class);
                startActivity(intent);
            }
        });

        //
        telpon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://livechat.klikdokter.com/");
            }
        });

        //
        image_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

        //
        image_beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, BeliActivity.class);
                startActivity(intent);
            }
        });

        //
        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, BeliActivity.class);
                startActivity(intent);
            }
        });

        //
        recyclerViewList=findViewById(R.id.view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        ArrayList<ListDomain> news=new ArrayList<>();
        news.add(new ListDomain("Demacolin Sirup 60 ml","demacolin"));
        news.add(new ListDomain("Anadex Sirup 60 ml","anadex"));
        news.add(new ListDomain("Dextamine Sirup 60 ml","dextamine"));
        news.add(new ListDomain("Fixiphar Dry Sirup 60 ml","fixiphar"));
        news.add(new ListDomain("Laprosin Sirup 60 ml","laprosin"));
        news.add(new ListDomain("Depakote ER 500 mg","depakote"));

        adapter = new NewsAdapter(news);
        recyclerViewList.setAdapter(adapter);
    }

    //
    void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
            }
        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}