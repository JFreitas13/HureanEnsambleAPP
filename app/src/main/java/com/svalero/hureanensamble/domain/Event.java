package com.svalero.hureanensamble.domain;

import java.time.LocalDate;

public class Event {
    private long id;
    private LocalDate eventDate;
    private String place;
    private boolean paid;

    public Event(long id, LocalDate eventDate, String place, boolean paid) {
        this.id = id;
        this.eventDate = eventDate;
        this.place = place;
        this.paid = paid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
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

}
