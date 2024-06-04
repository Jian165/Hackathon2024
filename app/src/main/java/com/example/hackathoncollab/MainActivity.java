package com.example.hackathoncollab;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edt_Email,edt_Password;
    Button btn_loginLocalMail, btn_Gmail;
    TextView CreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_Email =  findViewById(R.id.txt_Email);
        edt_Password =  findViewById(R.id.txt_Password);


}
}