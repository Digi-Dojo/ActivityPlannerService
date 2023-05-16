package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.Place;
import it.unibz.taskcalendarservice.application.User;
import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/calendar-event", method = RequestMethod.GET )
public class CalendarEventController {
    private final CRUDCalendarEvent crudCalendarEvent;
    private final SearchCalendarEvent searchCalendarEvent;

    @Autowired
    public CalendarEventController(CRUDCalendarEvent crudCalendarEvent, SearchCalendarEvent searchCalendarEvent) {
        this.crudCalendarEvent = crudCalendarEvent;
        this.searchCalendarEvent = searchCalendarEvent;
    }

    @GetMapping("/{id}")
    public CalendarEvent findById(@PathVariable("id")Long id){
        return searchCalendarEvent.findById(id);
    }

    @PostMapping("/create")
    public CalendarEvent createTask (String description, java.time.LocalDateTime startDate, LocalDateTime endDate,
                                     Optional<Place> place, Optional<User> user, Optional<List<String>> tags){
        return crudCalendarEvent.createCalendarEvent(description, startDate, endDate, place, user, tags);
    }

    @PostMapping("/update/{id}")
    public CalendarEvent updateCalendarEvent(Long calendarEventID, Optional<String> description, Optional<LocalDateTime> startDate,
                                             Optional<LocalDateTime> endDate, Optional<Place> place, Optional<User> user, Optional<List<String>> tags){
        return crudCalendarEvent.updateCalendarEvent(calendarEventID, description, startDate, endDate, place, user, tags);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCalendarEvent(Long calendarEventID){
        return crudCalendarEvent.deleteCalendarEvent(calendarEventID);
    }

    @GetMapping("/getAll")
    public List<CalendarEvent> getAll(){
        return searchCalendarEvent.getAll();
    }
}