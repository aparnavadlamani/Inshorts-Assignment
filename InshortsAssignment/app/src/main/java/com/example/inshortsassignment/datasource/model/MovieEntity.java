package com.example.inshortsassignment.datasource.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_movies")
public class MovieEntity {

    @ColumnInfo(name = "adult")
    private final boolean adult;

    @ColumnInfo(name = "backdrop_path")
    private final String backdropPath;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final Long id;

    @ColumnInfo(name = "title")
    private final String title;

    @ColumnInfo(name = "original_language")
    private final String originalLanguage;

    @ColumnInfo(name = "original_title")
    private final String originalTitle;

    @ColumnInfo(name = "overview")
    private final String overview;

    @ColumnInfo(name = "poster_path")
    private final String posterPath;

    @ColumnInfo(name = "popularity")
    private final double popularity;

    @ColumnInfo(name = "release_date")
    private final String releaseDate;

    @ColumnInfo(name = "vote_average")
    private final double voteAverage;

    @ColumnInfo(name = "vote_count")
    private final int voteCount;

    @ColumnInfo(name = "favourite")
    private final boolean favourite;

    public MovieEntity(boolean adult, String backdropPath, @NonNull Long id, String title, String originalLanguage, String originalTitle, String overview, String posterPath, double popularity, String releaseDate, double voteAverage, int voteCount, boolean favourite) {
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
        this.favourite = favourite;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isFavourite() {
        return favourite;
    }
}
