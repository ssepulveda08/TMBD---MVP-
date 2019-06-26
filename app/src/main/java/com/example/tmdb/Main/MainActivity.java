package com.example.tmdb.Main;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.example.tmdb.ListMovie.ListMovieFragment;
import com.example.tmdb.Main.ui.main.SectionsPagerAdapter;
import com.example.tmdb.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  implements MainView,
        ListMovieFragment.OnFragmentInteractionListener{

    private MainPresenter presenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new MainPresenter(this, new MainInteractor(this));
        presenter.onResume();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void stratView() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}