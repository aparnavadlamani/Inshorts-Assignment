package com.example.inshortsassignment.ui;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import com.example.inshortsassignment.ui.viewmodel.MovieListViewModel;
import com.example.inshortsassignment.ui.viewmodel.MoviesListViewModelFactory;
import com.example.inshortsassignment.R;
import dagger.android.support.AndroidSupportInjection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.inject.Inject;

public class MovieDetailsFragment extends Fragment{

    private static final String MOVIE_ITEM = "movie_item";

    private MovieListItem movieListItem;
    @Inject
    public MoviesListViewModelFactory factory;
    private MovieListViewModel viewModel;
    private View view;

    //TODO: Fix UI

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsFragment newInstance(MovieListItem movieListItem) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(MOVIE_ITEM, movieListItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieListItem = getArguments().getParcelable(MOVIE_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup();
    }

    private void setup() {
        AndroidSupportInjection.inject(this);
        viewModel = new ViewModelProvider(this, factory).get(MovieListViewModel.class);
        viewModel.isMovieFavourite.observe(getActivity(), this::isThisMovieFavourite);
        viewModel.isFavourite(movieListItem.id);
        ImageView backdropImage = view.findViewById(R.id.backdropImage);
        TextView movieTitle = view.findViewById(R.id.movieTitle);
        TextView overviewDesc = view.findViewById(R.id.overviewDesc);
        TextView languageDesc = view.findViewById(R.id.languageDesc);
        TextView releaseDateDesc = view.findViewById(R.id.releaseDateDesc);
        String backdropPath = "https://image.tmdb.org/t/p/" + "original" + movieListItem.backdropPath;

        Glide.with(this).load(backdropPath).into(backdropImage);
        movieTitle.setText(movieListItem.title);
        overviewDesc.setText(movieListItem.overview);

        String language = new Locale(movieListItem.originalLanguage).getDisplayLanguage();
        languageDesc.setText(getString(R.string.language, language));
        SimpleDateFormat inputDateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat outputDateTimeFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String date = null;
        try {
            date = outputDateTimeFormatter.format(inputDateTimeFormatter.parse(movieListItem.releaseDate)).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        releaseDateDesc.setText(getString(R.string.release_date, date));
        CheckBox favouriteIcon = view.findViewById(R.id.favouriteIcon);

        favouriteIcon.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    viewModel.saveFavouriteToDatabase(movieListItem);
                } else {
                    viewModel.deleteFavouriteFromDatabase(movieListItem.id);
                }
            }
        });
    }

    private void isThisMovieFavourite(Boolean aBoolean) {
        CheckBox favouriteIcon = view.findViewById(R.id.favouriteIcon);
        favouriteIcon.setChecked(aBoolean);
    }
}