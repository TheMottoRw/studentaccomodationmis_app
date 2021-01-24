package com.example.studentregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.ViewHolder> {

    private JSONArray dataReserve;
    public LinearLayout ln;
    public Context contx;


    @Override
    public ReserveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,  int viewType) {
        // create a new view
        ln = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reservation_texview, parent, false);
       ViewHolder vh = new ViewHolder(ln);
        return vh;
    }

    public ReserveAdapter(Context context, JSONArray reservdata) {
        dataReserve = reservdata;
        contx = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView TvName, Tvacademic, Tvroom, Tvmyid;
        public ViewHolder(LinearLayout  lnout) {
            super(lnout);

            TvName = lnout.findViewById(R.id.stname);
            Tvacademic = lnout.findViewById(R.id.date);
            Tvroom = lnout.findViewById(R.id.room_number);
            Tvmyid = lnout.findViewById(R.id.myid);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject object = dataReserve.getJSONObject(position);

            holder.TvName.setText(object.getString("names"));
            holder.Tvacademic.setText(object.getString("academic_year"));
            holder.Tvroom.setText(object.getString("room"));
//            holder.Tvmyid.setText(object.getString("registernumber"));


        }catch(JSONException ex){

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
