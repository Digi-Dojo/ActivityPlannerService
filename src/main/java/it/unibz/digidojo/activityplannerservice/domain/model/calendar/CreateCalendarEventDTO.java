package it.unibz.digidojo.activityplannerservice.domain.model.calendar;

import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;

import java.time.LocalDateTime;
import java.util.Optional;

public class CreateCalendarEventDTO {
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String title;
    private User user;
    private Place place;
    private String tags;

    public CreateCalendarEventDTO(){}

    public CreateCalendarEventDTO(String title, String description, LocalDateTime startDate, LocalDateTime endDate, Optional<String> user, Optional<String> place, Optional<String> tags){
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place.map(Place::new).orElse(null);
        this.user = user.map(User::new).orElse(null);
        this.tags = tags.orElse(null);
    }

    private String[] createTagsList(String tags) {
        System.out.println("Tags: " + tags);
        String[] tagsArray = tags.split(",");
        System.out.println("Tag1: " + tagsArray[0] + "\nTag2: " + tagsArray[1]);
        return tags.split(",");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String[] getTags() {
        String[] tagsArray = new String[1];
        if (tags == null || tags.trim().equals(""))
            return null;
        tagsArray[0] = tags;
        return tagsArray;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
