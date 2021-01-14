package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DeclarationForm extends AppCompatActivity {
    private EditText reg,studentname,studentid,acayear,landLord_names,landid,phoneLandlord,houseNo,district,cell,village,sector;
    private Button declare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_form);

        reg = findViewById(R.id.reg);
        studentname  =findViewById(R.id.studentname);
        studentid  =findViewById(R.id.studentid);
        acayear = findViewById(R.id.acayear);
        landid = findViewById(R.id.landid);
        phoneLandlord = findViewById(R.id.phoneLandlord);
        houseNo = findViewById(R.id.houseNo);
        district = findViewById(R.id.district);
        cell = findViewById(R.id.cell);
        village = findViewById(R.id.village);
        sector = findViewById(R.id.village);
        declare = findViewById(R.id.declare);



    }
}