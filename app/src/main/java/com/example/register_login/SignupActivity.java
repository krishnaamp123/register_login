package com.example.register_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;

public class SignupActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText email = (EditText) findViewById(R.id.email);
        EditText repassword = (EditText) findViewById(R.id.repassword);
        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);
        TextView loginn = (TextView) findViewById(R.id.loginn);
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        ImageView google2 = (ImageView) findViewById(R.id.google2);
        ImageView facebook2 = (ImageView) findViewById(R.id.facebook2);

        //
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToDashboardActivity();
        }

        //
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strusername2 = username.getText().toString();
                String strpassword2 = password.getText().toString();

                if (username.getText().toString().length() == 0) {
                    username.setError("Fill Username!");
                } else if (email.getText().toString().length() == 0) {
                    email.setError("Fill Email!");
                } else if (password.getText().toString().length() == 0) {
                    password.setError("Fill Password!");
                }else if (repassword.getText().toString().length() == 0){
                    repassword.setError("Fill RePassword!");
                } else {
                    Toast.makeText(SignupActivity.this, strusername2 + " Registered!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    intent.putExtra("keyusername2", strusername2);
                    intent.putExtra("keypassword2", strpassword2);
                    startActivity(intent);
                }
            }
        });

        //
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //
        google2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        //
        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    password.setTransformationMethod(null);
                    repassword.setTransformationMethod(null);
                }else{
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    repassword.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

    }

    //
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToDashboardActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToDashboardActivity() {
        finish();
        Intent intent = new Intent(SignupActivity.this,DashboardActivity.class);
        startActivity(intent);
    }

}