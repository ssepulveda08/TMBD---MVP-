package com.example.tmdb.Data;

import android.provider.BaseColumns;

/**
 * Creado por santiago
 */
public class dbTableContract {

    private dbTableContract() {}
        public static class MOVIE implements BaseColumns {
        public static final String TABLE_NAME = "MOVIE";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_VOTE_COUNT= "vote_count";
        public static final String COLUMN_VOTE_AVERAGE= "vote_average";
        public static final String COLUMN_VIDEO= "video";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_ORIGINAL_LANGIAGE = "original_language";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_ADULT = "adult";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATA = "release_data";
        public static final String COLUMN_CATEGORY = "category";
    }


}
