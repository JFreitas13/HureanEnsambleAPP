package com.svalero.hureanensamble.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Implementamos Serializable para poder pasar los objetos entre activities
 */
public class Event implements Serializable {
    private long id;
    private String eventDate;
    private String place;
    private boolean paid;
    private Playlist eventPlaylist;
    private User eventUser;

    public Event(long id, String eventDate, String place, boolean paid) {
        this.id = id;
        this.eventDate = eventDate;
        this.place = place;
        this.paid = paid;
    }

    public Event(String place, String eventDate, User eventUser, Playlist eventPlaylist) {
        this.eventDate = eventDate;
        this.place = place;
        this.eventUser = eventUser;
        this.eventPlaylist = eventPlaylist;
    }

    public Event() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventPlaylist(Playlist eventPlaylist) {
        this.eventPlaylist = eventPlaylist;
    }

    public void setEventUser(User eventUser) {
        this.eventUser = eventUser;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Playlist getEventPlaylist() {
        return eventPlaylist;
    }

    public User getEventUser() {
        return eventUser;
    }

}
