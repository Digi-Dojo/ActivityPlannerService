package it.unibz.digidojo.activityplannerservice.domain.usecase.calendar;

import it.unibz.digidojo.activityplannerservice.domain.repository.CalendarEventRepository;
import it.unibz.digidojo.activityplannerservice.application.kafka.producer.CalendarEventProducer;
import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;
import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CRUDCalendarEvent {
    private final CalendarEventRepository calendarEventRepository;
    private final SearchCalendarEvent searchCalendarEvent;

    @Autowired
    private CalendarEventProducer broadcaster;

    @Autowired
    public CRUDCalendarEvent(CalendarEventRepository calendarEventRepository, SearchCalendarEvent searchCalendarEvent) {
        this.calendarEventRepository = calendarEventRepository;
        this.searchCalendarEvent = searchCalendarEvent;
    }

    public CalendarEvent createCalendarEvent(String title, String description, LocalDateTime startDate, LocalDateTime endDate, Optional<Place> place,
                                             Optional<User> user, Optional<String[]> tags){
        CalendarEvent calendarReturned = new CalendarEvent(description, startDate, endDate, place, user, tags,title);
       broadcaster.emitCalendarEventCreated(calendarEventRepository.save(calendarReturned));
        return calendarEventRepository.save(calendarReturned);
    }

    public CalendarEvent updateCalendarEvent(Long calendarEventID, String title, Optional<String> description, Optional<LocalDateTime> startDate,
                                             Optional<LocalDateTime> endDate, Optional<Place> place, Optional<User> user, Optional<String> tags){
        CalendarEvent toBeModified = searchCalendarEvent.findById(calendarEventID);
        description.ifPresent(toBeModified::setDescription);
        startDate.ifPresent(toBeModified::setStartDate);
        endDate.ifPresent(toBeModified::setEndDate);
        user.ifPresent(toBeModified::setUser);
        place.ifPresent(toBeModified::setPlace);
        tags.ifPresent(toBeModified::setTags);
        broadcaster.emitCalendarEventUpdated(toBeModified);
        assert user.isEmpty() || place.isEmpty();
        return calendarEventRepository.save(toBeModified);
    }

    public boolean deleteCalendarEvent(Long id){
        Optional<CalendarEvent> toBeRemoved = calendarEventRepository.findById(id);
        if(toBeRemoved.isEmpty()) throw new IllegalArgumentException("Calendar event with id '" + id + "' does not exist");
        calendarEventRepository.delete(toBeRemoved.get());
        try{
            calendarEventRepository.findById(id);
        }catch (Exception e){
            System.out.println("Calendar event successfully deleted");
            return true;
        }
        System.out.println("Process unsuccessful, Calendar event has not been deleted");
        return false;
    }

}