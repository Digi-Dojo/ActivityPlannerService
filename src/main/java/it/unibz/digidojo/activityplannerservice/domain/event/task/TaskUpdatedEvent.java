package it.unibz.digidojo.activityplannerservice.domain.event.task;

import lombok.Getter;

import it.unibz.digidojo.sharedmodel.event.BaseEvent;
import it.unibz.digidojo.activityplannerservice.domain.model.task.Task;

@Getter
public class TaskUpdatedEvent extends BaseEvent {
    private static final String TASK_UPDATED = "TASK_UPDATED";

    private final Task payload;

    public TaskUpdatedEvent(Task payload) {
        super(TASK_UPDATED);
        this.payload = payload;
    }
}
