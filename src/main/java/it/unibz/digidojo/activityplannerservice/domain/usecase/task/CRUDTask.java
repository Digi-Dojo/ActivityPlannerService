package it.unibz.digidojo.activityplannerservice.domain.usecase.task;

import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.activityplannerservice.domain.model.task.Status;
import it.unibz.digidojo.activityplannerservice.domain.repository.TaskRepository;
import it.unibz.digidojo.activityplannerservice.domain.model.task.Task;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CRUDTask {
    private final TaskRepository taskRepository;
    private final SearchTask searchTask;

    @Autowired
    public CRUDTask(TaskRepository taskRepository, SearchTask searchTask) {
        this.taskRepository = taskRepository;
        this.searchTask = searchTask;
    }

    public Task createTask(String description, Optional<Place> place, Status status, Optional<List<String>> tags, String title, Optional<User> user){
        return taskRepository.save(new Task(description, place, status, tags, title, user));
    }

    public Task updateTask(Long taskID, Optional<String> description, Optional<Status> status, Optional<User> user, Optional<Place> place, Optional<List<String>> tags){
        Task toBeModified = searchTask.findById(taskID);
        description.ifPresent(toBeModified::setDescription);
        status.ifPresent(toBeModified::setStatus);
        user.ifPresent(toBeModified::setUser);
        place.ifPresent(toBeModified::setPlace);
        tags.ifPresent(toBeModified::setTags);
        assert user.isEmpty() || place.isEmpty();
        return taskRepository.save(toBeModified);
    }

    public boolean deleteTask(Long id){
        Optional<Task> toBeRemoved = taskRepository.findById(id);
        if(toBeRemoved.isEmpty()) throw new IllegalArgumentException("Task with id '" + id + "' does not exist");
        taskRepository.delete(toBeRemoved.get());
        try{
            taskRepository.findById(id);
        }catch (Exception e){
            System.out.println("Task successfully deleted");
            return true;
        }
        System.out.println("Process unsuccessful, Task has not been deleted");
        return false;
    }

}