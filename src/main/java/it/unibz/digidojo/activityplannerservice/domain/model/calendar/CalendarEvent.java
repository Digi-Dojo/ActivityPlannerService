package it.unibz.digidojo.activityplannerservice.domain.model.calendar;

import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "calendar_events")
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String title;
    @Transient
    private User user;
    @Transient
    private Place place;
    @Transient
    private String[] tags;// same as on the task (array is better)

    public CalendarEvent() {
    }

    //Constructors
    @Autowired
    public CalendarEvent(Long id , String title, String description, LocalDateTime startDate, LocalDateTime endDate, Optional<Place> place, Optional<User> user, Optional<String[]> tags) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place.orElse(null);
        this.user = user.orElse(null);
        this.tags = tags.orElse(null);
        this.title = title;
        assert place.isEmpty() || user.isEmpty();
    }

    @Autowired
    public CalendarEvent(String description, LocalDateTime startDate, LocalDateTime endDate, Optional<Place> place, Optional<User> user, Optional<String[]> tags, String title) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place.orElse(null);
        this.user = user.orElse(null);
        this.tags = tags.orElse(null);
        this.title = title;
        assert place.isEmpty() || user.isEmpty();
    }


    //Getters and Setters
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags.split(",");
    }

    //Methods
    public void addTag(String tag) {
        if (tags == null) {
            tags = new String[1];
            tags[0] = tag;
        }
        else{
            String[] newTags = new String[tags.length + 1];
            System.arraycopy(tags, 0, newTags, 0, tags.length);
            newTags[newTags.length - 1] = tag;
            tags = newTags;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}