package com.example.hackathoncollab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText UserName, Password, Email, ConfirmPassword, BackToLogin;
    Button Register;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    ProgressDialog progressDialog;
    DatabaseReference  DataRef;
    String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        UserName =  findViewById(R.id.txt_R_UserName);
        Password =  findViewById(R.id.txt_R_Password);
        Email =  findViewById(R.id.txt_R_Email);
        ConfirmPassword = findViewById(R.id.txt_R_PasswordConfirm);
        progressDialog = new ProgressDialog(this);
        Register =  findViewById(R.id.btn_Register);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuthentic();
            }
        });



    }

  private void PerformAuthentic() {
      String email = Email.getText().toString();
      String password = Password.getText().toString();
      String userName =  UserName.getText().toString();
      String confirmPassword = ConfirmPassword.getText().toString();

      if (!email.matches(emailPattern)) {
          Email.setError("Enter Correct Email");
      } else if (password.isEmpty() || password.length() < 6) {
          Password.setError("Enter Proper Password");
      } else if (!password.equals(confirmPassword)) {
          ConfirmPassword.setError("Password not match");
      } else {
          progressDialog.setMessage("Please Wait While Registration");
          progressDialog.setTitle("Registration");
          progressDialog.setCanceledOnTouchOutside(false);
          progressDialog.show();

          mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                      progressDialog.dismiss();
                      UserID = mAuth.getCurrentUser().getUid();
                      DataRef = FirebaseDatabase.getInstance("https://hackathoncollab2024-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users").child(UserID);
                      DataRef.child("UserName").setValue(userName);
                      sendUserToNextActivity();
                      Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();


                  } else {
                      progressDialog.dismiss();
                      sendUserToNextActivity();
                      Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                  }
              }
          });
      }
  }
  private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}