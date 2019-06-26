package com.example.tmdb.Detail;

import android.content.Context;
import android.util.Log;

import com.example.tmdb.Utilities.Util;
import com.example.tmdb.Utilities.ValidateConnection;

import org.json.JSONObject;

public class DetailPresenter implements DetailInteractor.OngetDataListener{

    private DetailView view;
    private DetailInteractor interactor;

    public DetailPresenter(DetailView view, DetailInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void onResume(){
       if (view != null){
           view.RenderView();
           view.RenderData();
       }
    }

    public void onVideo(String id){
        interactor.geturlvideo(id,this);
    }
    public void onDetail(Context context, String id){
        if (ValidateConnection.isOnlineNet(context) ){
            interactor.getDetailsMovie(id, this);
        }
    }

    @Override
    public void onAlerta(String msg) {
        view.onMessage(msg);
    }

    @Override
    public void setvideo(String url) {
        view.onListenerFabPlay(url);
    }

    @Override
    public void setdetail(JSONObject details) {
        String[]  arr = Util.getCategoryJson(details);
        if (arr.length >0){
            view.showCategorys(arr);
        }
        String language = Util.getLanguageJson(details);
        view.showLaguage(language);
    }
}
