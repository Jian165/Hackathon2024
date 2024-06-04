package com.example.hackathoncollab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edt_Email,edt_Password;
    Button btn_loginLocalMail, btn_Gmail;
    TextView txt_CreateAccount;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    ProgressDialog progressDialog;

    String emailPattern  = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_Email =  findViewById(R.id.txt_R_Email);
        edt_Password =  findViewById(R.id.txt_R_Password);
        btn_loginLocalMail = findViewById(R.id.btn_Register);
        btn_Gmail =  findViewById(R.id.btn_LogintWithGoogle);
        txt_CreateAccount = findViewById(R.id.txt_CreateAccount);

        txt_CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

    }

    private void performLogin() {
        String email = edt_Email.getText().toString();
        String password = edt_Password.getText().toString();

        if(!email.matches(emailPattern))
        {
            edt_Email.setError("Enter Correct Email");
        }
        else if (password.isEmpty() || password.length()<6)
        {
            edt_Password.setError("Enter Proper Password");
        }
        else
        {
            progressDialog.setMessage("Please Wait While login");
            progressDialog.setTitle("login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {

                        progressDialog.dismiss();
                        sendUserToNextActivity();

                        Toast.makeText(MainActivity.this, "Successfully login", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }

    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

}