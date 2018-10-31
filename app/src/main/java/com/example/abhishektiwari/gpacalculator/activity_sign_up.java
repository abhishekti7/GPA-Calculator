package com.example.abhishektiwari.gpacalculator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;

public class activity_sign_up extends Activity {

    private EditText fname, lname, password, email, col;
    private Button newAccount;
    private TextView login, error_password, error_email;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    String mail,username,LastName, pass, college;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //Call to initialize values of UI variables
        setUIvalues();
        progressDialog = new ProgressDialog(this);


        firebaseAuth = FirebaseAuth.getInstance();
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    progressDialog.setMessage("Please Wait.");
                    progressDialog.show();

                    //Register new user
                    String user_mail = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_mail,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendUserData();
                                progressDialog.dismiss();
                                firebaseAuth.signOut();
                                Toasty.success(activity_sign_up.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(activity_sign_up.this, LoginActivity.class));
                            }
                            else{
                                progressDialog.dismiss();
                                error_email.setVisibility(View.VISIBLE);
                                error_password.setVisibility(View.VISIBLE);
                                email.setText("");
                                password.setText("");
                                error_email.setText("Check validity of email");
                                error_password.setText("Password should contain atleast one UpperCase alphabet, one number and one special character.");
                                Toasty.error(activity_sign_up.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            };
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setUIvalues(){
        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        col = (EditText)findViewById(R.id.college);
        login = (TextView)findViewById(R.id.login);
        newAccount = (Button)findViewById(R.id.newAccount);
        error_password = (TextView)findViewById(R.id.error_password);
        error_email = (TextView)findViewById(R.id.error_email);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public Boolean validate(){
        Boolean result = false;
        username = fname.getText().toString();
        pass = password.getText().toString();
        mail = email.getText().toString();
        LastName = lname.getText().toString();
        college = col.getText().toString().trim();

        if(username.isEmpty() || pass.isEmpty() || mail.isEmpty()) {
            Toasty.warning(this, "Please enter all the details", Toast.LENGTH_SHORT);
        }else{
            result = true;
        }
        return result;
    }

    //Send data to db
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

        UserProfile userProfile = new UserProfile(username+" "+LastName,mail,college);
        UserGrades userGrades = new UserGrades(0,0,0,0,0,0,0,0);
        GradeList gradeList = new GradeList("10","9","8","7","6","5");
        Profile profile = new Profile(userProfile,userGrades,gradeList);
        myRef.setValue(profile);
    }
}
