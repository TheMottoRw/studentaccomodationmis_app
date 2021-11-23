package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeclarationForm extends AppCompatActivity {
    private EditText reg,studentname,level_class,acayear,landLord_names,landid,phoneLandlord,houseNo,district,cell,village,sector;
    private Button declare;
    private Helper helper;
    private ProgressDialog pgdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_form);
        helper = new Helper(this);
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));

        reg = findViewById(R.id.reg);
        studentname  =findViewById(R.id.studentname);
        level_class  =findViewById(R.id.level_class);
        acayear = findViewById(R.id.acayear);
        landid = findViewById(R.id.landid);
        landLord_names = findViewById(R.id.landLord_names);
        phoneLandlord = findViewById(R.id.phoneLandlord);
        houseNo = findViewById(R.id.houseNo);
        district = findViewById(R.id.district);
        cell = findViewById(R.id.cell);
        village = findViewById(R.id.village);
        sector = findViewById(R.id.sector);
        declare = findViewById(R.id.declare);
        declare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDeclaration();
            }
        });
        }

    public void saveDeclaration() {

        RequestQueue queue = Volley.newRequestQueue(DeclarationForm.this);
        String url = helper.host+"/declaration";
        Log.d("Req", url);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resp) {
                        // Display the first 500 characters of the response string.
                        Log.d("DeclarationResponse",resp);

                        try {
                            JSONObject response = new JSONObject(resp);
                            Snackbar.make(declare, response.getString("message"), Snackbar.LENGTH_SHORT).show();
                        }catch (JSONException ex){
                            Log.d("declareErr",ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("regno", reg.getText().toString().trim());
                params.put("academic_year", acayear.getText().toString());
                params.put("level_class", level_class.getText().toString());
                params.put("landnid", landid.getText().toString());
                params.put("landname", landLord_names.getText().toString());
                params.put("landphone", phoneLandlord.getText().toString());
                params.put("house_no", houseNo.getText().toString());
                params.put("district", district.getText().toString());
                params.put("sector", sector.getText().toString());
                params.put("cell", cell.getText().toString());
                params.put("village", village.getText().toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", helper.getDataValue("appid"));//put your token here
                return headers;
            }
        };;

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}