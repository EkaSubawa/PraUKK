package com.example.ekasubawa.datasekolah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity {

    @BindView(R.id.email) EditText etEmail;
    @BindView(R.id.password) EditText etPass;
    @BindView(R.id.btnLogin) Button btnlogin;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ProgressDialog mProgres;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        mProgres = new ProgressDialog(this);
        mProgres.setMessage("Log In...");
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                login();
            }
        });

    }

    private void login() {
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            Toast.makeText(this,R.string.field_kosong, Toast.LENGTH_LONG).show();
        }

        else if (TextUtils.isEmpty(email)){
            Toast.makeText(this, R.string.email_kosong, Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, R.string.pass_kosong, Toast.LENGTH_LONG).show();
        }

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mProgres.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(Login.this, Dashboard.class));
                    } else {
                        Toast.makeText(Login.this, "Login Error", Toast.LENGTH_LONG).show();
                    }

                    mProgres.dismiss();
                }
            });
        }
    }
}
