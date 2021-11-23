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

import org.json.JSONArray;
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

        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.password);
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
        final String url = helper.host + "/login";
        pgdialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("phone", edtPhone.getText().toString().trim());
        params.put("password", edtPassword.getText().toString());
        Log.d("LoginUrl",url);
        Log.d("LoginData",params.toString());
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
// prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String arr) {
                        // display response
                        pgdialog.dismiss();
                        try {
                            JSONObject array = new JSONObject(arr);
                            if (array.getString("status").equals("ok")) {
                                helper.showToast("Login succesful");
                                helper.setSession(array.getJSONObject("user_info").toString());
                                Intent intent = new Intent(Login.this,MainActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Log.d("LoginStatus",array.toString());
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
                Log.d("LoginData",params.toString());
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