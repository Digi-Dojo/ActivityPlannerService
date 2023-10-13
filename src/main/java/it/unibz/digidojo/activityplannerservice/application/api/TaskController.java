package it.unibz.digidojo.activityplannerservice.application.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unibz.digidojo.activityplannerservice.domain.model.place.Place;
import it.unibz.digidojo.activityplannerservice.domain.model.task.CreateTaskDTO;
import it.unibz.digidojo.activityplannerservice.domain.model.task.Status;
import it.unibz.digidojo.activityplannerservice.domain.model.task.Task;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;
import it.unibz.digidojo.activityplannerservice.domain.usecase.task.CRUDTask;
import it.unibz.digidojo.activityplannerservice.domain.usecase.task.SearchTask;

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
    public String sayHelloWorld() {
        return "\nHello World!";
    }

    @PostMapping("/{id}")
    public Task findById(@PathVariable("id") Long id) {
        return searchTask.findById(id);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody CreateTaskDTO createTaskDTO) {
        System.out.println("\nCreating task\n");
        return crudTask.createTask(
                createTaskDTO.getDescription(),
                Optional.ofNullable(createTaskDTO.getPlace()), createTaskDTO.getStatus(),
                Optional.ofNullable(createTaskDTO.getTags()), createTaskDTO.getTitle(),
                Optional.ofNullable(createTaskDTO.getUser())
        );
    }

    @PostMapping("/update/{id}")
    public Task updateTask(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) User user,
            @RequestParam(required = false) Place place,
            @RequestParam(required = false) List<String> tags
    ) {
        return crudTask.updateTask(
                id,
                Optional.ofNullable(description),
                Optional.ofNullable(status),
                Optional.ofNullable(user),
                Optional.ofNullable(place),
                Optional.ofNullable(tags)
        );
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTask(Long taskID) {
        return crudTask.deleteTask(taskID);
    }

    @GetMapping("/getAll")
    public List<Task> getAll() {
        System.out.println("GETTING ALL TASKS");
        return searchTask.getAll();
    }
}
