package com.example.tocomdor;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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
//        String url ="https://tocomdor.uw.r.appspot.com/unidadeDetalhes";
        String url ="https://tocomdor.uw.r.appspot.com/unidadesProximasRef";

        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("latitude", lat);
            jsonobject.put("longitude", lon);
            jsonobject.put("margem", range);
        } catch (Exception e){
            Log.d("API", "JSON Error: " + e.getMessage());
        }


        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                Request.Method.POST,url, jsonobject,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("api", "response: " + response.toString());
                        Log.d("api", "response length: " + response.length());

                        JSONObject json;
                        Estabelecimento currEst;
                        List<Estabelecimento> ests = new ArrayList<Estabelecimento>();

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                json = response.getJSONObject(i);
                                currEst = new Estabelecimento(json);
                                ests.add(currEst);
                            }
                        } catch (Exception e){
                            Log.d("API", "JSON read Error: " + e.getMessage());
                        }

                        //TODO Chamar funcao de retorno
                        Estabelecimento.Estabelecimentos.setEstabProximos(ests);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("api", "Android Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        queue.add(jsonArrReq);
    }



    public static void registraResposta(Resposta resp, final Context context ) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://tocomdor.uw.r.appspot.com/registraResposta";

        JSONObject jsonobject = new JSONObject();
        try {
            jsonobject.put("nsus", Integer.toString(0));
            jsonobject.put("latitude", resp.lat);
            jsonobject.put("longitude", resp.lon);
            jsonobject.put("resultado", resp.result);
            jsonobject.put("respostas", resp.getResStr()); //TODO Colocar a funcao do HB
            jsonobject.put("versao", 1); //TODO Criar um recurso com a versao
        } catch (Exception e){
            Log.d("API", "JSON Error: " + e.getMessage());
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,url, jsonobject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("api", "response registra: " + response.toString());

                        //TODO Tratar Erros
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("api", "Android Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        queue.add(jsonObjReq);
    }
}
