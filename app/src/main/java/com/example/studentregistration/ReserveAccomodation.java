package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReserveAccomodation extends AppCompatActivity {

    private EditText registerNumber,studentname,level,academic;
    private Spinner room;
    private Button reserve;
    private Helper helper;
    private ProgressDialog pgdialog;
    private String[] arrayList;
    private JSONArray arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_accomodation_form);
        helper = new Helper(this);
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));

        registerNumber = findViewById(R.id.registernumber);
        studentname = findViewById(R.id.stuname);
        level = findViewById(R.id.level);
        academic = findViewById(R.id.academic);
        room = findViewById(R.id.room);
        reserve = findViewById(R.id.reserve);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReservation();
            }
        });
        loadRooms();

    }
    public void loadRooms() {

        RequestQueue queue = Volley.newRequestQueue(ReserveAccomodation.this);
        String url = helper.host+"/rooms";
        Log.d("Req", url);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resp) {
                        // Display the first 500 characters of the response string.
                        try {
                            Log.d("ReserveResp",resp);
                            arr = new JSONArray(resp);
                            arrayList = new String[arr.length()];
                            for (int i=0;i<arr.length();i++){
                                JSONObject obj = arr.getJSONObject(i);
                                arrayList[i] = obj.getString("names")+" ("+obj.getString("host")+")";

                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReserveAccomodation.this, android.R.layout.simple_spinner_dropdown_item,arrayList);
                            room.setAdapter(adapter);

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
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", helper.getDataValue("appid"));//put your token here
                return headers;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    void saveReservation(){

        RequestQueue queue = Volley.newRequestQueue(ReserveAccomodation.this);
        String url = helper.host+"/reserve";
        Log.d("Req", url);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resp) {
                        // Display the first 500 characters of the response string.
                        try {
                            Log.d("ReserveResp",resp);
                            JSONObject response = new JSONObject(resp);
                            Snackbar.make(registerNumber, response.getString("message"), Snackbar.LENGTH_SHORT).show();
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
                String roomid = "";
                try {
                    roomid = arr.getJSONObject(room.getSelectedItemPosition()).getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Map<String, String> params = new HashMap<>();
                params.put("regno", registerNumber.getText().toString().trim());
                params.put("academic_year", academic.getText().toString());
                params.put("level_class", level.getText().toString());
                params.put("room_id", roomid);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", helper.getDataValue("appid"));//put your token here
                return headers;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}