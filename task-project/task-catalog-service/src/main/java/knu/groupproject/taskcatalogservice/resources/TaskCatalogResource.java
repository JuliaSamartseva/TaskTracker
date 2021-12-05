package knu.groupproject.taskcatalogservice.resources;

import knu.groupproject.taskcatalogservice.dto.TaskClassDto;
import knu.groupproject.taskcatalogservice.mapper.TaskMapper;
import knu.groupproject.taskcatalogservice.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class TaskCatalogResource {
  private final Logger logger = LoggerFactory.getLogger(TaskCatalogResource.class);
  @Autowired private TaskService taskService;

  @GetMapping
  public List<TaskClassDto> getCatalog() {
    logger.info("Getting task list");
    return TaskMapper.mapTaskList(taskService.getAllTasks());
  }

  @PostMapping("/add-task")
  public void addTask(TaskClassDto task) {
    logger.info("Adding new task");
    taskService.saveTask(TaskMapper.fromTaskDto(task));
  }
}
