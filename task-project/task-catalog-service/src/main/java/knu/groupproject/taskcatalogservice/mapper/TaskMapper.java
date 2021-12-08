package knu.groupproject.taskcatalogservice.mapper;

import knu.groupproject.taskcatalogservice.dto.TaskClassDto;
import knu.groupproject.taskcatalogservice.model.Priority;
import knu.groupproject.taskcatalogservice.model.Status;
import knu.groupproject.taskcatalogservice.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
  private static final Logger logger = LoggerFactory.getLogger(TaskMapper.class);

  public static List<TaskClassDto> mapTaskList(List<Task> tasks) {
    return tasks.stream()
        .map(
            taskObject -> {
              TaskClassDto taskDto = new TaskClassDto();
              taskDto.setId(taskObject.getId());
              taskDto.setName(taskObject.getName());
              taskDto.setDescription(taskObject.getDescription());
              taskDto.setDeadline(taskObject.getDeadline().toString());
              taskDto.setCreated(taskObject.getCreated().toString());
              taskDto.setStatus(taskObject.getStatus().toString());
              taskDto.setPriority(taskObject.getPriority().toString());
              taskDto.setUserEmail(taskObject.getUserEmail());
              return taskDto;
            })
        .collect(Collectors.toList());
  }

  public static Task fromTaskDto(TaskClassDto dtoTask) {
    Task task = new Task();
    logger.info("Get task from dto: " + dtoTask.getName());
    task.setName(dtoTask.getName());
    task.setDescription(dtoTask.getDescription());

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

    Date deadlineDate = null;
    Date createdDate = null;
    try {
      deadlineDate = formatter.parse(dtoTask.getDeadline());
    } catch (ParseException | NullPointerException e) {
      e.printStackTrace();
    }
    try {
      createdDate = formatter.parse(dtoTask.getCreated());
    } catch (ParseException | NullPointerException e) {
      e.printStackTrace();
    }
    task.setDeadline(deadlineDate);
    task.setCreated(createdDate);
    task.setStatus(Status.valueOf(dtoTask.getStatus()));
    task.setPriority(Priority.valueOf(dtoTask.getPriority()));
    task.setUserEmail(dtoTask.getUserEmail());
    return task;
  }
}
