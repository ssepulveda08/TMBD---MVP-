package com.example.tmdb.Splash;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.tmdb.Utilities.ValidateConnection;

public class SplashPresenter implements SplashInteractor.OnDataListener{

    private SplashView view;
    private SplashInteractor interactor;

    public SplashPresenter(SplashView view, SplashInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void onResume(Context context){
        Log.d("Debug","onResume presenter");
        //validar conexcion a internet
        if (ValidateConnection.isOnlineNet(context) ){
            interactor.getmovie(this);
        }else{
            Log.d("Debug","Sin internet");
            view.onMessage("No hay conexión a Internet");
            view.changeActivity();
        }
    }

    @Override
    public void onAlerta(String msg) {

    }

    @Override
    public void ChangeActivity() {
        Log.d("Debug", "change activity presenter");
        view.changeActivity();
    }
}
