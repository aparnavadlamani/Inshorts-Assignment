package com.example.inshortsassignment.datasource.utils;

import com.example.inshortsassignment.datasource.model.MovieEntity;
import com.example.inshortsassignment.datasource.model.MovieListItem;

public final class MovieHelper {
    public static MovieListItem getMovieListItem(MovieEntity movieEntity) {
        boolean adult = movieEntity.isAdult();
        String backdropPath = movieEntity.getBackdropPath();
        Long id = movieEntity.getId();
        String title = movieEntity.getTitle();
        String originalLanguage = movieEntity.getOriginalLanguage();
        String originalTitle = movieEntity.getOriginalTitle();
        String overview = movieEntity.getOverview();
        String posterPath = movieEntity.getPosterPath();
        double popularity = movieEntity.getPopularity();
        String releaseDate = movieEntity.getReleaseDate();
        double voteAverage = movieEntity.getVoteAverage();
        int voteCount = movieEntity.getVoteCount();

        return new MovieListItem(adult, backdropPath, id, title, originalLanguage, originalTitle, overview, posterPath, popularity, releaseDate, voteAverage, voteCount);
    }

    public static MovieEntity getMovieEntity(MovieListItem movieListItem) {
        boolean adult = movieListItem.adult;
        String backdropPath = movieListItem.backdropPath;
        Long id = movieListItem.id;
        String title = movieListItem.title;
        String originalLanguage = movieListItem.originalLanguage;
        String originalTitle = movieListItem.originalTitle;
        String overview = movieListItem.overview;
        String posterPath = movieListItem.posterPath;
        double popularity = movieListItem.popularity;
        String releaseDate = movieListItem.releaseDate;
        double voteAverage = movieListItem.voteAverage;
        int voteCount = movieListItem.voteCount;

        return new MovieEntity(adult, backdropPath, id, title, originalLanguage, originalTitle, overview, posterPath, popularity, releaseDate, voteAverage, voteCount, true);
    }
}
