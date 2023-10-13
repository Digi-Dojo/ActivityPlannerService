//package it.unibz.taskcalendarservice.domain;
//
//import it.unibz.taskcalendarservice.common.domain.User;
//import it.unibz.taskcalendarservice.domain.task.Task;
//import it.unibz.taskcalendarservice.domain.task.Status;
//import TaskRepository;
//import it.unibz.taskcalendarservice.domain.task.TaskService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//public class TaskServiceTest {
//
//    private TaskRepository taskRepo;
//    @Mock
//    private TaskService taskService;
//
//    @Test
//    void createTaskTest(){
//        String description = "Test task";
//        Status status = Status.DONE;
//
//        Task createdTask = taskService.createTask(description, status);
//
//        Optional<Task> retrievedTask = taskRepo.findById(createdTask.getId());
//        assertTrue(retrievedTask.isPresent());
//        assertEquals(description, retrievedTask.get().getDescription());
//        assertEquals(status, retrievedTask.get().getStatus());
//    }
//
//    @Test
//    void updateTaskDescriptionTest(){
//
//        // create a new task and save it to the repository
//        Task task = new Task("Task 1", null);
//        task = taskRepo.save(task);
//
//        boolean updated = taskService.updateTaskDescription(task.getId(), "New description");
//
//        assertTrue(updated);
//        Optional<Task> updatedTask = taskRepo.findById(task.getId());
//        assertTrue(updatedTask.isPresent());
//        assertEquals("New description", updatedTask.get().getDescription());
//    }
//
//    @Test
//    void updateTaskStatusTest(){
//        Task task = new Task("Test task", Status.PENDING);
//        taskRepo.save(task);
//
//        boolean result = taskService.updateTaskStatus(task.getId(), Status.DONE);
//        assertTrue(result);
//
//        Optional<Task> updatedTask = taskRepo.findById(task.getId());
//        assertTrue(updatedTask.isPresent());
//        assertEquals(Status.DONE, updatedTask.get().getStatus());
//    }
//
//    @Test
//    void updateTaskUserTest(){
//
//        Task task = new Task("", null);
//        taskRepo.save(task);
//
//        User user = new User(0L,"John");
//
//        boolean result = taskService.updateTaskUser(task.getId(),user);
//        assertTrue(result);
//
//        assertNotNull(taskService.getTaskById(task.getId()).getUser());
//
//    }
//
//    @Test
//    void deleteTaskTest() {
//        Task task = new Task("Test task", Status.PENDING);
//        taskRepo.save(task);
//
//        boolean result = taskService.deleteTask(task.getId());
//        assertTrue(result);
//
//        Optional<Task> deletedTask = taskRepo.findById(task.getId());
//        assertTrue(deletedTask.isEmpty());
//    }
//
//    @Test
//    void testUpdateTaskTags() {
//        // create a task with tags to update
//        Task task = new Task("Test Task", Status.PENDING);
//        task.setTags(Arrays.asList("tag1", "tag2"));
//        taskRepo.save(task);
//
//        List<String> newTags = Arrays.asList("tag2", "tag3", "tag4");
//
//        boolean result = taskService.updateTaskTags(task.getId(), newTags);
//        assertTrue(result);
//
//        Optional<Task> updatedTask = taskRepo.findById(task.getId());
//        assertTrue(updatedTask.isPresent());
//        assertEquals(newTags, updatedTask.get().getTags());
//    }
//}
