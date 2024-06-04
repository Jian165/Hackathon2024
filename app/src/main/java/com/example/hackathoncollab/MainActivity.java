package com.example.hackathoncollab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edt_Email,edt_Password;
    Button btn_loginLocalMail, btn_Gmail;
    TextView txt_CreateAccount;


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
                Intent intent = new Intent(MainActivity.this, ResiterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });






}
}