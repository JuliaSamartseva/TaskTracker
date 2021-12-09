package knu.groupproject.taskcatalogservice.repository;

import knu.groupproject.taskcatalogservice.model.Priority;
import knu.groupproject.taskcatalogservice.model.Status;
import knu.groupproject.taskcatalogservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findAllByUserEmail(String userEmail);
  @Modifying
  @Query("update Task t set t.name = ?1, t.description = ?2, t.deadline = ?3, t.status = ?4, t.priority = ?5 where t.id = ?6")
  void updateTaskById(String name, String description, Date deadline, Status status, Priority priority, Long id);
}
