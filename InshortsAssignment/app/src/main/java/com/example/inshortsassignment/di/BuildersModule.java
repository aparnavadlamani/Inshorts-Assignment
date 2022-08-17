package com.example.inshortsassignment.di;

import com.example.inshortsassignment.datasource.local.LocalDataSource;
import com.example.inshortsassignment.datasource.local.LocalDataSourceImpl;
import com.example.inshortsassignment.ui.MovieDetailsFragment;
import com.example.inshortsassignment.ui.MovieListFragment;
import com.example.inshortsassignment.datasource.repository.MoviesRepository;
import com.example.inshortsassignment.datasource.repository.MoviesRepositoryImpl;
import com.example.inshortsassignment.datasource.remote.MoviesService;
import com.example.inshortsassignment.datasource.remote.MoviesServiceImpl;
import com.example.inshortsassignment.ui.SearchFragment;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract MovieListFragment contributeMovieListFragment();
    @ContributesAndroidInjector
    abstract MovieDetailsFragment contributeMovieDetailsFragment();
    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @Binds
    public abstract MoviesRepository bindMovieRepository(MoviesRepositoryImpl moviesRepositoryImpl);

    @Binds
    public abstract LocalDataSource bindLocalDataSource(LocalDataSourceImpl localDataSourceImpl);

    @Binds
    public abstract MoviesService bindMoviesService(MoviesServiceImpl moviesServiceImpl);

}
