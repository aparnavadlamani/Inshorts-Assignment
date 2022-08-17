package com.example.inshortsassignment.datasource.remote;

import com.example.inshortsassignment.datasource.model.MovieList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesAPI {

    @GET("trending/movie/day")
    Observable<MovieList> getTrendingMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/now_playing")
    Observable<MovieList> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("search/movie")
    Observable<MovieList> getMoviesForSearch(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page,
                                             @Query("query") String query, @Query("include_adult") Boolean includeAdult);

}
