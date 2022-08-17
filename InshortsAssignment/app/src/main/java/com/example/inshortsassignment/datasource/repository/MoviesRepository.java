package com.example.inshortsassignment.datasource.repository;

import com.example.inshortsassignment.datasource.model.MovieListItem;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;

public interface MoviesRepository {

    Observable<List<MovieListItem>> getTrendingMovies(int page);

    Observable<List<MovieListItem>> getNowPlayingMovies(int page);

    Observable<List<MovieListItem>> getMoviesForSearch(String query, int page, Boolean includeAdult);

    Observable<List<MovieListItem>> getFavouriteMovies();

    Completable saveFavouriteMovies(MovieListItem movieListItem);

    Single<Integer> isFavourite(Long id);

    Completable deleteFavouriteMovie(Long id);
}
