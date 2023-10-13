package it.unibz.digidojo.activityplannerservice.domain.event.task;

import lombok.Getter;

import it.unibz.digidojo.sharedmodel.event.BaseEvent;

@Getter
public class TaskDeletedEvent extends BaseEvent {
    private static final String TASK_DELETED = "TASK_DELETED";

    private final Long id;

    public TaskDeletedEvent(Long id) {
        super(TASK_DELETED);
        this.id = id;
    }
}
