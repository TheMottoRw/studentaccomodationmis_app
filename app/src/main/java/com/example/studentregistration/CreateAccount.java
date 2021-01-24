package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    private EditText name,id,national,phone_student,password;
    private RadioButton male, female;
    private Spinner department;
    private RadioGroup group;
    private Button register;
    private ProgressDialog pgdialog;
    private Helper helper;
    public String gender = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        helper = new Helper(getApplicationContext());
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));

        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        national = findViewById(R.id.national);
        phone_student = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        group = findViewById(R.id.radiogroup);
        department = findViewById(R.id.department);

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentSignup();
            }
        });
    }
    public void studentSignup() {
        pgdialog.show();
        if(male.isChecked()) gender = "Male";
        else gender = "Female";
        final String url = helper.host + "/signup";
        pgdialog.show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject array) {
                        // display response
                        pgdialog.dismiss();
                        try {
                            if (array.getString("status").equals("ok")) {
                                helper.showToast("Account created successful");
                                helper.setSession(array.getJSONArray("user_info").getJSONObject(0).toString());
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            } else {
                                helper.showToast("Failed to create account");
                            }
                        } catch (JSONException ex) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pgdialog.dismiss();
                        Log.e("jsonerr", "JSON Error " + (error != null ? error.getMessage() : ""));
                    }
                }
        ) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("names", name.getText().toString().trim());
                params.put("phone", phone_student.getText().toString().trim());
                params.put("regno", id.getText().toString().trim());
                params.put("nid", national.getText().toString().trim());
                params.put("gender", gender.trim());
                params.put("department", department.getSelectedItem().toString());
                params.put("password", password.getText().toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", helper.getDataValue("appid"));//put your token here
                return headers;
            }
        };
        ;

// add it to the RequestQueue
        queue.add(getRequest);
    }
}