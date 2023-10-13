package it.unibz.digidojo.activityplannerservice.domain.event.calendar;
import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;
import it.unibz.digidojo.sharedmodel.event.BaseEvent;

import lombok.Getter;

@Getter
public class CalendarEventCreatedEvent extends BaseEvent {
    private static final String CALENDAR_EVENT_CREATED = "CALENDAR_EVENT_CREATED";

    private final CalendarEvent payload;

    public CalendarEventCreatedEvent(CalendarEvent payload){
        super(CALENDAR_EVENT_CREATED);
        this.payload = payload;
    }
}