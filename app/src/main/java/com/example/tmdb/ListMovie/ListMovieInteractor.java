package com.example.tmdb.ListMovie;

import android.content.Context;
import android.os.Handler;

import com.example.tmdb.Data.DictionaryOpenHelper;
import com.example.tmdb.Data.Model.MovieDto;

import java.util.ArrayList;

public class ListMovieInteractor {
    private Context mcontext;

    public ListMovieInteractor(Context context) {
        this.mcontext = context;
    }

    public interface OnListinishedListener {
        void onFinished(ArrayList<MovieDto> items);
    }

    public void findItems(final OnListinishedListener listener, int mcategory) {
        new Handler().postDelayed(() -> listener.onFinished(createArrayList(mcategory)), 1000);
    }

    private ArrayList<MovieDto> createArrayList(int mcategory) {
        DictionaryOpenHelper db = new DictionaryOpenHelper(mcontext);
        ArrayList<MovieDto> arraydb = db.getMovieCategory(mcategory+1);
        System.out.println("Count: "+arraydb.size());
        return arraydb;
    }


}
