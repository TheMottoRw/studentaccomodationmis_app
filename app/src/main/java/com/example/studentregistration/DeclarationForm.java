package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

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
        declare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                savedeclaraction();
            }
        });

//       public void savedeclaraction() {
//
//            RequestQueue queue = Volley.newRequestQueue(DeclarationForm.this);
//            String url = "http://localhost:3000/users";
//            Log.d("Req", url);
//// Request a string response from the provided URL.
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            // Display the first 500 characters of the response string.
//                            Snackbar.make(declare ,response, Snackbar.LENGTH_SHORT).show();
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(), "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//// Add the request to the RequestQueue.
//            queue.add(stringRequest);
//
//        }

    }
}