package com.example.tmdb.ListMovie;

import com.example.tmdb.Data.Model.MovieDto;

import java.util.ArrayList;

public class ListMoviePresenter implements ListMovieInteractor.OnListinishedListener{

    private ListMovieView view;
    private ListMovieInteractor interactor;

    public ListMoviePresenter(ListMovieView view, ListMovieInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void onResume(int category) {
        if (view != null) {
            view.showProgress();
        }
        interactor.findItems(this,category);
    }


    @Override
    public void onFinished(ArrayList<MovieDto> items) {
        if (view != null) {
            view.hideProgress();
        }
        view.setItems(items);
    }
}
