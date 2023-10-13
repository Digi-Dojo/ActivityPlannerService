package it.unibz.digidojo.activityplannerservice.application.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import it.unibz.digidojo.activityplannerservice.domain.broadcaster.TaskBroadcaster;
import it.unibz.digidojo.activityplannerservice.domain.event.task.TaskUpdatedEvent;
import it.unibz.digidojo.activityplannerservice.util.CRUD;
import it.unibz.digidojo.activityplannerservice.domain.event.task.TaskCreatedEvent;
import it.unibz.digidojo.activityplannerservice.domain.model.task.Task;

@Component
public class TaskProducer extends BaseProducer implements TaskBroadcaster {
    @Autowired
    public TaskProducer(final KafkaTemplate<String, String> sender) {
        super(sender);
    }

    @Override
    public void emitTaskCreated(Task task) {
        TaskCreatedEvent taskCreated = new TaskCreatedEvent(task);
        this.sendEvent(CRUD.CREATE, taskCreated);
    }

    @Override
    public void emitTaskUpdated(Task task) {
        TaskUpdatedEvent calendarEventUpdateEvent = new TaskUpdatedEvent(task);
        this.sendEvent(CRUD.UPDATE, calendarEventUpdateEvent);
    }
}