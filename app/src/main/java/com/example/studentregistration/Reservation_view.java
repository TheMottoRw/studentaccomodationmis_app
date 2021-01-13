package com.example.studentregistration;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Reservation_view extends Fragment {

    private FloatingActionButton fabserve;
    private RecyclerView reserveRecycle;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.reservation_tab, container, false);


        fabserve = view.findViewById(R.id.fab_reserv);

        // for recycle view
        reserveRecycle = view.findViewById(R.id.reservation_recycle);
        layoutManager = new LinearLayoutManager(view.getContext());
        reserveRecycle.setLayoutManager(layoutManager);
        fetch_Data();


        fabserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReservation  = new Intent (view.getContext(),Reserve_Accomodation.class);
                startActivity(addReservation);
            }
        });

        fetch_Data();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetch_Data();
    }
    private void fetch_Data() {
    }
}