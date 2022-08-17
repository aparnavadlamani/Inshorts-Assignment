package com.example.inshortsassignment.datasource.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieList {

    @SerializedName("page")
    @Expose
    public int page;

    @SerializedName("total_pages")
    @Expose
    public int totalPages;

    @SerializedName("total_results")
    @Expose
    public int totalResults;

    @SerializedName("results")
    @Expose
    public List<MovieListItem> results;
}
