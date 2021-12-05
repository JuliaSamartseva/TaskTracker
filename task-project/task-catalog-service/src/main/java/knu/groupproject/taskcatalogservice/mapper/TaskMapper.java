package knu.groupproject.taskcatalogservice.mapper;

import knu.groupproject.taskcatalogservice.dto.TaskClassDto;
import knu.groupproject.taskcatalogservice.model.Priority;
import knu.groupproject.taskcatalogservice.model.Status;
import knu.groupproject.taskcatalogservice.model.Task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
  public static List<TaskClassDto> mapTaskList(List<Task> tasks) {
    return tasks.stream()
        .map(
            taskObject ->
                TaskClassDto.builder()
                    .id(taskObject.getId())
                    .name(taskObject.getName())
                    .description(taskObject.getDescription())
                    .deadline(taskObject.getDeadline().toString())
                    .created(taskObject.getCreated().toString())
                    .status(taskObject.getStatus().toString())
                    .priority(taskObject.getPriority().toString())
                    .userEmail(taskObject.getUserEmail())
                    .build())
        .collect(Collectors.toList());
  }

  public static Task fromTaskDto(TaskClassDto dtoTask) {
    Task task = new Task();
    task.setName(dtoTask.getName());
    task.setDescription(dtoTask.getDescription());
    task.setDeadline(new Date(dtoTask.getDeadline()));
    task.setCreated(new Date(dtoTask.getCreated()));
    task.setStatus(Status.valueOf(dtoTask.getStatus()));
    task.setPriority(Priority.valueOf(dtoTask.getPriority()));
    task.setUserEmail(dtoTask.getUserEmail());
    return task;
  }
}
