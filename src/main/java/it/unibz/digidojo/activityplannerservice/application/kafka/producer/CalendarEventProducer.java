package it.unibz.digidojo.activityplannerservice.application.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import it.unibz.digidojo.activityplannerservice.domain.broadcaster.CalendarEventBroadcaster;
import it.unibz.digidojo.activityplannerservice.domain.event.calendar.CalendarEventUpdatedEvent;
import it.unibz.digidojo.activityplannerservice.domain.model.calendar.CalendarEvent;
import it.unibz.digidojo.activityplannerservice.util.CRUD;
import it.unibz.digidojo.activityplannerservice.domain.event.calendar.CalendarEventCreatedEvent;

@Component
public class CalendarEventProducer extends BaseProducer implements CalendarEventBroadcaster {
    @Autowired
    public CalendarEventProducer(final KafkaTemplate<String, String> sender) {
        super(sender);
    }

    @Override
    public void emitCalendarEventCreated(CalendarEvent calendarEvent) {
        CalendarEventCreatedEvent calendarEventCreatedEvent = new CalendarEventCreatedEvent(calendarEvent);
        this.sendEvent(CRUD.CREATE, calendarEventCreatedEvent);
    }

    @Override
    public void emitCalendarEventUpdated(CalendarEvent calendarEvent) {
        CalendarEventUpdatedEvent calendarEventUpdateEvent = new CalendarEventUpdatedEvent(calendarEvent);
        this.sendEvent(CRUD.UPDATE, calendarEventUpdateEvent);
    }
}