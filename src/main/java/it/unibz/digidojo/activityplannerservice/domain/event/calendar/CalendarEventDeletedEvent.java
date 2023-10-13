package it.unibz.digidojo.activityplannerservice.domain.event.calendar;
import lombok.Getter;

import it.unibz.digidojo.sharedmodel.event.BaseEvent;

@Getter
public class CalendarEventDeletedEvent extends BaseEvent {
    private static final String CALENDAR_EVENT_DELETED = "CALENDAR_EVENT_DELETED";

    private final Long id;

    public CalendarEventDeletedEvent(final Long id) {
        super(CALENDAR_EVENT_DELETED);
        this.id = id;
    }
}