package com.example.tmdb.Data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieDto implements Parcelable {

    int vote_count;
    int id;
    boolean video;
    String vote_average;
    String title;
    String popularity;
    String poster_path;
    String original_language;
    String original_title;
    String backdrop_path;
    boolean adult;
    String overview;
    String release_data;
    int Category; //1.Popular, 2.Top Rated, 3.Upcoming.


    public MovieDto( int id, int vote_count,
                     boolean video, String vote_average,
                     String title, String popularity,
                     String poster_path, String original_language,
                     String original_title, String backdrop_path,
                     boolean adult, String overview, String release_data, int category) {
        this.vote_count = vote_count;
        this.id = id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_data = release_data;
        Category = category;
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_data() {
        return release_data;
    }

    public int getCategory() {
        return Category;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.vote_count);
        dest.writeInt(this.id);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeString(this.vote_average);
        dest.writeString(this.title);
        dest.writeString(this.popularity);
        dest.writeString(this.poster_path);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.backdrop_path);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.overview);
        dest.writeString(this.release_data);
        dest.writeInt(this.Category);
    }

    protected MovieDto(Parcel in) {
        this.vote_count = in.readInt();
        this.id = in.readInt();
        this.video = in.readByte() != 0;
        this.vote_average = in.readString();
        this.title = in.readString();
        this.popularity = in.readString();
        this.poster_path = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.backdrop_path = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.release_data = in.readString();
        this.Category = in.readInt();
    }

    public static final Parcelable.Creator<MovieDto> CREATOR = new Parcelable.Creator<MovieDto>() {
        @Override
        public MovieDto createFromParcel(Parcel source) {
            return new MovieDto(source);
        }

        @Override
        public MovieDto[] newArray(int size) {
            return new MovieDto[size];
        }
    };
}
