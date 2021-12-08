package knu.groupproject.taskcatalogservice.resources;

import knu.groupproject.taskcatalogservice.dto.TaskClassDto;
import knu.groupproject.taskcatalogservice.mapper.TaskMapper;
import knu.groupproject.taskcatalogservice.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class TaskCatalogResource {
  private final Logger logger = LoggerFactory.getLogger(TaskCatalogResource.class);
  @Autowired private TaskService taskService;

  @GetMapping("/{email}")
  public List<TaskClassDto> getCatalog(@PathVariable String email) {
    logger.info("Getting task list");
    return TaskMapper.mapTaskList(taskService.getAllTasks(email));
  }

  @GetMapping("/{email}/{sort}")
  public List<TaskClassDto> getSortedCatalog(@PathVariable String email, @PathVariable String sort) {
    logger.info("Getting task list");
    return TaskMapper.mapTaskList(taskService.getAllSortedTasks(email, sort));
  }

  @PostMapping("/add-task")
  public void addTask(@RequestBody TaskClassDto task) {
    logger.info("Adding new task");
    taskService.saveTask(TaskMapper.fromTaskDto(task));
  }

  @DeleteMapping("/delete-task/{id}")
  public void deleteTask(@PathVariable Long id){
    logger.info("Into catalog delete task method");
    taskService.deleteTask(id);
  }
}
