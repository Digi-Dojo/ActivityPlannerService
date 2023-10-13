package it.unibz.digidojo.activityplannerservice.domain.broadcaster;

import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;

public interface CalendarEventBroadcaster {
    void emitCalendarEventCreated(CalendarEvent calendar);
    void emitCalendarEventUpdated(CalendarEvent calendar);
}
