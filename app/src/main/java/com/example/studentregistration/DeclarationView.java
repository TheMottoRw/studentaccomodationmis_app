package com.example.studentregistration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;

public class DeclarationView extends Fragment {

    private FloatingActionButton fabdeclare;
    private RecyclerView declareRecycle;
    private RecyclerView.LayoutManager layoutManager;
    public Context ContextCtx;
    private Helper helper;
    private ProgressDialog pgdialog;


    public DeclarationView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view  =  inflater.inflate(R.layout.declaration_view, container, false);

        fabdeclare = view.findViewById(R.id.fab_decla);

        // for recycle view
        declareRecycle = view.findViewById(R.id.declarecycle);
        layoutManager = new LinearLayoutManager(view.getContext());
        declareRecycle.setLayoutManager(layoutManager);
        ContextCtx = view.getContext();
        helper = new Helper(ContextCtx);
        pgdialog = new ProgressDialog(ContextCtx);
        pgdialog.setMessage(ContextCtx.getString(R.string.processrequest));
        fetch_Data();

        fabdeclare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adddeclaration  = new Intent (view.getContext(), DeclarationForm.class);
                startActivity(adddeclaration);
            }
        });


        return view;
    }
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        ContextCtx = ctx;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetch_Data();
    }

    private void fetch_Data() {
        pgdialog.show();
        RequestQueue queue = Volley.newRequestQueue(ContextCtx);
        String url = helper.host + "/student_declaration/" + helper.getDataValue("id");
        Log.d("Req", url);
// Request a string response from the provided URL.
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray array) {
                        pgdialog.dismiss();

                        Log.d("Resp", array.toString());
                        if (array.length() > 0) {
//
                            declareAdapter adaptExpenses = new declareAdapter(ContextCtx, array);
                            declareRecycle.setAdapter(adaptExpenses);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pgdialog.dismiss();
                Toast.makeText(ContextCtx, "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}