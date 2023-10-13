package it.unibz.digidojo.activityplannerservice.domain.event.calendar;

import it.unibz.digidojo.sharedmodel.event.BaseEvent;
import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;
import lombok.Getter;

@Getter
public class CalendarEventUpdatedEvent extends BaseEvent {
    private static final String CALENDAR_EVENT_UPDATED = "CALENDAR_EVENT_UPDATED";

    private final CalendarEvent payload;

    public CalendarEventUpdatedEvent(CalendarEvent payload){
        super(CALENDAR_EVENT_UPDATED);
        this.payload = payload;
    }
}
