package com.example.inshortsassignment.datasource.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import com.example.inshortsassignment.datasource.model.MovieEntity;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

@Dao
public
interface MoviesDao {

    @Query("select * from movies")
    Flowable<List<MovieListItem>> getMoviesList();

    @Query("select * from movies where title LIKE '%' || :keyword || '%'")
    Flowable<List<MovieListItem>> getMoviesListBasedOnKeyword(String keyword);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MovieListItem> movie);

    @Query("delete from movies")
    void deleteAll ();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavouriteMovie(MovieEntity movie);

    @Query("delete from favourite_movies where id = :id")
    int deleteFavouriteMovie(Long id);

    @Query("select exists (select 1 from favourite_movies where id = :id)")
    Single<Integer> isFavourite(Long id);

    @Query("select * from favourite_movies where favourite = :isFavourite")
    Flowable<List<MovieEntity>> getFavourites(boolean isFavourite);
}
