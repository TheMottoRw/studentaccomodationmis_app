package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Reserve_Accomodation extends AppCompatActivity {

    private EditText registerNumber,studentname,level,academic;
    private Spinner room;
    private Button reserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve__accomodation_form);

        registerNumber = findViewById(R.id.registernumber);
        studentname = findViewById(R.id.stuname);
        level = findViewById(R.id.level);
        academic = findViewById(R.id.academic);
        room = findViewById(R.id.room);
        reserve = findViewById(R.id.reserve);

    }
}