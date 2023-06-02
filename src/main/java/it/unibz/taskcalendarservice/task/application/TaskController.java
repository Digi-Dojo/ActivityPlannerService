package it.unibz.taskcalendarservice.task.application;

import it.unibz.taskcalendarservice.common.domain.Place;
import it.unibz.taskcalendarservice.common.domain.User;
import it.unibz.taskcalendarservice.task.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/tasks")
public class TaskController {

    private final CRUDTask crudTask;
    private final SearchTask searchTask;

    @Autowired
    public TaskController(CRUDTask crudTask, SearchTask searchTask) {
        this.crudTask = crudTask;
        this.searchTask = searchTask;
    }

    /*
    Test: curl http://localhost:8080/api/tasks/sayhello
    run in terminal
     */
    @GetMapping(value = "/sayhello")
    public String sayHelloWorld(){
        return "\nHello World!";
    }

    @PostMapping("/{id}")
    public Task findById(@PathVariable("id")Long id){
        return searchTask.findById(id);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody CreateTaskDTO createTaskDTO){
        return crudTask.createTask( createTaskDTO.getDescription(), Optional.ofNullable(createTaskDTO.getPlace()), createTaskDTO.getStatus(),
                Optional.ofNullable(createTaskDTO.getTags()), createTaskDTO.getTitle(), Optional.ofNullable(createTaskDTO.getUser()));
    }

    @PostMapping("/update/{id}")
    public Task updateTask(Long taskID, Optional<String> description, Optional<Status> status, Optional<User> user, Optional<Place> place, Optional<List<String>> tags){
        return crudTask.updateTask(taskID, description, status, user, place, tags);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(Long taskID){
        return crudTask.deleteTask(taskID);
    }

    @GetMapping("/getAll")
    public List<Task> getAll(){
        return searchTask.getAll();
    }
}
