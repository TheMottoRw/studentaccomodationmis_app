package com.example.studentregistration;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DeclarationView extends Fragment {

    private FloatingActionButton fabdeclare;
    private RecyclerView declareRecycle;
    private RecyclerView.LayoutManager layoutManager;

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

    private void fetch_Data() {
    }
}