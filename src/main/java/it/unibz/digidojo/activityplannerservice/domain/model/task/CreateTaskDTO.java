package it.unibz.digidojo.activityplannerservice.domain.model.task;

import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CreateTaskDTO {
    private String description, title;
    private Status status;
    private User user;
    private Place place;
    private String tags; // probabilmente array è meglio per indicizzare

    public CreateTaskDTO(){}

    public CreateTaskDTO(String title, String description, Optional<User> user, Optional<Place> place, Status status, Optional<String> tags) {
        this.description = description;
        this.title = title;
        this.status = status;
        this.place = place.orElse(null);
        this.user = user.orElse(null);
        this.tags = tags.orElse(null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<String> getTags() {
        if(tags.isEmpty()) return null;
        return Arrays.asList(tags.split(","));
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
