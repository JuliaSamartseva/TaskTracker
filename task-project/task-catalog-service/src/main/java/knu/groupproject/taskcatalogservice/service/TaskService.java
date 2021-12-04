package knu.groupproject.taskcatalogservice.service;

import knu.groupproject.taskcatalogservice.model.Task;
import knu.groupproject.taskcatalogservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
  @Autowired private TaskRepository repository;

  public List<Task> getAllTasks() {
    return repository.findAll();
  }
}
