package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class create_account extends AppCompatActivity {

    private EditText name,id,national,phone_student,password;
    private RadioButton male, female;
    private Spinner marital;
    private RadioGroup group;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        national = findViewById(R.id.national);
        phone_student = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        group = findViewById(R.id.radiogroup);

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}