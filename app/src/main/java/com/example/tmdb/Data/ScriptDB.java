package com.example.tmdb.Data;

import static com.example.tmdb.Data.dbTableContract.*;

/**
 * Creado por Deimer el 16/10/18.
 */
public class ScriptDB {


    public static final String SQL_CREATE_MOVIE =
            "CREATE TABLE " + MOVIE.TABLE_NAME + " (" +
                    MOVIE._ID + " INTEGER PRIMARY KEY," +
                    MOVIE.COLUMN_ID + " INTEGER,"+
                    MOVIE.COLUMN_VOTE_COUNT + " TEXT," +
                    MOVIE.COLUMN_VIDEO + " TEXT," +
                    MOVIE.COLUMN_VOTE_AVERAGE + " TEXT," +
                    MOVIE.COLUMN_TITLE + " TEXT," +
                    MOVIE.COLUMN_POPULARITY + " TEXT," +
                    MOVIE.COLUMN_POSTER_PATH + " TEXT," +
                    MOVIE.COLUMN_BACKDROP_PATH + " TEXT," +
                    MOVIE.COLUMN_ORIGINAL_LANGIAGE + " TEXT," +
                    MOVIE.COLUMN_ORIGINAL_TITLE + " TEXT," +
                    MOVIE.COLUMN_ADULT + " TEXT," +
                    MOVIE.COLUMN_OVERVIEW + " TEXT," +
                    MOVIE.COLUMN_RELEASE_DATA + " TEXT," +
                    MOVIE.COLUMN_CATEGORY + " INTEGER)";




}
