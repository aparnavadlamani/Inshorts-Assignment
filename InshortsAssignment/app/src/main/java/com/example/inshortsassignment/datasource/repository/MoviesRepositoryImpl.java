package com.example.inshortsassignment.datasource.repository;

import com.example.inshortsassignment.datasource.utils.MovieHelper;
import com.example.inshortsassignment.datasource.local.LocalDataSource;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import com.example.inshortsassignment.datasource.remote.MoviesService;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class MoviesRepositoryImpl implements MoviesRepository {

    private LocalDataSource localDataSource;
    private MoviesService moviesService;

    @Inject
    public MoviesRepositoryImpl(LocalDataSource localDataSource, MoviesService moviesService) {
        this.localDataSource = localDataSource;
        this.moviesService = moviesService;
    }

    @Override
    public Observable<List<MovieListItem>> getTrendingMovies(int page) {
        Observable<List<MovieListItem>> observableFromDb = localDataSource.getMovieListFromLocal();
        Observable<List<MovieListItem>> observableFromApi = Observable.defer(new Callable<Observable<List<MovieListItem>>>() {
            @Override public Observable<List<MovieListItem>> call() throws Exception {
                  return moviesService.getTrendingMovies(page).map(movieList -> movieList.results)
                        .flatMap(movieListItems -> {
                            localDataSource.deleteAll();
                            localDataSource.insertAll(movieListItems);
                            return localDataSource.getMovieListFromLocal();
                        });
            }
        });
        return Observable.concatArrayEager(observableFromDb.subscribeOn(Schedulers.io()), observableFromApi.subscribeOn(Schedulers.io()));
    }

    @Override
    public Observable<List<MovieListItem>> getNowPlayingMovies(int page) {
        Observable<List<MovieListItem>> observableFromDb = localDataSource.getMovieListFromLocal();
        Observable<List<MovieListItem>> observableFromApi = Observable.defer(new Callable<Observable<List<MovieListItem>>>() {
            @Override public Observable<List<MovieListItem>> call() throws Exception {
                return moviesService.getNowPlayingMovies(page).map(movieList -> movieList.results)
                    .flatMap(movieListItems -> {
                        localDataSource.deleteAll();
                        localDataSource.insertAll(movieListItems);
                        return null;
                    });
            }
        });
        return Observable.concatArrayEager(observableFromDb.subscribeOn(Schedulers.io()), observableFromApi.subscribeOn(Schedulers.io()));
    }

    @Override
    public Observable<List<MovieListItem>> getMoviesForSearch(String query, int page, Boolean includeAdult) {
        Observable<List<MovieListItem>> observableFromDb = localDataSource.getMovieListBasedOnKeyword(query);
        Observable<List<MovieListItem>> observableFromApi = Observable.defer(new Callable<Observable<List<MovieListItem>>>() {
            @Override public Observable<List<MovieListItem>> call() throws Exception {
                return moviesService.getMoviesForSearch(query, page, includeAdult).map(movieList -> movieList.results);
            }
        });
        return Observable.merge(observableFromDb.subscribeOn(Schedulers.io()), observableFromApi.subscribeOn(Schedulers.io()));
    }

    @Override
    public Observable<List<MovieListItem>> getFavouriteMovies() {
        return localDataSource.getFavouriteMovies();
    }

    @Override
    public Completable saveFavouriteMovies(MovieListItem movieListItem) {
        return Completable.fromAction(()-> {
            localDataSource.saveFavourite(MovieHelper.getMovieEntity(movieListItem));
        });
    }

    @Override
    public Single<Integer> isFavourite(Long id) {
        return localDataSource.isFavourite(id);
    }

    @Override
    public Completable deleteFavouriteMovie(Long id) {
        return Completable.fromAction(()-> {
            localDataSource.deleteFavourite(id);
        });
    }
}
