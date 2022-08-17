package com.example.inshortsassignment.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.example.inshortsassignment.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieListFragment movieListFragment = MovieListFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContainer, movieListFragment, null).addToBackStack(MovieListFragment.class.getName());
        transaction.commitAllowingStateLoss();
    }
}