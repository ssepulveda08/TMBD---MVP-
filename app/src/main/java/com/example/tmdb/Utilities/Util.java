package com.example.tmdb.Utilities;

import android.view.View;

import com.example.tmdb.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Util {

    public static void OnAlertaSnackbar(View view, String msg){
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.aceptar, v -> {
            snackbar.dismiss();
        })
                .setActionTextColor(view.getResources().getColor(R.color.colorwhite))
                .show();
    }

    public static String[] getCategoryJson(JSONObject details) {
        try{
            JSONArray  arr = details.getJSONArray("genres");
            String[] resul = new String[arr.length()];
            for (int i = 0; i < arr.length() ; i++) {
                JSONObject row = arr.getJSONObject(i);
                resul[i] = row.getString("name");
            }
            return resul;
        }catch (JSONException e){
            return new String[0];
        }
    }


    public static String getLanguageJson(JSONObject details) {
        try{
            String resul = "";
            JSONArray  arr = details.getJSONArray("spoken_languages");

            if (arr.length() > 0){
                JSONObject row = arr.getJSONObject(0);
                resul = row.getString("name");
            }
            return resul;
        }catch (JSONException e){
            return "";
        }
    }


}
