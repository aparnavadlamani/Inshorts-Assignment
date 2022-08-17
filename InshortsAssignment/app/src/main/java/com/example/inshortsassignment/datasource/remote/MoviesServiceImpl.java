package com.example.inshortsassignment.datasource.remote;

import com.example.inshortsassignment.datasource.model.MovieList;
import com.example.inshortsassignment.datasource.remote.MoviesAPI;
import com.example.inshortsassignment.datasource.remote.MoviesService;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MoviesServiceImpl implements MoviesService {

    private final MoviesAPI moviesAPI;
    private static final String API_KEY = "fff5ceff8fb8579ead9b47d87bd7e803";
    private static final String LAN = "en-US";

    @Inject
    public MoviesServiceImpl(MoviesAPI moviesAPI) {
        this.moviesAPI = moviesAPI;
    }

    @Override
    public Observable<MovieList> getTrendingMovies(int page) {
        return moviesAPI.getTrendingMovies(API_KEY, page);
    }

    @Override
    public Observable<MovieList> getNowPlayingMovies(int page) {
        return moviesAPI.getNowPlayingMovies(API_KEY, LAN, page);
    }

    @Override
    public Observable<MovieList> getMoviesForSearch(String query, int page, Boolean includeAdult) {
        return moviesAPI.getMoviesForSearch(API_KEY, LAN, page, query, includeAdult);
    }

}
