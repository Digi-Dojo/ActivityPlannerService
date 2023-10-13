package it.unibz.digidojo.activityplannerservice.domain.event.task;

import lombok.Getter;

import it.unibz.digidojo.sharedmodel.event.BaseEvent;
import it.unibz.digidojo.activityplannerservice.domain.model.task.Task;

@Getter
public class TaskCreatedEvent extends BaseEvent {
    private static final String TASK_CREATED = "TASK_CREATED";

    private final Task payload;

    public TaskCreatedEvent(Task payload){
        super(TASK_CREATED);
        this.payload = payload;
    }
}
