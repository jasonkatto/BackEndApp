package com.jk.backendapp;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface getJsonObject {
    JSONObject getJsonResponse(JSONObject j) throws JSONException;
    JSONArray getJSonArray(JSONArray jsonArray);
    VolleyError getError(VolleyError geterror);
    String response(String response);
}
