package it.unibz.digidojo.activityplannerservice.domain.broadcaster;

import it.unibz.digidojo.activityplannerservice.domain.model.task.Task;

public interface TaskBroadcaster {
    void emitTaskCreated(Task task);
    void emitTaskUpdated(Task task);
}
