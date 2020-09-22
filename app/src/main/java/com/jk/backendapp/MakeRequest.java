package com.jk.backendapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class MakeRequest implements getJsonObject {

     private  ProgressDialog dialog=null;

     public MakeRequest(JSONObject jsonObject, final Activity activity,String url, int method, final Boolean isShow ){


         dialog=new ProgressDialog(activity);
         dialog.setTitle("Please Wait.");
         dialog.setMessage("Loading...");

         if (isShow)
         {
             dialog.show();
         }
         dialog.setCanceledOnTouchOutside(false);

         StringRequest stringRequest=new StringRequest(method, url, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 if (isShow){
                     dialog.dismiss();
                 }
                 Log.e("response",response);
                     response(response);

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 if (isShow) {
                     dialog.dismiss();
                 }
                 Log.e("Value", "" + error.getMessage());
                 getError(error);
                 VolleyLog.d("", "Error: " + error);
                 if (error instanceof NetworkError) {
                     if (activity != null)
                         Toast.makeText(activity, "No internet connection", Toast.LENGTH_LONG).show();
                 } else if (error instanceof ServerError) {
                     if (activity != null)
                         Toast.makeText(activity, "Server not responding", Toast.LENGTH_LONG).show();
                 } else if (error instanceof AuthFailureError) {
                     if (activity != null)
                         Toast.makeText(activity, "No internet connection", Toast.LENGTH_LONG).show();
                 } else if (error instanceof ParseError) {
                     if (activity != null)
                         Toast.makeText(activity, "Server not responding", Toast.LENGTH_LONG).show();
                 } else if (error instanceof NoConnectionError) {
                     if (activity != null)
                         Toast.makeText(activity, "No internet connection", Toast.LENGTH_LONG).show();
                 } else if (error instanceof TimeoutError) {
                     if (activity != null)
                         Toast.makeText(activity, "Connection TimeOut! Check your internet connection", Toast.LENGTH_LONG).show();
                 } else if (error.networkResponse == null) {
                     if (error.getClass().equals(TimeoutError.class)) {
                         if (activity != null)
                             if (!activity.isFinishing()) {
                                 Toast.makeText(activity, "Server not responding", Toast.LENGTH_LONG).show();
                             }

                     }
                 }
             }
         });



         stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                 0,
                 DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                 DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

         // Adding request to request queue
         String tag_json_obj = "json_obj_req";
         AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);


     }



}
