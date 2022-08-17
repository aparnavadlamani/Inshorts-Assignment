package com.example.inshortsassignment.datasource.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
public class MovieListItem implements Parcelable {

    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    @Expose
    public boolean adult;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    public Long id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    public String title;

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    @Expose
    public String originalTitle;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    @Expose
    public String overview;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    @Expose
    public String posterPath;

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    @Expose
    public double popularity;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    @Expose
    public double voteAverage;

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    @Expose
    public int voteCount;

    public MovieListItem(boolean adult, String backdropPath, Long id, String title, String originalLanguage, String originalTitle, String overview, String posterPath, double popularity, String releaseDate, double voteAverage, int voteCount) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.id = id;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(adult);
        parcel.writeString(backdropPath);
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(originalLanguage);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
        parcel.writeDouble(popularity);
        parcel.writeString(releaseDate);
        parcel.writeDouble(voteAverage);
        parcel.writeInt(voteCount);
    }
}
