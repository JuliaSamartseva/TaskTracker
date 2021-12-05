package knu.groupproject.taskcatalogservice.service;

import knu.groupproject.taskcatalogservice.dto.TaskClassDto;
import knu.groupproject.taskcatalogservice.model.Task;
import knu.groupproject.taskcatalogservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
  @Autowired private TaskRepository taskRepository;
  public List<TaskClassDto> getAllTasks(String userEmail) {
    return taskRepository
            .findAllByUserEmail(userEmail)
            .stream()
            .map(
                    taskObject -> TaskClassDto
                            .builder()
                            .id(taskObject.getId())
                            .name(taskObject.getName())
                            .description(taskObject.getDescription())
                            .deadline(taskObject.getDeadline().toString())
                            .created(taskObject.getCreated().toString())
                            .status(taskObject.getStatus().toString())
                            .priority(taskObject.getPriority().toString())
                            .userEmail(taskObject.getUserEmail())
                            .build()

            )
            .collect(Collectors.toList());
  }
}
