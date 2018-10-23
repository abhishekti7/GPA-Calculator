package com.example.abhishektiwari.gpacalculator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileReader;

import es.dmoral.toasty.Toasty;

public class forgot_password extends AppCompatActivity {

    private EditText forgot_email;
    private Button forgot_password_btn;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgot_email=(EditText)findViewById(R.id.forgot_email);
        forgot_password_btn = (Button)findViewById(R.id.enter_forgot_email);
        firebaseAuth = FirebaseAuth.getInstance();

        forgot_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = forgot_email.getText().toString().trim();

                if(email.equals("")){
                    Toasty.warning(forgot_password.this, "Please Enter your email to continue",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toasty.success(forgot_password.this,"Password reset link sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgot_password.this, LoginActivity.class));
                            }else{
                                Toasty.error(forgot_password.this,"No matching account found",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
