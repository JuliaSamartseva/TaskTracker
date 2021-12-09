package knu.groupproject.taskcatalogservice.service;

import knu.groupproject.taskcatalogservice.dto.TaskClassDto;
import knu.groupproject.taskcatalogservice.model.Priority;
import knu.groupproject.taskcatalogservice.model.Status;
import knu.groupproject.taskcatalogservice.model.Task;
import knu.groupproject.taskcatalogservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
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
  @Transactional
  public void updateTask(TaskClassDto updatedTask, Long id){
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

    Date deadlineDate = null;
    try {
      deadlineDate = formatter.parse(updatedTask.getDeadline());
    } catch (ParseException | NullPointerException e) {
      e.printStackTrace();
    }
    taskRepository.updateTaskById(updatedTask.getName(), updatedTask.getDescription(), deadlineDate,
            Status.valueOf(updatedTask.getStatus()), Priority.valueOf(updatedTask.getPriority()), id);
  }
}
