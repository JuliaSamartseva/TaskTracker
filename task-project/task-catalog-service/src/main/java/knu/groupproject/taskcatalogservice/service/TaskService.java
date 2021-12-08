package knu.groupproject.taskcatalogservice.service;

import knu.groupproject.taskcatalogservice.model.Task;
import knu.groupproject.taskcatalogservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
  @Autowired private TaskRepository taskRepository;

  public List<Task> getAllTasks(String userEmail) {
    return taskRepository.findAllByUserEmail(userEmail);
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
