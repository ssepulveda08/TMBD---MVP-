package com.example.tmdb.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tmdb.Main.MainActivity;
import com.example.tmdb.R;

public class SplashActivity extends AppCompatActivity implements SplashView{

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this, new SplashInteractor(this));
        presenter.onResume(this);
    }

    @Override
    public void dataChange() {

    }

    @Override
    public void changeActivity() {
        new Handler().postDelayed(() ->{
            Intent startIntent = new Intent(this, MainActivity.class);
            startActivity(startIntent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//            finish();
        }, 2000);
    }

    @Override
    public void onMessage(String msg) {

    }
}
