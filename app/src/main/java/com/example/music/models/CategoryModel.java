package com.example.music.models;
import java.util.List;
import java.util.ArrayList;

public class CategoryModel {
    private String name;
    private String coverUrl;
    private List<String> songs;

    public CategoryModel() {
        this.name = "";
        this.coverUrl = "";
        this.songs = new ArrayList<>();
    }

    public CategoryModel(String name, String coverUrl, List<String> songs, List<String> phapthoai, List<String> podcast) {
        this.name = name;
        this.coverUrl = coverUrl;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

}
