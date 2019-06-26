package com.example.tmdb.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tmdb.Data.Model.MovieDto;
import com.example.tmdb.Data.dbTableContract.MOVIE;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.tmdb.Data.ScriptDB.SQL_CREATE_MOVIE;


public class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DICTIONARY_TABLE_NAME = "TMDB";
    private static final String     SQL_DELETE_MOVIE =
            "DELETE FROM " + MOVIE.TABLE_NAME;

    public DictionaryOpenHelper(Context context) {
        super(context, DICTIONARY_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //EJECUTAR SCRIPT DE BD
        db.execSQL(SQL_CREATE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void ClearTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_MOVIE);
        db.close();
    }

    public long insertMovie(JSONObject row , int Categori) throws  JSONException{
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MOVIE.COLUMN_ID, row.getInt("id"));
            values.put(MOVIE.COLUMN_VOTE_COUNT, row.getString( "vote_count"));
            values.put(MOVIE.COLUMN_VOTE_AVERAGE, row.getString( "vote_average"));
            values.put(MOVIE.COLUMN_VIDEO, row.getString( "video"));
            values.put(MOVIE.COLUMN_TITLE , row.getString( "title"));
            values.put(MOVIE.COLUMN_POPULARITY, row.getString( "popularity"));
            values.put(MOVIE.COLUMN_POSTER_PATH, row.getString( "poster_path"));
            values.put(MOVIE.COLUMN_ORIGINAL_LANGIAGE, row.getString( "original_language"));
            values.put(MOVIE.COLUMN_ORIGINAL_TITLE, row.getString( "original_title"));
            values.put(MOVIE.COLUMN_BACKDROP_PATH, row.getString( "backdrop_path"));
            values.put(MOVIE.COLUMN_ADULT , row.getString( "adult"));
            values.put(MOVIE.COLUMN_OVERVIEW, row.getString( "overview"));
            values.put(MOVIE.COLUMN_RELEASE_DATA, row.getString( "release_date"));
            values.put(MOVIE.COLUMN_CATEGORY , Categori);

        // insert row
        long idx = db.insert(MOVIE.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return idx;
    }


    public ArrayList<MovieDto> getMovieCategory(int category) {
        ArrayList<MovieDto> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MOVIE.TABLE_NAME + " WHERE " + MOVIE.COLUMN_CATEGORY +"="+ category+ " ORDER BY " +
                MOVIE.COLUMN_TITLE + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MovieDto note = new MovieDto(
                        cursor.getInt(cursor.getColumnIndex(MOVIE.COLUMN_ID)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_VOTE_COUNT))),
                        Boolean.valueOf(cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_VIDEO))),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_VOTE_AVERAGE)),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_POPULARITY)),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_POSTER_PATH)),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_ORIGINAL_LANGIAGE)),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_ORIGINAL_TITLE)),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_BACKDROP_PATH)),
                        Boolean.valueOf(cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_ADULT))),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_OVERVIEW)),
                        cursor.getString(cursor.getColumnIndex(MOVIE.COLUMN_RELEASE_DATA)),
                        cursor.getInt(cursor.getColumnIndex(MOVIE.COLUMN_CATEGORY)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }






}
