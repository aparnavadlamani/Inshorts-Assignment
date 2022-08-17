package com.example.inshortsassignment.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.inshortsassignment.datasource.model.MovieListItem;
import com.example.inshortsassignment.R;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private MovieItemClickCallBack callBack;
    private List<MovieListItem> movieTitleList;

    public MovieListAdapter(MovieItemClickCallBack callBack, List<MovieListItem> movieTitleList) {
        this.callBack = callBack;
        this.movieTitleList = movieTitleList;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        String path = "https://image.tmdb.org/t/p/" + "original" + movieTitleList.get(position).posterPath;
        Glide.with(holder.itemView).load(path).into(holder.poster);
        holder.poster.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.posterImage:
                        callBack.movieItemClicked(movieTitleList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieTitleList.size();
    }

    public void updateList(List<MovieListItem> list) {
        movieTitleList = list;
    }
}

class MovieListViewHolder extends RecyclerView.ViewHolder {

    ImageView poster;
    public MovieListViewHolder(@NonNull View itemView) {
        super(itemView);
        poster = itemView.findViewById(R.id.posterImage);
    }
}