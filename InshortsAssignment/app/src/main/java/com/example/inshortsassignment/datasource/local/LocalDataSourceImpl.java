package com.example.inshortsassignment.datasource.local;

import android.util.Log;
import com.example.inshortsassignment.datasource.utils.MovieHelper;
import com.example.inshortsassignment.datasource.utils.Utils;
import com.example.inshortsassignment.datasource.model.MovieEntity;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

public class LocalDataSourceImpl implements LocalDataSource {
    private final MoviesDao moviesDao;

    @Inject
    public LocalDataSourceImpl(MoviesDao moviesDao) {
        this.moviesDao = moviesDao;
    }

    @Override
    public Observable<List<MovieListItem>> getFavouriteMovies() {
        return moviesDao.getFavourites(true)
            .toObservable()
            .map(movieEntities -> Utils.map(movieEntities, MovieHelper::getMovieListItem));
    }

    @Override
    public Observable<List<MovieListItem>> getMovieListFromLocal() {
        return moviesDao.getMoviesList().toObservable();
    }

    @Override
    public Observable<List<MovieListItem>> getMovieListBasedOnKeyword(String keyword) {
        return moviesDao.getMoviesListBasedOnKeyword(keyword).toObservable();
    }

    @Override
    public void insertAll(List<MovieListItem> movieListItems) {
        try {
            moviesDao.insertAll(movieListItems);
        } catch (Exception e) {
            Log.e("DB insert", e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        moviesDao.deleteAll();
    }

    @Override
    public void saveFavourite(MovieEntity movieEntity) {
        moviesDao.addFavouriteMovie(movieEntity);
    }

    @Override
    public void deleteFavourite(Long id) {
        moviesDao.deleteFavouriteMovie(id);
    }

    @Override
    public Single<Integer> isFavourite(Long id) {
        return moviesDao.isFavourite(id);
    }


}
