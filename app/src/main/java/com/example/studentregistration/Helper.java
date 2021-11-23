package com.example.studentregistration;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Helper {
    public String host = "";
    public Context ctx;
    public Helper(Context context){
        ctx = context;
        host = getHost();
    }
    //    public static String host = "https://www.mbwira.rw/Methode/armory/api/requests";
    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    public void toggleNetworkConnectivityTextView(TextView tv){
        if(isNetworkConnected()) tv.setVisibility(View.GONE);
        else tv.setVisibility(View.VISIBLE);
        tv.refreshDrawableState();
    }
    public void setHost(String host){
        SharedPreferences.Editor sh = this.getEditor().edit();
        sh.putString("host",host);
        sh.apply();
    }
    public String getHost(){
        String local = "http://192.168.13.120/RUT/accomodationmis/index.php/api",
                remote = "http://10.0.2.2/RUT/accomodationmis/index.php/api";
        return getEditor().getString("host",remote);
    }

    public void showToast(String message){
        Toast.makeText(ctx,message,Toast.LENGTH_LONG).show();
    }

    public void setSession(String obj){
        SharedPreferences.Editor sh = this.getEditor().edit();
        sh.putString("user_info",obj);
        sh.apply();
    }
    public void  logout(){
        SharedPreferences.Editor sh = getEditor().edit();
        sh.remove("user_info");
        sh.apply();
    }
    public boolean hasSession(){
        return this.getEditor().contains("user_info");
    }
    public String getDataValue(String parameter){
        String val = "";
        SharedPreferences sh = ctx.getSharedPreferences("accomodation",Context.MODE_PRIVATE);
        try{
            JSONObject obj = new JSONObject(sh.getString("user_info",""));
            if(!obj.equals(new JSONObject())){
                val = obj.getString(parameter);
            }
        }catch (JSONException ex){
            Log.d("userErr",ex.getMessage());
        }
        return val;
    }
    public SharedPreferences getEditor(){
        SharedPreferences sh = ctx.getSharedPreferences("accomodation",Context.MODE_PRIVATE);
        return sh;
    }
}
