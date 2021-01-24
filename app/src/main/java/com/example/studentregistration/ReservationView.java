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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;


public class ReservationView extends Fragment {

    private FloatingActionButton fabserve;
    private RecyclerView reserveRecycle;
    private RecyclerView.LayoutManager layoutManager;
    public Context ContextCtx;
    private Helper helper;
    private ProgressDialog pgdialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.reservation_tab, container, false);


        fabserve = view.findViewById(R.id.fab_reserv);
        helper = new Helper(ContextCtx);
        pgdialog = new ProgressDialog(ContextCtx);
        pgdialog.setMessage(ContextCtx.getString(R.string.processrequest));


        // for recycle view
        reserveRecycle = view.findViewById(R.id.reservation_recycle);
        layoutManager = new LinearLayoutManager(view.getContext());
        reserveRecycle.setLayoutManager(layoutManager);
        fetch_Data();


        fabserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReservation = new Intent(view.getContext(), ReserveAccomodation.class);
                startActivity(addReservation);
            }
        });

        fetch_Data();
        return view;
    }

    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        ContextCtx = ctx;


    }

    @Override
    public void onResume() {
        super.onResume();
//        fetch_Data();
    }

    private void fetch_Data() {
        pgdialog.show();
        RequestQueue queue = Volley.newRequestQueue(ContextCtx);
        String url = helper.host + "/student_reservation/" + helper.getDataValue("id");
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
                            ReserveAdapter adaptExpenses = new ReserveAdapter(ContextCtx, array);
                            reserveRecycle.setAdapter(adaptExpenses);


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
