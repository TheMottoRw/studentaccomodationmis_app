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

public class declareAdapter extends RecyclerView.Adapter<declareAdapter.ViewHolder> {

    private JSONArray declarationData;
    public LinearLayout ln;
    public Context contx;

    public declareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ln = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.declare_text_view, parent, false);
        declareAdapter.ViewHolder vh = new declareAdapter.ViewHolder(ln);
        return vh;

    }
    public declareAdapter(Context context, JSONArray reservdata) {
        declarationData = reservdata;
        contx = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView TvName, TvlandlordName,Tvregno, Tvacademic;
        public ViewHolder(LinearLayout  lnout) {
            super(lnout);

            TvName = lnout.findViewById(R.id.student);
            Tvacademic = lnout.findViewById(R.id.aca);
            TvlandlordName = lnout.findViewById(R.id.lname);
            Tvregno = lnout.findViewById(R.id.stid);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull declareAdapter.ViewHolder holder, int position) {
        {
            try {
                JSONObject object = declarationData.getJSONObject(position);

                holder.TvName.setText(object.getString("stuname"));
                holder.Tvacademic.setText(object.getString("academic"));
                holder.TvlandlordName.setText(object.getString("landName"));
                holder.Tvregno.setText(object.getString("regno")); // for registrationNumber


            }catch(JSONException ex){

            }
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
