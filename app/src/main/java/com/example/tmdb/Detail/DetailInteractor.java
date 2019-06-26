package com.example.tmdb.Detail;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.tmdb.Splash.SplashInteractor;
import com.example.tmdb.volleySingleton.mySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailInteractor {
    private Context context;

    public DetailInteractor(Context context) {
        this.context = context;
    }

    public interface OngetDataListener {
        void onAlerta(String msg);
        void setvideo(String url);
        void setdetail(JSONObject keyw);
    }

    public void geturlvideo(String id, final OngetDataListener listener){
        getVideo(id,listener);
    }
    public void getDetailsMovie(String id, final OngetDataListener listener){
        getDetails(id,listener);
    }


    private void getVideo(String id, OngetDataListener listener){
        String ruta = "http://api.themoviedb.org/3/movie/"+id+"/videos?api_key=a9082a0d75524555cfa6ee806cf1e80a";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ruta,
                (String response) -> {
                    System.out.println("Respuesta servidor REGISTRO:"+response);
                    try {
                        String keyw ="";
                        JSONObject row2 = new JSONObject(response);
                        JSONArray array  = row2.getJSONArray("results");
                        if (array.length()>0){
                            JSONObject row = array.getJSONObject(0);
                            keyw = row.getString("key");
                        }
                        listener.setvideo(keyw);

                    } catch (JSONException e) {
                        Log.d("DEBUG", "Error: "+e.getMessage());
                    }
                },
                error -> {
                    Log.d("DEBUG", "Error: "+error.getMessage());
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        mySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }


    private void getDetails(String id, OngetDataListener listener){
        String ruta = "http://api.themoviedb.org/3/movie/"+id+"?api_key=a9082a0d75524555cfa6ee806cf1e80a";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ruta,
                (String response) -> {
                    System.out.println("Respuesta servidor REGISTRO:"+response);
                    try {
                        JSONObject row2 = new JSONObject(response);
                        listener.setdetail(row2);

                    } catch (JSONException e) {
                        Log.d("DEBUG", "Error: "+e.getMessage());
                    }
                },
                error -> {
                    Log.d("DEBUG", "Error: "+error.getMessage());
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        mySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
}
