package it.unibz.digidojo.activityplannerservice.application.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;
import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CreateCalendarEventDTO;
import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;
import it.unibz.digidojo.activityplannerservice.domain.usecase.calendar.CRUDCalendarEvent;
import it.unibz.digidojo.activityplannerservice.domain.usecase.calendar.SearchCalendarEvent;

@CrossOrigin
@RestController
@RequestMapping(path = "api/calendar-events")
public class CalendarEventController {
    private final CRUDCalendarEvent crudCalendarEvent;
    private final SearchCalendarEvent searchCalendarEvent;

    @Autowired
    public CalendarEventController(CRUDCalendarEvent crudCalendarEvent, SearchCalendarEvent searchCalendarEvent) {
        this.crudCalendarEvent = crudCalendarEvent;
        this.searchCalendarEvent = searchCalendarEvent;
    }

    @GetMapping("/{id}")
    public CalendarEvent findById(@PathVariable("id") Long id) {
        return searchCalendarEvent.findById(id);
    }

    @PostMapping("/create")
    public CalendarEvent createCalendarEvent(@RequestBody CreateCalendarEventDTO calendarEventDTO) {
        return crudCalendarEvent.createCalendarEvent(calendarEventDTO.getTitle(), calendarEventDTO.getDescription(),
                calendarEventDTO.getStartDate(), calendarEventDTO.getEndDate(),
                Optional.ofNullable(calendarEventDTO.getPlace()), Optional.ofNullable(calendarEventDTO.getUser()),
                Optional.ofNullable(calendarEventDTO.getTags()));
    }

    @PostMapping("/update/{id}")
    public CalendarEvent updateCalendarEvent(Long calendarEventID, String title, Optional<String> description,
                                             Optional<LocalDateTime> startDate,
                                             Optional<LocalDateTime> endDate, Optional<Place> place, Optional<User> user,
                                             Optional<String> tags) {
        return crudCalendarEvent.updateCalendarEvent(calendarEventID, title, description, startDate, endDate, place, user, tags);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCalendarEvent(Long calendarEventID) {
        return crudCalendarEvent.deleteCalendarEvent(calendarEventID);
    }

    @GetMapping("/getAll")
    public List<CalendarEvent> getAll() {
        System.out.println("GETTING ALL CALENDAR EVENTS");
        return searchCalendarEvent.getAll();
    }
}