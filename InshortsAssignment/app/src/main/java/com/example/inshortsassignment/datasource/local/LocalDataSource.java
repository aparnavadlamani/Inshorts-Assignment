package com.example.inshortsassignment.datasource.local;

import com.example.inshortsassignment.datasource.model.MovieEntity;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;

public interface LocalDataSource {

    Observable<List<MovieListItem>> getFavouriteMovies();

    Observable<List<MovieListItem>> getMovieListFromLocal();

    Observable<List<MovieListItem>> getMovieListBasedOnKeyword(String keyword);

    void insertAll(List<MovieListItem> movieListItems);

    void deleteAll();

    void saveFavourite(MovieEntity movieEntity);

    void deleteFavourite(Long id);

    Single<Integer> isFavourite(Long id);

}
