package com.example.tmdb.Detail;

import org.json.JSONObject;

;

public interface DetailView {
    void showProgress();
    void hideProgress();
    void RenderView();
    void RenderData();
    void onListenerFabPlay(String key);
    void onMessage(String msg);
//    void onrenderDataDetails(JSONObject json);
    void showCategorys(String[] array);
    void showLaguage(String Array);
    void Showtagline();

}
