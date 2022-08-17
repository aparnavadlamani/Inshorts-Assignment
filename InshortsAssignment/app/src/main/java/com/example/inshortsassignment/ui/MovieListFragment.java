package com.example.inshortsassignment.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import com.example.inshortsassignment.ui.viewmodel.MovieListViewModel;
import com.example.inshortsassignment.ui.viewmodel.MoviesListViewModelFactory;
import com.example.inshortsassignment.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import dagger.android.support.AndroidSupportInjection;
import java.util.List;
import javax.inject.Inject;

public class MovieListFragment extends Fragment implements MovieItemClickCallBack{

    @Inject
    public MoviesListViewModelFactory factory;
    private MovieListViewModel viewModel;
    private View view;

    //TODO: Fix the concatArrayEager
    //TODO: Fix the RecyclerViewAadpter part
    //TODO: Fix the Posters to fit the layout

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_list, container, false);
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
        viewModel.movieListData.observe(getViewLifecycleOwner(), this::updateMovieList);
        viewModel.getTrendingMoviesList();
        TabLayout tlCatgeories = view.findViewById(R.id.categories);
        tlCatgeories.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                if ("Trending".equals(tab.getText())) {
                    viewModel.getTrendingMoviesList();
                } else if ("Now Playing".equals(tab.getText())) {
                    viewModel.getNowPlayingMoviesList();
                } else if ("Favourites".equals(tab.getText())) {
                    viewModel.getFavourites();
                }
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
    }

    private void updateMovieList(List<MovieListItem> movieListItems) {
        RecyclerView rvMovieList = view.findViewById(R.id.movieList);
        rvMovieList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        MovieListAdapter adapter = new MovieListAdapter(this, movieListItems);
        rvMovieList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void movieItemClicked(MovieListItem movieListItem) {
        MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.newInstance(movieListItem);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContainer, movieDetailsFragment, null).addToBackStack(MovieDetailsFragment.class.getName());
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                SearchFragment searchFragment = SearchFragment.newInstance();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flContainer, searchFragment, null).addToBackStack(SearchFragment.class.getName());
                transaction.commitAllowingStateLoss();
        }
        return super.onOptionsItemSelected(item);
    }
}

interface MovieItemClickCallBack {
    void movieItemClicked(MovieListItem movieListItem);
}