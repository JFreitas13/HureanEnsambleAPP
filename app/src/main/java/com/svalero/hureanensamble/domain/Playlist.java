package com.svalero.hureanensamble.domain;

import java.io.Serializable;
import java.util.List;

public class Playlist implements Serializable {

    private long id;
    private String name;
    private String userId;
    private List<Song> songs;

    public Playlist(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Playlist(long id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public Playlist(long id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
