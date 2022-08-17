package com.example.inshortsassignment.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import com.example.inshortsassignment.ui.viewmodel.MovieListViewModel;
import com.example.inshortsassignment.ui.viewmodel.MoviesListViewModelFactory;
import com.example.inshortsassignment.R;
import dagger.android.support.AndroidSupportInjection;
import java.util.List;
import javax.inject.Inject;

public class SearchFragment extends Fragment implements MovieItemClickCallBack {

    @Inject
    public MoviesListViewModelFactory factory;
    private MovieListViewModel viewModel;

    View view;

    //TODO: Fix the RecyclerViewAadpter part

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
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

        RecyclerView rvSearchList = view.findViewById(R.id.rvSearchList);
        SearchView searchView = view.findViewById(R.id.searchText);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.searchMovies(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void updateMovieList(List<MovieListItem> movieListItems) {
        RecyclerView rvMovieList = view.findViewById(R.id.rvSearchList);
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
}