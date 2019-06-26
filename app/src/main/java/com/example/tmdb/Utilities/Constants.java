package com.example.tmdb.Utilities;

/**
 * Creado por Deimer el 23/10/18.
 */
public class Constants {

    public static final String URL = "https://api.themoviedb.org/3/";
    public static final String KEY = "a9082a0d75524555cfa6ee806cf1e80a";
    public static final String RUTA_POPULAR = URL+"movie/popular?api_key="+KEY;
    public static final String RUTA_UPCOMING = URL+"movie/upcoming?api_key="+KEY;
    public static final String RUTA_TOP_RATE = URL+"movie/top_rated?api_key="+KEY;

    public static final String RUTA_IMG_MINI = "https://image.tmdb.org/t/p/w200";
    public static final String RUTA_IMG_MEDIUM = "https://image.tmdb.org/t/p/w500";
}
