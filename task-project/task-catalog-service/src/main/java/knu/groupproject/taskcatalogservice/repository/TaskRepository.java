package knu.groupproject.taskcatalogservice.repository;

import knu.groupproject.taskcatalogservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {}
