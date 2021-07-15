package com.example.tocomdor;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Api {
    public static void getEstabelecimentos(float lat, float lon, float range, final Context context ){
        RequestQueue queue = Volley.newRequestQueue(context);
        //TODO add url
        String url ="";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Estabelecimento> ests = new ArrayList<Estabelecimento>();
                            Estabelecimento currEst;

                            JSONArray jsArray = new JSONArray(response);
                            JSONObject json;

                            for (int i = 0; i < jsArray.length(); i++) {
                                json = jsArray.getJSONObject(i);
                                currEst = new Estabelecimento(json);
                                ests.add(currEst);
                            }
                            //TODO colocar retorno
//                            repo.chegouresposta( ests );

                        }catch (JSONException e) {
                            //TODO colocar retorno
//                           repo.chegouresposta( Collections.emptyList() );
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("erro", "error response volley. " + error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("latitude", Float.toString(lat));
                params.put("longitude", Float.toString(lon));
                params.put("margem", Float.toString(range));

                return params;
            }
        };

        queue.add(stringRequest);
    }
}
