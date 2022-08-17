package com.example.inshortsassignment.datasource.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import com.example.inshortsassignment.datasource.model.MovieEntity;

@Database(entities = {MovieListItem.class, MovieEntity.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    public abstract MoviesDao moviesDao();

}
