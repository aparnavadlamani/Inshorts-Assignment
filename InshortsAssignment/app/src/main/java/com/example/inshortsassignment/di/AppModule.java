package com.example.inshortsassignment.di;

import android.app.Application;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.example.inshortsassignment.datasource.remote.MoviesAPI;
import com.example.inshortsassignment.datasource.local.MoviesDao;
import com.example.inshortsassignment.datasource.local.MoviesDatabase;
import com.example.inshortsassignment.ui.viewmodel.MoviesListViewModelFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application getApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    MoviesAPI moviesAPI(Application application) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(MoviesAPI.class);
    }

    @Provides
    @Singleton
    MoviesDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, MoviesDatabase.class, "movies.db").build();
    }

    @Provides
    @Singleton
    MoviesDao provideMoviesDao(MoviesDatabase database) {
        return database.moviesDao();
    }

    @Provides
    ViewModelProvider.Factory providesMoviesListViewModelFactory(MoviesListViewModelFactory factory) {
        return factory;
    }
}
