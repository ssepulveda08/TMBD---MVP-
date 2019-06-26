package com.example.tmdb.Splash;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.tmdb.Data.DictionaryOpenHelper;
import com.example.tmdb.volleySingleton.mySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.tmdb.Utilities.Constants.RUTA_POPULAR;
import static com.example.tmdb.Utilities.Constants.RUTA_TOP_RATE;
import static com.example.tmdb.Utilities.Constants.RUTA_UPCOMING;

public class SplashInteractor {
    Context mcontext;
    public DictionaryOpenHelper db;

    public SplashInteractor(Context mcontext) {
        this.mcontext = mcontext;
        this.db = new DictionaryOpenHelper(this.mcontext);
    }


    public interface OnDataListener {
        void onAlerta(String msg);
        void ChangeActivity();
    }

    /**Metodo para consultar las peliculas por categoria*/
    public void getmovie(final OnDataListener listener){
//        new Handler().postDelayed(() ->{
            db.ClearTable(); //limpiar la base de datos
            Log.d("DEBUG", "getmovie: ");
            Checkmovies(RUTA_POPULAR,1, listener);
            Checkmovies(RUTA_TOP_RATE,2, listener);
            Checkmovies(RUTA_UPCOMING,3, listener);
//        }, 1000);
    }

    private void Checkmovies(String Ruta, int Category, OnDataListener listener){
        Log.d("Checkmovies" , Ruta);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Ruta,
                (String response) -> {
                    System.out.println("Respuesta servidor REGISTRO:"+response);
                    try {
                        JSONObject row2 = new JSONObject(response);
                        JSONArray  array  = row2.getJSONArray("results");
                        for (int i = 0; i <array.length() ; i++) {
                            JSONObject row = array.getJSONObject(i);
                            db.insertMovie(row, Category);
                        }
                        if (Category == 3){
                            listener.ChangeActivity();
                        }
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
        mySingleton.getInstance(mcontext).addToRequestQueue(stringRequest);

    }
}
