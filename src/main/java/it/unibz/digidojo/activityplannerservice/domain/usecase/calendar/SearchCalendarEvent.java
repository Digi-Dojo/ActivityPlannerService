package it.unibz.digidojo.activityplannerservice.domain.usecase.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;
import it.unibz.digidojo.activityplannerservice.domain.repository.CalendarEventRepository;

@Service
public class SearchCalendarEvent {
    CalendarEventRepository calendarEventRepository;

    @Autowired
    public SearchCalendarEvent(CalendarEventRepository calendarEventRepository){
        this.calendarEventRepository = calendarEventRepository;
    }

    public CalendarEvent findById(Long id){
        Optional<CalendarEvent> calendarEvent = calendarEventRepository.findById(id);
        if (calendarEvent.isEmpty()) throw new IllegalArgumentException("Calendar event with id '" + id + "' does not exist");
        return calendarEvent.get();
    }

    public List<CalendarEvent> getAll(){
        List<CalendarEvent> calendarEvents = calendarEventRepository.findAll();
        System.out.println("\nCalendarEventsList size: " + calendarEvents.size());
        return calendarEvents;
    }
}