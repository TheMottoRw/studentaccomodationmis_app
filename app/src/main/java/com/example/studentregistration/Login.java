package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    public TextView create;
    private Button login;
    private ProgressDialog pgdialog;
    private Helper helper;
    private EditText edtPhone,edtPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPhone = findViewById(R.id.password);
        edtPassword = findViewById(R.id.edtPhone);
        login = findViewById(R.id.log_in);
        create  = findViewById(R.id.create);
        pgdialog = new ProgressDialog(this);
        helper = new Helper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentLogin();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateAccount.class));
            }
        });
    }
    public void studentLogin() {
        pgdialog.show();
        final String url = helper.host + "/studentLogin";
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
                                helper.showToast("Login succesful");
                                helper.setSession(array.getJSONArray("user_info").getJSONObject(0).toString());
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            } else {
                                helper.showToast("Failed to login");
                            }
                        } catch (JSONException ex) {
                            Log.d("loginErr",ex.getMessage());
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
                params.put("phone", edtPhone.getText().toString().trim());
                params.put("password", edtPassword.getText().toString());
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