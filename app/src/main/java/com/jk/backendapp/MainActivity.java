package com.jk.backendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new MakeRequest(new JSONObject(),MainActivity.this,"http://dummy.restapiexample.com/api/v1/employee/4", Request.Method.GET,
                true){

            @Override
            public JSONObject getJsonResponse(JSONObject j) throws JSONException {


                return null;
            }

            @Override
            public JSONArray getJSonArray(JSONArray jsonArray) {
                return null;
            }

            @Override
            public VolleyError getError(VolleyError geterror) {
                return null;
            }

            @Override
            public String response(String response) {
                Log.e("Data in JSON",response);
                return null;
            }
        };
    }
}
