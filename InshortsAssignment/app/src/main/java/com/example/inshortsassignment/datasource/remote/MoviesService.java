package com.example.inshortsassignment.datasource.remote;

import com.example.inshortsassignment.datasource.model.MovieList;
import io.reactivex.Observable;

public interface MoviesService {

    Observable<MovieList> getTrendingMovies(int page);

    Observable<MovieList> getNowPlayingMovies(int page);

    Observable<MovieList> getMoviesForSearch(String query, int page, Boolean includeAdult);

}
