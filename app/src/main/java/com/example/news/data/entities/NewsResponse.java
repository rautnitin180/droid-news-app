package com.example.news.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("num_results")
    public int numResults;
    @SerializedName("status")
    public String status;
    @SerializedName("copyright")
    public String copyright;

    @SerializedName("results")
    public List<NewsEntity> articleEntities;

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<NewsEntity> getArticles() {
        return articleEntities;
    }

    public void setArticles(List<NewsEntity> articleEntities) {
        this.articleEntities = articleEntities;
    }
}
