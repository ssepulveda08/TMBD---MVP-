package com.example.tmdb.ListMovie;

import com.example.tmdb.Data.Model.MovieDto;

import java.util.ArrayList;

public interface ListMovieView {
    void showProgress();
    void hideProgress();
    void setItems(ArrayList<MovieDto> items);
    void showMessage(String message);
}
