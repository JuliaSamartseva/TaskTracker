package knu.groupproject.taskcatalogservice.service;

import knu.groupproject.taskcatalogservice.model.Task;
import knu.groupproject.taskcatalogservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
  @Autowired private TaskRepository taskRepository;

  public List<Task> getAllTasks(String userEmail) {
    return taskRepository.findAllByUserEmail(userEmail);
  }

  public List<Task> getAllSortedTasks(String email, String sort) {
    List<Task> tasks = getAllTasks(email);

    switch (sort) {
      case "name":
        tasks.sort(Comparator.comparing(Task::getName));
        break;
      case "deadline":
        tasks.sort(Comparator.comparing(Task::getDeadline));
        break;
      case "created":
        tasks.sort(Comparator.comparing(Task::getCreated));
        break;
      case "priority":
        tasks.sort(Comparator.comparing(Task::getPriority));
        break;
      default:
        return tasks;
    }

    return tasks;
  }

  public Optional<Task> getTaskById(Long id) {
    return taskRepository.findById(id);
  }

  public Task saveTask(Task task) {
    return taskRepository.save(task);
  }

  public void deleteTask(Long id){
    taskRepository.deleteById(id);
  }
}
