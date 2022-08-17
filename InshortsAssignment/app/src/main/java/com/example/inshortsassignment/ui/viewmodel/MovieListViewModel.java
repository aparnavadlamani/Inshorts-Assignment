package com.example.inshortsassignment.ui.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.inshortsassignment.datasource.repository.MoviesRepository;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class MovieListViewModel extends ViewModel {

    private MoviesRepository moviesRepository;
    private CompositeDisposable compositeDisposable;
    private final MutableLiveData<List<MovieListItem>> _movieListData = new MutableLiveData<>();
    public final LiveData<List<MovieListItem>> movieListData = _movieListData;
    private final MutableLiveData<Boolean> _isMovieFavourite = new MutableLiveData<>();
    public final LiveData<Boolean> isMovieFavourite = _isMovieFavourite;

    @Inject
    public MovieListViewModel(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
        compositeDisposable = new CompositeDisposable();
    }

    public void getTrendingMoviesList() {
        compositeDisposable.add(moviesRepository.getTrendingMovies(1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMovieListSuccess, this::onError));
    }

    private void onError(Throwable throwable) {
        Log.e("MovieViewModel", throwable.getMessage());
    }

    public void getNowPlayingMoviesList() {
        compositeDisposable.add(moviesRepository.getNowPlayingMovies(1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMovieListSuccess, this::onError));
    }

    public void searchMovies(String keyword) {
        compositeDisposable.add(moviesRepository.getMoviesForSearch(keyword, 1, false).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMovieListSuccess, this::onError));
    }

    public void getFavourites() {
        compositeDisposable.add(moviesRepository.getFavouriteMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMovieListSuccess, this::onError));
    }

    public void saveFavouriteToDatabase(MovieListItem movieListItem) {
        Action onComplete = new Action() {
            @Override
            public void run() throws Exception {
                Log.d(MovieListViewModel.class.getName(), "saveFavourite onComplete");
            }
        };
        compositeDisposable.add(
            moviesRepository.saveFavouriteMovies(movieListItem).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete, this::onError));
    }

    public void deleteFavouriteFromDatabase(Long id) {
        Action onComplete = new Action() {
            @Override
            public void run() throws Exception {
                Log.d(MovieListViewModel.class.getName(), "deleteFavourite onComplete");
            }
        };
        compositeDisposable.add(
            moviesRepository.deleteFavouriteMovie(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete, this::onError));
    }

    public void isFavourite(Long id) {
        compositeDisposable.add(moviesRepository.isFavourite(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::movieFavourite, this::onError));
    }

    private void movieFavourite(Integer isFavourite) {
        _isMovieFavourite.postValue(isFavourite == 1);
    }

    private void onMovieListSuccess(List<MovieListItem> movieList) {
        _movieListData.postValue(movieList);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable!=null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}