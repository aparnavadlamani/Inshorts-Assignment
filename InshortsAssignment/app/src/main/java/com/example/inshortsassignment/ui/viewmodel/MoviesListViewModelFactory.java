package com.example.inshortsassignment.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.example.inshortsassignment.ui.viewmodel.MovieListViewModel;
import javax.inject.Inject;

public class MoviesListViewModelFactory implements Factory {

    private MovieListViewModel movieListViewModel;

    @Inject
    public MoviesListViewModelFactory(MovieListViewModel movieListViewModel) {
        this.movieListViewModel = movieListViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MovieListViewModel.class)) {
            return (T) movieListViewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}